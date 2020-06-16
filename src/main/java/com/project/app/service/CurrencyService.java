package com.project.app.service;

import com.project.app.model.Currency;
import com.project.app.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findAllOrdered() {
        return Observable.from(currencyRepository.findAll())
                .sorted((c1, c2) ->
                        CharSequence.compare(c1.getShortName(), c2.getShortName()))
                .toList()
                .toBlocking()
                .single();
    }

    public List<String> findAllShortNames() {
        return Observable.from(currencyRepository.findAll())
                .map(Currency::getShortName)
                .toList()
                .toBlocking()
                .single();
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
