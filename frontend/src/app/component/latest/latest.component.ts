import { Component, OnInit } from '@angular/core';
import {Rate} from "../../model/rate";
import {RateService} from "../../service/rate.service";
import {finalize} from "rxjs/operators";

@Component({
  selector: 'app-latest',
  templateUrl: './latest.component.html',
  styleUrls: ['./latest.component.css']
})
export class LatestComponent implements OnInit {

  allRates: Array<Rate>;
  foundRates: Array<Rate>;
  date: string;
  findText: string;

  constructor(private rateService: RateService) {
    this.loadAllRates();
  }

  ngOnInit(): void {
  }

  loadAllRates() {
    this.rateService.findAllLast()
      .pipe(
        finalize(() => {
          this.date = this.allRates[0].date;
          this.loadFoundRates();
        }))
      .subscribe(
      data => this.allRates = data);
  }

  loadFoundRates() {
    if(this.findText != undefined && this.findText != "") {
      let text = this.findText.toLowerCase();

      this.foundRates = this.allRates
        .filter(rate =>
          rate.currency.shortName.toLowerCase().includes(text) ||
          rate.currency.fullName.toLowerCase().includes(text) ||
          rate.currency.country.toLowerCase().includes(text) )}
    else {
      this.foundRates = this.allRates;
    }
  }

  onEvent() {
    this.loadFoundRates();
  }
}
