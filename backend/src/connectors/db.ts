import mariadb from "mariadb"

export class Request {
    query
    params
    constructor(query : string, params? : string[]) {
        this.query = query
        this.params = params !== undefined ?  params : []
    }
}

const pool = mariadb.createPool({
    port: 3306,
    host: 'localhost',
    user: 'admin',
    password: 'admin',
    database: 'store',
    connectionLimit: 5
})

export const sql = async (request : Request) => {
    const conn = await pool.getConnection()
    const rows = await conn.query(request.query, request.params)
    return rows
}

export const sequentialSQL = async (requests : Request[]) : Promise<any[]> => {
    const conn = await pool.getConnection()
    let result : any[] = []
    // Not using Promise.all, as sequential execution is necessary
    result = requests.map(async req => {
        return await conn.query(req.query, req.params)
    })
    return result
}

