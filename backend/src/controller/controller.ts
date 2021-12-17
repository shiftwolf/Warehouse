import { BaseModel } from "../model/model";
import express from "express"

export abstract class BaseController {

    static pathToTable(path : any) : RegExpMatchArray | null {
        // Regex should match last hierarchical element of endpoint or null
        // e.g. "/apiv1/abc/customers/" => "customers"
        return path.match(/([\w]+)(?=$)/g)
    }

    static async findAll(req : express.Request, res : express.Response) {
        const matched : RegExpMatchArray | null = this.pathToTable(req.path)
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
        if (typeof req.query.username === "string" && typeof req.query.password === "string" &&
        typeof req.query.firstname === "string" && typeof req.query.lastname === "string" &&
        typeof req.query.permissions === "string") {
            const result = await(BaseModel.createEmployee(
                req.query.username, req.query.password, req.query.firstname, req.query.lastname,
                req.query.permissions
                ))
            res.status(201).send(result)
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }
    static async createProduct(req : express.Request, res : express.Response) {
        if (typeof req.query.ean === "string" && typeof req.query.name === "string"
        && typeof req.query.amount === "string") {
            const result = await(BaseModel.createProduct(req.query.ean, req.query.name, req.query.amount))
            res.status(201).send(result)
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }
    static async updateProduct(req : express.Request, res : express.Response) {
        if (typeof req.query.name === "string" && typeof req.query.amount === "string") {
            const result = await(
                BaseModel.updateProduct(req.query.name, req.query.amount, req.params.id)
            )
            res.status(200).send(result)
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }

}