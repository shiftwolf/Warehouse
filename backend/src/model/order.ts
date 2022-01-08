export interface OrderForm {
    employeeID : string
    customerID : string
    contents: OrderFormContent[]
}

export interface OrderFormContent {
    productEAN: string
    amount: string
}

export interface IncomingOrder {
    employeeID : number
    customerID : number
    contents: IncomingOrderContent[]
}

export interface IncomingOrderContent {
    productEAN: number
    amount: number
}

export class Order implements OrderForm {
    constructor (
        incomingOrder : IncomingOrder
    ) {
        // Incoming are numbers but SQL queries need strings!
        this.employeeID = incomingOrder.employeeID.toString()
        this.customerID = incomingOrder.customerID.toString()
        this.contents = incomingOrder.contents.map(
            (content : IncomingOrderContent) : OrderFormContent => {
                return {
                    productEAN : content.productEAN.toString(),
                    amount : content.amount.toString()
                }
            }
        )
    }
    employeeID: string;
    customerID: string;
    contents: OrderFormContent[];
}