package com.project.app.service;

import com.project.app.model.Currency;
import com.project.app.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Currency findByShortName(String shortName) {
        return currencyRepository.findByShortName(shortName)
                .orElse(null);
    }

    public Currency findByFullName(String fullName) {
        return currencyRepository.findByFullName(fullName)
                .orElse(null);
    }
}
