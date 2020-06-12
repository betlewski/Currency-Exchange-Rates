package com.project.app.service;

import com.project.app.model.Currency;
import com.project.app.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    public List<String> findAllShortNames() {
        return currencyRepository.findAll()
                .stream()
                .map(Currency::getShortName)
                .collect(Collectors.toList());
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
