import {Currency} from "./currency";

export class Rate {
  id: number;
  currency: Currency;
  value: number;
  lastChange: number;
  date: string;

  constructor(currency: Currency, value: number, lastChange: number, date: string) {
    this.currency = currency;
    this.value = value;
    this.lastChange = lastChange;
    this.date = date;
  }
}
