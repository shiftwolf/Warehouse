import * as db from "../connectors/db"


export class Customer {
    constructor(
        readonly name : string, 
        readonly country : string, 
        readonly address1 : string, 
        readonly address2 : string,
        readonly city : string,
        readonly state : string, 
        readonly zipcode : string
        ) 
    {
        
    }
}

export interface Customers {
    name : string, 
    country : string, 
    address1 : string, 
    address2 : string,
    state : string, 
    zipcode : string

    create() : any
    update() : any
    //static getById(id : string) : any
      
}

export class CustomerImpl implements Customers {
    constructor(
        readonly name : string, 
        readonly country : string, 
        readonly address1 : string, 
        readonly address2 : string,
        readonly state : string, 
        readonly zipcode : string
        ) {}

    create() {
        const request = new db.Request(
            `insert into customers (name, country, address1, address2, state, zipcode) values (?,?,?,?,?,?);`,
            [this.name, this.country, this.address1, this.address2, this.state, this.zipcode]
        )
        return db.sql(request)
    }

    update() {


    }

    static getById(id: string) {


    }

}