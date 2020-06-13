import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LatestComponent} from "./component/latest/latest.component";
import {HistoryComponent} from "./component/history/history.component";
import {CalculatorComponent} from "./component/calculator/calculator.component";
import {AboutComponent} from "./component/about/about.component";


const routes: Routes = [
  { path: '', component: AboutComponent },
  { path: 'latest', component: LatestComponent },
  { path: 'history', component: HistoryComponent },
  { path: 'calculator', component: CalculatorComponent },
  { path: '**', pathMatch: 'full', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
