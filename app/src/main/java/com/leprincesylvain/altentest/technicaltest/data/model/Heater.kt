package com.leprincesylvain.altentest.technicaltest.data.model

class Heater {
    var id =0
    var devicename = "light"
    var mode = false
    var temperature = 0
    val productType = ProductType.HEATER

    constructor(id: Int, devicename: String, mode: Boolean, temperature: Int) {
        this.id = id
        this.devicename = devicename
        this.mode = mode
        this.temperature = temperature
    }
}