import express from "express"
import { BaseController } from "../controller/controller"


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
            .get(BaseController.findAll)
        
        this.app.route(`/${version}/employees`)
            .get(BaseController.findAll)
            .post(
                // e.g. ?username=wolf?password=hunter2?firstname=Timo?lastname=Wolf?permissions=1
                BaseController.createEmployee
            )
        
        this.app.route(`/${version}/products`)
            .get(BaseController.findAll)
            .post(
                // e.g. ?ean=1234567890123?name=Superglue?amount=10
                BaseController.createProduct
            )
        
        this.app.route(`/${version}/products/:id`)
            .put(BaseController.updateProduct)
            
        this.app.route(`/${version}/orders`)
            .get(BaseController.findAll)
        
        this.app.route(`/${version}/order_contents/:fkOrderID`)   
            .get()

        return this.app
    }

    getName() {
        return this.name
    }

}