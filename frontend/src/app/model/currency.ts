export class Currency {
  id: number;
  shortName: string;
  fullName: string;
  country: string;

  constructor(shortName: string, fullName: string, country: string) {
    this.shortName = shortName;
    this.fullName = fullName;
    this.country = country;
  }
}
