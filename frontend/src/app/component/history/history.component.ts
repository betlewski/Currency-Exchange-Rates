import { Component, OnInit } from '@angular/core';
import {Rate} from "../../model/rate";
import {Currency} from "../../model/currency";
import {CurrencyService} from "../../service/currency.service";
import {RateService} from "../../service/rate.service";
import {finalize} from "rxjs/operators";
import * as CanvasJS from "../../../assets/canvasjs.min";
import {from} from "rxjs";

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  currencies: Array<Currency>;
  rates: Array<Rate>;
  chosenCurrency: Currency;

  message: string;
  dataPoints = [];
  chart: CanvasJS;

  constructor(private currencyService: CurrencyService,
              private rateService: RateService) {

    this.loadCurrencies();
    this.chosenCurrency = null;
    this.message = "Wybierz walutę, aby wyświetlić wykres zmian...";
  }

  ngOnInit(): void {
  }

  loadCurrencies() {
    this.currencyService.findAll()
      .subscribe(data =>
          this.currencies = data.filter(
            cur => cur.shortName != "EUR"));
  }

  loadRates() {
    let shortName = this.chosenCurrency.shortName;
    this.rateService.findAllByShortName(shortName)
      .pipe(
        finalize(() => this.refreshData()))
      .subscribe(
        data => this.rates = data);
  }

  refreshData() {
    let shortName = this.chosenCurrency.shortName;
    let min = this.rates[0].value;
    let max = this.rates[0].value;

    this.dataPoints = [];
    from(this.rates)
      .pipe(
        finalize(() => {
          min *= 0.95;
          max *= 1.05;
          this.drawChart(shortName, min, max);
        }))
      .subscribe(rate => {
        min = min > rate.value ? rate.value : min;
        max = max < rate.value ? rate.value : max;
        this.dataPoints.push({
          label: rate.date, y: rate.value, color: "darkgray"
        });
      });
  }

  drawChart(shortName: string, min: number, max: number) {
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
        minimum: min,
        maximum: max,
        suffix: " " + shortName
      },
      data: [{
        type: "splineArea",
        lineColor: "darkgray",
        color: "rgba(210,210,210,0.7)",
        yValueFormatString: "#0.00## " + shortName,
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
