package com.project.app.service;

import com.project.app.repository.CurrencyRepository;
import com.project.app.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataAPIService {

    private CurrencyRepository currencyRepository;
    private RateRepository rateRepository;

    @Autowired
    public DataAPIService(CurrencyRepository currencyRepository,
                       RateRepository rateRepository) {
        this.currencyRepository = currencyRepository;
        this.rateRepository = rateRepository;
    }

    @Scheduled(cron = "")
    public void loadDataFromURL() {

    }
}
