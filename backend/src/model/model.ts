import { sql, sequentialSQL, Request } from "../connectors/db"
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
                `insert into products (ean, name, amount) values (?,?,?);`, 
                [product.ean, product.name, product.amount]))
        return res
    }

    static async findProduct (id : string) {
        console.log(id)
        const res = await sql(new Request(`select * from products where ean = ?;`, [id]))
        return res
    }

    static async findOrderPreviews () {
        const res = await sql(new Request(
            `select orders.id as orderId, orders.created_at as createdAt, orders.completed as completed, customers.name as customerName, customers.id as customerId, employees.name as employeeName from (orders inner join customers on customers.id = orders.customer_id) left join employees on employees.id = orders.employee_id;`
        ))
        return res
    }

    static async findOrderDetails (orderID : string) {
        let res = await sql(new Request(
            `select order_row.id orderId, order_row.created_at orderCreatedAt, order_row.completed orderCompleted, customers.id customerId, customers.name customerName, customers.country customerCountry, customers.state customerState, customers.zipcode customerZipcode, customers.address1 customerAddress1, customers.address2 customerAddress2, customers.city customerCity, employees.name employeeName ,JSON_ARRAYAGG(JSON_OBJECT("ean", products.ean,"name", products.name,"amount", products.amount, "location", products.location)) orderContents from ((((select * from orders where orders.id = ?) order_row inner join customers on customers.id = order_row.customer_id) left join employees on employees.id = order_row.employee_id) inner join order_contents on order_row.id = order_contents.order_id) inner join products on products.ean = order_contents.product_ean;`
            , [orderID]
        ))
        console.log(res)
        
        // MariaDB stringifies the JSON of res[0].orderContents
        // So here we extract orderContents, parse it to JSON again
        // and put it back in to res
        let orderContents = JSON.parse(res[0].orderContents)
        res[0].orderContents = orderContents

        const newres = {
            order: {
                id: res[0].orderId,
                created_at: res[0].orderCreatedAt,
                completed: res[0].orderCompleted
            },
            customer: {
                id: res[0].customerId,
                name: res[0].customerName,
                country: res[0].customerCountry,
                state: res[0].customerState,
                zipcode: res[0].customerZipcode,
                city: res[0].customerCity,
                address1: res[0].customerAddress1,
                address2: res[0].customerAddress2,
            },
            products: res[0].orderContents,
            employeeName: res[0].employeeName
        }


        return newres
    }

    static async updateOrderCompletion(orderID : string, newState : string) {
        const res = await sql(new Request(
            `update orders set completed = ? where id = ?;`,
            [newState, orderID]
        ))
        return res
    }

    static async updateProduct (product : Product) {
        const res = product.location === undefined 
            ? await sql(new Request(
                `update products set name = ?, amount = ? where ean = ?;`, 
                [product.name, product.amount, product.ean]))
            : await sql(new Request(
                `update products set name = ?, amount = ?, location = ? where ean = ?;`, 
                [product.name, product.amount, product.location, product.ean]))
        return res
    }

    static async createCustomer (customer : Customer) {
        const res = await sql(new Request(
            `insert into customers (name, country, address1, address2, state, zipcode, city) values (?,?,?,?,?,?,?);`, 
            [customer.name, customer.country, customer.address1, 
                customer.address2, customer.state, customer.zipcode]))
        return res
    }

    static async updateCustomer (customer : Customer, id : string) {
        const res = await sql(new Request(
            `update customers set name = ?, country = ?, address1 = ?, address2 = ?, state = ?, zipcode = ?, city = ? where id = ?;`, 
            [customer.name, customer.country, customer.address1, 
                customer.address2, customer.state, customer.zipcode, customer.city, id]))
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