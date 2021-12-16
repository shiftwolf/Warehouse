"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.BaseModel = void 0;
const db_1 = require("../connectors/db");
class BaseModel {
    static async findAll(table) {
        const res = await (0, db_1.sql)(`select * from ${table};`);
        return res;
    }
}
exports.BaseModel = BaseModel;
