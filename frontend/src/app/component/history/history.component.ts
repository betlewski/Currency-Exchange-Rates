import { Component, OnInit } from '@angular/core';
import {Rate} from "../../model/rate";
import {Currency} from "../../model/currency";
import {CurrencyService} from "../../service/currency.service";
import {RateService} from "../../service/rate.service";
import * as CanvasJS from "../../../assets/canvasjs.min";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  currencies: Array<Currency>;
  rates: Array<Rate>;
  chosenCurrency: Currency;

  dataPoints = [];
  chart: CanvasJS;

  constructor(private currencyService: CurrencyService,
              private rateService: RateService) {

    this.loadCurrencies();
    this.chosenCurrency = null;
  }

  ngOnInit(): void {
  }

  loadCurrencies() {
    this.currencyService.findAll()
      .subscribe(
        data => this.currencies = data
            .filter(value => value.shortName != "EUR")
      );
  }

  loadRates() {
    let shortName = this.chosenCurrency.shortName;
    this.rateService.findAllByShortName(shortName)
      .subscribe(
        data => {
          this.rates = data;
          this.refreshData();
        });
  }

  refreshData() {
    let shortName = this.chosenCurrency.shortName;
    let max = this.rates[0].value;

    this.dataPoints = [];
    this.rates.forEach(rate => {
      this.dataPoints.push({
        label: rate.date, y: rate.value, color: "darkgray"
      });
      max = max < rate.value ? rate.value : max;
    });
    max += max * 0.6;
    this.drawChart(shortName, max);
  }

  drawChart(shortName: string, max: number) {
    this.chart = new CanvasJS.Chart("chart", {
      theme: "light2",
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "wartość 1 EUR",
        fontColor: "black",
        fontWeight: "lighter",
        fontSize: 15,
        margin: 10,
        horizontalAlign: "center"
      },
      axisY: {
        maximum: max,
        suffix: " " + shortName
      },
      data: [{
        type: "splineArea",
        lineColor: "darkgray",
        color: "rgba(210,210,210,0.7)",
        yValueFormatString: "#.## " + shortName,
        dataPoints: this.dataPoints
      }]
    });
    this.chart.render();
  }

  onChange() {
    if(this.chosenCurrency.id != undefined) {
      this.loadRates();
    }
    else {
      this.rates = null;
      this.chart.destroy();
    }
  }
}
