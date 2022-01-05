import { sql, Request, sequentialSQL } from "../connectors/db"
import { Customer } from "./customer"
import { Order, OrderContent } from "../model/order"
import { Product } from "./product"
import { Employee } from "./employee"
import bcrypt from "bcrypt"


export abstract class Model {
    static async findAll (table : string) {
        const res = await sql(new Request(`select * from ${table};`))
        return res
    }

    static async findOne (table : string, id : string, customID : string = "id") {
        const res = await sql(new Request(`select * from ${table} where ${customID} = ?;`, [id]))
        return res
    }

    static async deleteOne (table : string, id : string, customID : string = "id") {
        const res = await sql(new Request(`delete from ${table} where ${customID} = ?;`, [id]))
        return res
    }

    static async deleteOrder (id : string) {
        const res = await sequentialSQL([
            new Request(`delete from orders where id = ?;`, [id]),
            new Request(`delete from order_contents where order_id = ?`, [id])
        ])
        return res
    }

    static async createEmployee (employee : Employee) {
        const hash = bcrypt.hashSync(employee.password, 10);
        const res = await sql(new Request(
            `insert into employees (username, hashedpw, name, permissions) values (?,?,?,?);`, 
            [employee.username, hash, employee.name, employee.permissions]))
        return res
    }

    static async createProduct (product : Product) {
        const res = product.location !== undefined 
            ? await sql(new Request(
                `insert into products (ean, name, amount, location) values (?,?,?,?);`, 
                [product.ean, product.name, product.amount, product.location]))
            : await sql(new Request(
                `insert into products (ean, name, amount, location) values (?,?,?);`, 
                [product.ean, product.name, product.amount]))
        return res
    }

    static async findProduct (id : string) {
        console.log(id)
        const res = await sql(new Request(`select * from products where ean = ?;`, [id]))
        return res
    }

    static async updateProduct (product : Product) {
        const res = product.location !== undefined 
            ? await sql(new Request(
                `update products set name = ?, amount = ? where ean = ?;`, 
                [product.name, product.amount, product.ean]))
            : await sql(new Request(
                `update products set name = ?, amount = ?, location = ? where ean = ?;`, 
                [product.name, product.amount, product.ean]))
        return res
    }

    static async createCustomer (customer : Customer) {
        const res = await sql(new Request(
            `insert into customers (name, country, address1, address2, state, zipcode) values (?,?,?,?,?,?);`, 
            [customer.name, customer.country, customer.address1, 
                customer.address2, customer.state, customer.zipcode]))
        return res
    }
    static async updateCustomer (customer : Customer, id : string) {
        const res = await sql(new Request(
            `update customers set name = ?, country = ?, address1 = ?, address2 = ?, state = ?, zipcode = ? where id = ?;`, 
            [customer.name, customer.country, customer.address1, 
                customer.address2, customer.state, customer.zipcode, id]))
        return res
    }
    static async findOrderContents (orderID : string) {
        const res = await sql(new Request(`select * from order_contents where order_id = ?;`, [orderID]))
        return res
    }

    static async createOrder (order : Order) {
        const requests1 : Request[] = [
            new Request(`insert into orders (employee_id, customer_id, completed) values (?,?,0);`, 
            [order.employeeID, order.customerID]),
            new Request(`SET @id = LAST_INSERT_ID()`)
        ]
        const contents : OrderContent[] = order.contents
        const requests2 : Request[] = contents.map(content => {
            return new Request(`insert into order_contents (order_id, product_ean, amount) values (@id,?,?);`,
            [content.productEAN, content.amount])
        })
        const responses = await sequentialSQL(requests1.concat(requests2))
        return responses
    }
}