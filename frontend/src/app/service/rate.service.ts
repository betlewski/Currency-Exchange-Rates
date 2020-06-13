import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {Rate} from "../model/rate";

@Injectable({
  providedIn: 'root'
})
export class RateService {

  private RATE_URL = environment.baseUrl + "/rate";
  private FIND_ALL_BY_SHORTNAME_URL = this.RATE_URL + "/allByShortName";
  private FIND_ALL_LAST_URL = this.RATE_URL + "/allLast";
  private FIND_LAST_BY_SHORTNAME_URL = this.RATE_URL + "/lastByShortName";
  private FIND_LAST_BY_SHORTNAMES_URL = this.RATE_URL + "/lastByShortNames";

  constructor(private httpClient: HttpClient) { }

  public findAllByShortName(shortName: string): Observable<Rate[]> {
    return this.httpClient.get<Rate[]>(this.FIND_ALL_BY_SHORTNAME_URL + "/" + shortName);
  }

  public findAllLast(): Observable<Rate[]> {
    return this.httpClient.get<Rate[]>(this.FIND_ALL_LAST_URL);
  }

  public findLastByShortName(shortName: string): Observable<Rate> {
    return this.httpClient.get<Rate>(this.FIND_LAST_BY_SHORTNAME_URL);
  }

  public findLastByShortNames(shortName1: string, shortName2: string): Observable<number> {
    return this.httpClient.get<number>(this.FIND_LAST_BY_SHORTNAMES_URL +
      + "/" + shortName1 + "/" + shortName2);
  }
}
