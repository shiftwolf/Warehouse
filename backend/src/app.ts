#!/usr/bin/env node
import express from "express"
import { Routes } from "./routes/config"

const app = express()

new Routes(app)

app.use(express.json())

app.listen(3000, );
