import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Rate} from "../model/rate";
import {Currency} from "../model/currency";

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  private CURRENCY_URL = environment.baseUrl + "/currency";
  private FIND_ALL_URL = this.CURRENCY_URL + "/all";

  constructor(private httpClient: HttpClient) { }

  public findAll(): Observable<Currency[]> {
    return this.httpClient.get<Currency[]>(this.FIND_ALL_URL);
  }
}
