#!/usr/bin/env node
import express from "express"
import { Routes } from "./routes/config"

const app = express()
app.use(express.json())

app.use(express.urlencoded({extended: true}));

new Routes(app)


app.listen(3000, '192.168.178.25');
