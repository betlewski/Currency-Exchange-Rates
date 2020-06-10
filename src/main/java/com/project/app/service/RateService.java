package com.project.app.service;

import com.project.app.model.Rate;
import com.project.app.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RateService {

    private RateRepository rateRepository;

    @Autowired
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public List<Rate> findAllByCurrencyShortNameOrderByDateAsc(String shortName) {
        return rateRepository.findAllByCurrencyShortNameOrderByDateAsc(shortName);
    }

    public Rate findLastByCurrencyShortNameOrderByDateDesc(String shortName) {
        return rateRepository.findTopByCurrencyShortNameOrderByDateDesc(shortName)
                .orElse(null);
    }

    public Double findLastExchangeRateByShortNames(String shortName1, String shortName2) {

        Rate rate1 = rateRepository.findTopByCurrencyShortNameOrderByDateDesc(shortName1)
                .orElse(null);
        Rate rate2 = rateRepository.findTopByCurrencyShortNameOrderByDateDesc(shortName2)
                .orElse(null);

        if(rate1 != null && rate2 != null) {
            Double value1 = rate1.getValue();
            Double value2 = rate2.getValue();

            return BigDecimal.valueOf(value2 / value1)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        else {
            return null;
        }
    }
}
