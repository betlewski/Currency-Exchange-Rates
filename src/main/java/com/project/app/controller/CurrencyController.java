package com.project.app.controller;

import com.project.app.model.Currency;
import com.project.app.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
@CrossOrigin
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/byShortName/{shortName}")
    @ResponseBody
    public Currency findByShortName(@PathVariable String shortName) {
        return currencyService.findByShortName(shortName);
    }

    @GetMapping("/byFullName/{fullName}")
    @ResponseBody
    public Currency findByFullName(@PathVariable String fullName) {
        return currencyService.findByFullName(fullName);
    }
}
