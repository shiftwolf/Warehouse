import { sql } from "../connectors/db"

export abstract class BaseModel {
    static async findAll (table : string) {
        const res = await sql(`select * from ${table};`)
        return res
    }
}