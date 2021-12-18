export class Customer {
    name: string;
    country: string;
    adress1: string;
    adress2: string;
    state: string;
    zipcode: string;
    constructor(name : string, country : string, adress1 : string, adress2 : string,
         state : string, zipcode : string) {
        this.name = name
        this.country = country
        this.adress1 = adress1
        this.adress2 = adress2
        this.state = state
        this.zipcode = zipcode
    }
}