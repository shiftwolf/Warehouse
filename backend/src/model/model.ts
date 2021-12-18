import { sql } from "../connectors/db"
import { Customer } from "./customer"
import bcrypt from "bcrypt"

export abstract class Model {
    static async findAll (table : string) {
        const res = await sql(`select * from ${table};`)
        return res
    }
    static async findOne (table : string, id : string) {
        const res = await sql(`select * from ${table} where id = ?;`, [id])
        return res
    }
    static async createEmployee (username: string, password: string,
        firstname: string, lastname: string, permissions: string) {
        const hash = bcrypt.hashSync(password, 10);
        const res = await sql(
            `insert into employees (username, password, firstname, lastname, permissions) values (?,?,?,?,?);`, 
            [username, password, firstname, lastname, permissions])
        return res
    }
    static async createProduct (ean: string, name: string, amount: string) {
        const res = await sql(
            `insert into products (ean, name, amount) values (?,?,?);`, 
            [ean, name, amount])
        return res
    }
    static async updateProduct (name: string, amount: string, ean : string) {
        const res = await sql(
            `update products set name = ?, amount = ? where ean = ?;`, 
            [name, amount, ean])
        return res
    }
    static async createCustomer (customer : Customer) {
        const res = await sql(
            `insert into customers (name, country, adress1, adress2, state, zipcode) values (?,?,?,?,?,?);`, 
            [customer.name, customer.country, customer.adress1, 
                customer.adress2, customer.state, customer.zipcode])
        return res
    }
    static async updateCustomer (customer : Customer, id : string) {
        const res = await sql(
            `update customers set name = ?, country = ?, adress1 = ?, adress2 = ?, state = ?, zipcode = ? where id = ?;`, 
            [customer.name, customer.country, customer.adress1, 
                customer.adress2, customer.state, customer.zipcode, id])
        return res
    }
}