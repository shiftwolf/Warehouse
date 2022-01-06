import bcrypt from "bcrypt"
import { sql, sequentialSQL, Request } from "../connectors/db"

export class Employee {
    constructor(
        readonly username : string, 
        readonly password : string,
        readonly name : string, 
        readonly permissions : string, 
        readonly id? : string
        ) 
    {}
}

export class EmployeeORM {
    constructor(
        readonly employee : Employee
    ) 
    {}

    async create() {
        const hash = bcrypt.hashSync(this.employee.password, 10);
        const res = await sql(new Request(
            `insert into employees (username, hashedpw, name, permissions) values (?,?,?,?);`, 
            [this.employee.username, hash, this.employee.name, this.employee.permissions]
        ))
        return res     
    }

    async update() {
        let res
        if (this.employee.id) {
            res = await sql(new Request(
                `update employees set name = ?, permissions = ? where id = ?;`, 
                [this.employee.name, this.employee.permissions, this.employee.id]
            ))
        }
        return res
    }


}