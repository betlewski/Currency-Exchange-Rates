package com.project.app.service;

import com.project.app.model.Currency;
import com.project.app.model.Rate;
import com.project.app.repository.CurrencyRepository;
import com.project.app.repository.RateRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataAPIService {

    private CurrencyRepository currencyRepository;
    private RateRepository rateRepository;

    private static final String API_URL = "https://api.exchangeratesapi.io/latest";

    @Autowired
    public DataAPIService(CurrencyRepository currencyRepository,
                       RateRepository rateRepository) {
        this.currencyRepository = currencyRepository;
        this.rateRepository = rateRepository;
    }

    @Scheduled(cron = "0 0 18 ? * MON-FRI")
    public void updateData() throws IOException, JSONException {
        JSONObject json = readDataFromUrl();
        saveDataFromUrl(json);
    }

    private JSONObject readDataFromUrl() throws IOException {
        try (InputStream is = new URL(DataAPIService.API_URL).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);

            return new JSONObject(jsonText);
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;

        while((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private void saveDataFromUrl(JSONObject json) throws JSONException {
        LocalDate lastDate, newDate;

        Optional<Rate> rate = rateRepository.findTopByCurrencyShortNameOrderByDateDesc("EUR");
        lastDate = rate.map(Rate::getDate).orElse(null);

        String date = json.get("date").toString();
        newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if(lastDate == null || newDate.isAfter(lastDate)) {
            JSONObject rates = (JSONObject) json.get("rates");

            List<String> shortNames = currencyRepository.findAll()
                    .stream()
                    .map(Currency::getShortName)
                    .filter(shortName -> !shortName.equals("EUR"))
                    .collect(Collectors.toList());

            for (String shortName : shortNames) {
                String rateString = rates.get(shortName).toString();
                double change = 0.0;

                Currency currency = currencyRepository.findByShortName(shortName)
                        .orElse(null);

                Double rateValue = BigDecimal.valueOf(Double.parseDouble(rateString))
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();

                Rate lastRate = rateRepository.findTopByCurrencyShortNameOrderByDateDesc(shortName)
                        .orElse(null);

                if(lastRate != null) {
                    String changeString = String.valueOf(
                            ((rateValue - lastRate.getValue()) * 100) / rateValue);

                    change = BigDecimal.valueOf(Double.parseDouble(changeString))
                            .setScale(1, RoundingMode.HALF_UP)
                            .doubleValue();
                }

                if(currency != null) {
                    Rate newRate = new Rate(0L, currency, rateValue, change, newDate);
                    rateRepository.save(newRate);
                }
            }
            saveBaseRate(newDate);
            System.out.println("Update data on " + newDate);
        }
    }

    private void saveBaseRate(LocalDate date) {
        Currency euro = currencyRepository.findByShortName("EUR")
                .orElse(null);

        if(euro != null) {
            Rate newRate = new Rate(0L, euro, 1.0, 0.0, date);
            rateRepository.save(newRate);
        }
    }
}