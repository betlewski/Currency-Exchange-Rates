import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {RateService} from "./service/rate.service";
import { LatestComponent } from './component/latest/latest.component';
import { HistoryComponent } from './component/history/history.component';
import { CalculatorComponent } from './component/calculator/calculator.component';
import { AboutComponent } from './component/about/about.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    LatestComponent,
    HistoryComponent,
    CalculatorComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [RateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
