import express from "express"
import { Controller } from "../controller/controller"


export class Routes {
    app : express.Application
    name : string = "UserRoutes" 

    constructor(app : express.Application) {
        this.app = app
        this.configureRoutes()
    }

    configureRoutes() {
        const version : string = "apiv1"

        this.app.route('/')
            .get(async (req : express.Request, res : express.Response) => {
                res.send(`Successful connection to ${this.name}`)
            })
        
        this.app.route(`/${version}/customers`)
            .get(Controller.findAll)
            .post(
                // name / country / address1 / (address2) / (state/province) / (zipcode) 
                Controller.createCustomer
            )
        
        this.app.route(`/${version}/customers/:id`)
            .get(Controller.findOne)
            .put(
                // name / country / adress1 / (adress2) / (state/province) / (zipcode) 
                Controller.updateCustomer
            )
            .delete(Controller.deleteOne)
        
        this.app.route(`/${version}/employees`)
            .get(Controller.findAll)
            .post(
                // e.g. ?username=wolf?password=hunter2?firstname=Timo?lastname=Wolf?permissions=1
                Controller.createEmployee
            )
        
        this.app.route(`/${version}/employees/:id`)
            .get(Controller.findOne)
            .delete(Controller.deleteOne)
        
        this.app.route(`/${version}/products`)
            .get(Controller.findAll)
            .post(
                // e.g. ?ean=1234567890123?name=Superglue?amount=10
                Controller.createProduct
            )
        
        this.app.route(`/${version}/products/:ean`)
            .put(Controller.updateProduct)
            .delete
            
        this.app.route(`/${version}/orders`)
            .get(Controller.findAll)
            .post(
                // see order.ts
                Controller.createOrder
            )
        
        this.app.route(`/${version}/order_contents/:fkOrderID`)   
            .get(Controller.findOrderContents)

        return this.app
    }

    getName() {
        return this.name
    }

}