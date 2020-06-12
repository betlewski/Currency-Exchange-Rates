import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

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

}
