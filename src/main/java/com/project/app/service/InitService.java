package com.project.app.service;

import com.project.app.model.Currency;
import com.project.app.repository.CurrencyRepository;
import com.project.app.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

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

        List<Currency> currencies = Arrays.asList(
                new Currency(0L, "EUR", "euro", "kraje strefy Euro"),
                new Currency(0L, "CAD", "kanadyjski dolar", "Kanada"),
                new Currency(0L, "HKD", "dolar", "Hong Kong"),
                new Currency(0L, "ISK", "islandzka korona", "Islandia"),
                new Currency(0L, "PHP", "filipińskie peso", "Filipiny"),
                new Currency(0L, "DKK", "duńska korona", "Dania"),
                new Currency(0L, "HUF", "węgierski forint", "Węgry"),
                new Currency(0L, "CZK", "czeska korona", "Czechy"),
                new Currency(0L, "AUD", "australijski dolar", "Australia"),
                new Currency(0L, "RON", "rumuński lei", "Rumunia"),
                new Currency(0L, "SEK", "korona szwedzka", "Szwecja"),
                new Currency(0L, "IDR", "indonezyjska rupiah", "Indonezja"),
                new Currency(0L, "INR", "indyjska rupia", "Indie"),
                new Currency(0L, "BRL", "brazylijski real", "Brazylia"),
                new Currency(0L, "RUB", "rosyjski rubl", "Rosja"),
                new Currency(0L, "HRK", "kuna chorwacka", "Chorwacja"),
                new Currency(0L, "JPY", "japoński jen", "Japonia"),
                new Currency(0L, "THB", "tajlandzki bat", "Tajlandia"),
                new Currency(0L, "CHF", "szwajcarski frank", "Szwajcaria"),
                new Currency(0L, "SGD", "singapurski dolar", "Singapur"),
                new Currency(0L, "PLN", "polski złoty", "Polska"),
                new Currency(0L, "BGN", "bułgarski lew", "Bułgaria"),
                new Currency(0L, "TRY", "turecka lira", "Turcja"),
                new Currency(0L, "CNY", "chiński yuan", "Chiny"),
                new Currency(0L, "NOK", "korona norweska", "Norwegia"),
                new Currency(0L, "NZD", "nowozelandzki dolar", "Nowa Zelandia"),
                new Currency(0L, "ZAR", "południowoafrykański rand", "Republika Południowej Afryki"),
                new Currency(0L, "USD", "amerykański dolar", "Stany Zjednoczone"),
                new Currency(0L, "MXN", "meksykańskie peso", "Meksyk"),
                new Currency(0L, "ILS", "izraelski szekel", "Izrael"),
                new Currency(0L, "GBP", "funt szterling", "Wielka Brytania"),
                new Currency(0L, "KRW", "koreański won", "Korea Południowa"),
                new Currency(0L, "MYR", "malezyjski ringit", "Malezja"));

        currencyRepository.saveAll(currencies);
    }
}
