import { Model } from "../model/model";
import { Customer } from "../model/customer"
import express from "express"

export abstract class Controller {

    static pathToTable(path : any, hasParams : boolean) : RegExpMatchArray | null {
        // Regex should match last hierarchical element of endpoint or null
        // e.g. "/apiv1/abc/customers/" => "customers"
        let match;
        if (hasParams) {
            match = path.match(/([\w]+)(?=\/[\w]+$)/g)
        } else {
            match = path.match(/([\w]+)(?=$)/g)
        }
        if(match === undefined) {
            return null
        } 
        return match
    }

    static async findAll(req : express.Request, res : express.Response) {
        const matched : RegExpMatchArray | null = Controller.pathToTable(req.path, false)
        let result : string;
        if (typeof req.query.id === "string") {
            Controller.findOne(req, res)
        } else if (matched) {
            result = await Model.findAll(matched[0])
            res.status(200).send(result)
        } else {
            result = "An error occured on database access."
            res.status(400).send(result)
        }
    }
    static async findOne(req : express.Request, res : express.Response) {
        const matched : RegExpMatchArray | null = Controller.pathToTable(req.path, true)
        let result : string;
        const id = req.query.id ? req.query.id : req.params.id
        if (matched && typeof id === "string") {
            result = await Model.findOne(matched[0], id)
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
            const result = await Model.createEmployee(
                req.query.username, req.query.password, req.query.firstname, req.query.lastname,
                req.query.permissions
                )
            res.status(201).send(result)
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }
    static async createProduct(req : express.Request, res : express.Response) {
        if (typeof req.query.ean === "string" && typeof req.query.name === "string"
        && typeof req.query.amount === "string") {
            const result = await(Model.createProduct(req.query.ean, req.query.name, req.query.amount))
            res.status(201).send(result)
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }
    static async updateProduct(req : express.Request, res : express.Response) {
        if (typeof req.query.name === "string" && typeof req.query.amount === "string") {
            const result = await
                Model.updateProduct(req.query.name, req.query.amount, req.params.ean)
            res.status(200).send(result)
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }
    static async createCustomer(req : express.Request, res : express.Response) {
        if (typeof req.query.name === "string" && typeof req.query.country === "string"
        && typeof req.query.adress1 === "string") {
            // Note that some values are optional, therefore can be "NULL"
            const customer = new Customer (
                req.query.name, req.query.country, req.query.adress1,
                req.query.adress2 === "string" ? req.query.adress2 : "NULL",
                req.query.state === "string" ? req.query.state : "NULL",
                req.query.zipcode === "string" ? req.query.zipcode : "NULL"
            )
            const result = await Model.createCustomer(customer)
            res.status(201).send(result)
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }
    static async updateCustomer(req : express.Request, res : express.Response) {
        if (typeof req.query.name === "string" && typeof req.query.country === "string"
        && typeof req.query.adress1 === "string" && typeof req.params.id === "string") {
            // Note that some values are optional, therefore can be "NULL"
            const customer = new Customer (
                req.query.name, req.query.country, req.query.adress1,
                req.query.adress2 === "string" ? req.query.adress2 : "NULL",
                req.query.state === "string" ? req.query.state : "NULL",
                req.query.zipcode === "string" ? req.query.zipcode : "NULL"
            )
            const result = await Model.updateCustomer(customer, req.params.id)
            res.status(200).send(result)
        } else {
            res.status(400).send("Incorrect formatting.")
        }
    }
}
