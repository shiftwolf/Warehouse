export interface Order {
    employeeID : string
    customerID : string
    contents: OrderContent[]
}

export interface OrderContent {
    productEAN: string
    amount: string
}