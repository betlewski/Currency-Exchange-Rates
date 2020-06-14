import { Component, OnInit } from '@angular/core';
import {Currency} from "../../model/currency";
import {CurrencyService} from "../../service/currency.service";
import {RateService} from "../../service/rate.service";

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  currenciesFrom: Array<Currency>;
  currenciesTo: Array<Currency>;
  chosenFrom: Currency;
  chosenTo: Currency;

  value1From: number;
  value1To: number;
  value2From: number;
  value2To: number;

  constructor(private currencyService: CurrencyService,
              private rateService: RateService) {

    this.loadCurrenciesFrom();
    this.currenciesTo = null;
    this.chosenFrom = null;
    this.chosenTo = null;
  }

  ngOnInit(): void {
  }

  loadCurrenciesFrom() {
    this.currencyService.findAll()
      .subscribe(
      data => this.currenciesFrom = data);
  }

  loadCurrenciesTo() {
    this.currenciesTo = this.currenciesFrom
      .filter(value => value != this.chosenFrom);
  }

  getExchangeRate() {
    let shortName1 = this.chosenFrom.shortName;
    let shortName2 = this.chosenTo.shortName;

    this.rateService.findLastByShortNames(shortName1, shortName2)
      .subscribe(
        data => this.value1To = data);
  }

  calculate() {
    let rate = this.value1To;
    let number = this.value2From;

    if(rate != null && number >= 0) {
      this.value2To = number * rate;
    }
    else {
      this.value2To = null;
    }
  }

  onChangeFrom() {
    this.chosenTo = null;
    this.value1To = null;
    this.value2To = null;

    if(this.chosenFrom.id != undefined) {
      this.loadCurrenciesTo();
      this.value1From = 1;
    }
    else {
      this.currenciesTo = null;
      this.value1From = null;
    }
  }

  onChangeTo() {
    this.value2To = null;

    if(this.chosenTo.id != undefined) {
      this.getExchangeRate();
    }
    else {
      this.value1To = null;
    }
  }
}
