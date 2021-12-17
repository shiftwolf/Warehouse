import { sql } from "../connectors/db"
import bcrypt from "bcrypt"

export abstract class BaseModel {
    static async findAll (table : string) {
        const res = await sql(`select * from ${table};`)
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
    static async updateProduct (name: string, amount: string, id : string) {
        const res = await sql(
            `update products set name = ?, amount = ? where ean = ?;`, 
            [name, amount, id])
        return res
    }
}