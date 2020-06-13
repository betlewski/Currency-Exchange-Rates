import { Component, OnInit } from '@angular/core';
import {Rate} from "../../model/rate";
import {RateService} from "../../service/rate.service";

@Component({
  selector: 'app-latest',
  templateUrl: './latest.component.html',
  styleUrls: ['./latest.component.css']
})
export class LatestComponent implements OnInit {

  rates: Array<Rate>;
  date: string;

  constructor(private rateService: RateService) {
    this.loadRates();
  }

  ngOnInit(): void {
  }

  loadRates() {
    this.rateService.findAllLast().subscribe(
      data =>  {
        this.rates = data;
        this.date = data[0].date;
      });
  }
}
