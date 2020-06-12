import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {RateService} from "./service/rate.service";
import { LatestComponent } from './component/latest/latest.component';
import { HistoryComponent } from './component/history/history.component';
import { CalculatorComponent } from './component/calculator/calculator.component';
import { MainComponent } from './component/main/main.component';

@NgModule({
  declarations: [
    AppComponent,
    LatestComponent,
    HistoryComponent,
    CalculatorComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [RateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
