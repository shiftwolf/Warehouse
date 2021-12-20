import { Model } from "../model/model";
import { Order } from "../model/order";
import { Customer } from "../model/customer"
import express from "express"

export abstract class Controller {

    static errMessage : string = "Request could not be matched."

    static pathToTable(path : any, hasParams : boolean) : RegExpMatchArray | null {
        // Regex should match last hierarchical element of endpoint or null
        let match;
        if (hasParams) {
            // e.g. "/apiv1/abc/customers/:id" => "customers"
            match = path.match(/([\w]+)(?=\/[\w]+$)/g)
        } else {
            // e.g. "/apiv1/abc/customers/" => "customers"
            match = path.match(/([\w]+)(?=$)/g)
        }
        if(match === undefined) {
            return null
        } 
        return match
    }

    static async findAll(req : express.Request, res : express.Response) {
        const matched : RegExpMatchArray | null = Controller.pathToTable(req.path, false)
        if (typeof req.query.id === "string") {
            Controller.findOne(req, res)
        } else if (matched) {
            const result = await Model.findAll(matched[0])
            res.status(200).send(result)
        } else {
            res.status(400).send(Controller.errMessage)
        }
    }
    static async findOne(req : express.Request, res : express.Response) {
        const matched : RegExpMatchArray | null = Controller.pathToTable(req.path, true)
        const id = req.query.id ? req.query.id : req.params.id
        if (matched && typeof id === "string") {
            const result = await Model.findOne(matched[0], id)
            res.status(200).send(result)
        } else {
            res.status(400).send(Controller.errMessage)
        }
    }

    static async deleteOne(req : express.Request, res : express.Response) {
        const matched : RegExpMatchArray | null = Controller.pathToTable(req.path, true)
        const id = req.query.id ? req.query.id : req.params.id
        if (matched && typeof id === "string") {
            if (matched[0] !== "orders") {
                const result = await Model.deleteOne(matched[0], id,
                     matched[0] === "products" ? "ean" : undefined)
                res.status(200).send(result)
            } else {
                const result = await Model.deleteOrder(id)
                res.status(200).send(result)
            }
        } else {
            res.status(400).send(Controller.errMessage)
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
            res.status(400).send(Controller.errMessage)
        }
    }
    static async createProduct(req : express.Request, res : express.Response) {
        if (typeof req.query.ean === "string" && typeof req.query.name === "string"
        && typeof req.query.amount === "string") {
            const result = await(Model.createProduct(req.query.ean, req.query.name, req.query.amount))
            res.status(201).send(result)
        } else {
            res.status(400).send(Controller.errMessage)
        }
    }
    static async updateProduct(req : express.Request, res : express.Response) {
        if (typeof req.query.name === "string" && typeof req.query.amount === "string") {
            const result = await
                Model.updateProduct(req.query.name, req.query.amount, req.params.ean)
            res.status(200).send(result)
        } else {
            res.status(400).send(Controller.errMessage)
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
            res.status(400).send(Controller.errMessage)
        }
    }
    static async updateCustomer(req : express.Request, res : express.Response) {
        if (typeof req.query.name === "string" && typeof req.query.country === "string"
        && typeof req.query.address1 === "string" && typeof req.params.id === "string") {
            // Note that some values are optional, therefore can be "NULL"
            const customer = new Customer (
                req.query.name, req.query.country, req.query.address1,
                req.query.address2 === "string" ? req.query.address2 : "NULL",
                req.query.state === "string" ? req.query.state : "NULL",
                req.query.zipcode === "string" ? req.query.zipcode : "NULL"
            )
            const result = await Model.updateCustomer(customer, req.params.id)
            res.status(200).send(result)
        } else {
            res.status(400).send(Controller.errMessage)
        }
    }
    static async findOrderContents(req : express.Request, res : express.Response) {
        if (typeof req.params.fkOrderID === "string") {
            const result = await Model.findOrderContents(req.params.fkOrderID)
            res.status(200).send(result)
        } else {
            res.status(400).send(Controller.errMessage)
        }
    }
    static async createOrder(req : express.Request, res : express.Response) {
        if (typeof req.body === "object" && req.body !== null) {
            const result = await Model.createOrder(req.body)
            res.status(201).send(result)
        } else {
            res.status(400).send(Controller.errMessage)
        }
    }
}
