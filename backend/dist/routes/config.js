"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Routes = void 0;
const controller_1 = require("../controller/controller");
class Routes {
    constructor(app) {
        this.name = "UserRoutes";
        this.app = app;
        this.configureRoutes();
    }
    configureRoutes() {
        const version = "apiv1";
        this.app.route('/')
            .get(async (req, res) => {
            res.send(`Successful connection to ${this.name}`);
        });
        this.app.route(`/${version}/customers`)
            .get(controller_1.BaseController.findAll);
        this.app.route(`/${version}/employees`)
            .get(controller_1.BaseController.findAll);
        this.app.route(`/${version}/products`)
            .get(controller_1.BaseController.findAll);
        this.app.route(`/${version}/orders`)
            .get(controller_1.BaseController.findAll);
        this.app.route(`/${version}/order_contents/:fkOrderID`)
            .get();
        return this.app;
    }
    getName() {
        return this.name;
    }
}
exports.Routes = Routes;
