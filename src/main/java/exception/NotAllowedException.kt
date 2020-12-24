package exception

class NotAllowedException:ShipmentException {
    constructor():super(){}
    constructor(msg:String?):super(msg){}
}