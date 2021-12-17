import { BaseModel } from "../model/model";
import express from "express"

export abstract class BaseController {
    static async findAll(req : express.Request, res : express.Response) {
        // Regex should match last hierarchical element of endpoint
        // e.g. /apiv1/abc/customers/ => customers
        const matched : RegExpMatchArray | null = req.path.match(/([\w]+)(?=$)/g)
        let result : string;
        if (matched) {
            result = await BaseModel.findAll(matched[0])
            res.status(200).send(result)
        } else {
            result = "An error occured on database access."
            res.status(400).send(result)
        }
        
    }
    static async createEmployee(req : express.Request, res : express.Response) {
        const matched : RegExpMatchArray | null = req.path.match(/([\w]+)(?=$)/g)
        if (typeof req.query.username === "string" && typeof req.query.password === "string" && matched) {
            const result = await(BaseModel.createEmployee(matched[0], req.query.username, req.query.password))
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }

}