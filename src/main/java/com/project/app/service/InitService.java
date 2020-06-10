package com.project.app.service;

import com.project.app.repository.CurrencyRepository;
import com.project.app.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitService {

    private CurrencyRepository currencyRepository;
    private RateRepository rateRepository;

    @Autowired
    public InitService(CurrencyRepository currencyRepository,
                       RateRepository rateRepository) {
        this.currencyRepository = currencyRepository;
        this.rateRepository = rateRepository;
    }

    @PostConstruct
    public void init() {

    }
}
