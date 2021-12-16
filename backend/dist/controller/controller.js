"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.BaseController = void 0;
const model_1 = require("../model/model");
class BaseController {
    static async findAll(req, res) {
        // Regex should match last hierarchical element of endpoint
        // e.g. /apiv1/abc/customers/ => customers
        const matched = req.path.match(/([\w]+)(?=$)/g);
        let result;
        if (matched) {
            result = await model_1.BaseModel.findAll(matched[0]);
        }
        else {
            result = "An error occured on database access.";
        }
        res.send(result);
    }
}
exports.BaseController = BaseController;
