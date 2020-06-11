package com.project.app.controller;

import com.project.app.model.Rate;
import com.project.app.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rate")
@CrossOrigin
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/allByShortName/{shortName}")
    @ResponseBody
    public List<Rate> findAllByCurrencyShortNameOrderByDateAsc(@PathVariable String shortName) {
        return rateService.findAllByCurrencyShortNameOrderByDateAsc(shortName);
    }

    @GetMapping("/allLast")
    @ResponseBody
    public List<Rate> findAllLastOrderByCurrencyShortNameAsc() {
        return rateService.findAllLastOrderByCurrencyShortNameAsc();
    }

    @GetMapping("/lastByShortName/{shortName}")
    @ResponseBody
    public Rate findLastByCurrencyShortNameOrderByDateDesc(@PathVariable String shortName) {
        return rateService.findLastByCurrencyShortNameOrderByDateDesc(shortName);
    }

    @GetMapping("/lastByShortNames/{shortName1}/{shortName2}")
    @ResponseBody
    public Double findLastExchangeRateByShortNames(@PathVariable String shortName1,
                                                   @PathVariable String shortName2) {
        return rateService.findLastExchangeRateByShortNames(shortName1, shortName2);
    }
}
