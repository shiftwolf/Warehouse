import mariadb from "mariadb"

const pool = mariadb.createPool({
    port: 3306,
    host: 'localhost',
    user: 'admin',
    password: 'admin',
    database: 'store',
    connectionLimit: 5
})

export const sql = async (query : string, params : string[] = []) => {
    const conn = await pool.getConnection()
    const rows = await conn.query(query, params)
    return rows
}

