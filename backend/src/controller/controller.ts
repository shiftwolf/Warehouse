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
        } else {
            result = "An error occured on database access."
        }
        res.send(result)
    }

}