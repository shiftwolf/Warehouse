export class Product {
    constructor(
        readonly ean : string,
        readonly name : string,
        readonly amount : string,
        readonly location? : string
    ) {

    }
}