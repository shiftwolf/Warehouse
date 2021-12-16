"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.fetchDB = void 0;
const mariadb_1 = __importDefault(require("mariadb"));
const pool = mariadb_1.default.createPool({
    port: 3306,
    host: 'localhost',
    user: 'admin',
    password: 'admin',
    database: 'store',
    connectionLimit: 5
});
const fetchDB = async (query, params) => {
    const conn = await pool.getConnection();
    const rows = await conn.query(query, params);
    const result = {
        content: rows,
        status: rows.length ? 200 : 404
    };
    return result;
};
exports.fetchDB = fetchDB;
