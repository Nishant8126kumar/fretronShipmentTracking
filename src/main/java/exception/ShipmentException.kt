package exception

open class ShipmentException :Exception {
    constructor():super(){}
    constructor(msg:String?):super(msg){}
    constructor(msg: String,e:Exception?){}
}

//package com.fretron.vehicleManager.exceptions
//
//open class FretronException : Exception {
//    constructor() : super() {}
//    constructor(msg: String?) : super(msg) {}
//    constructor(msg: String?, e: Exception?) {}
//}