export class Customer {
    name: string;
    country: string;
    address1: string;
    address2: string;
    state: string;
    zipcode: string;
    constructor(name : string, country : string, address1 : string, address2 : string,
         state : string, zipcode : string) {
        this.name = name
        this.country = country
        this.address1 = address1
        this.address2 = address2
        this.state = state
        this.zipcode = zipcode
    }
}