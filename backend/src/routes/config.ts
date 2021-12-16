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
        
        this.app.route(`/${version}/products`)
            .get(BaseController.findAll)
            
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