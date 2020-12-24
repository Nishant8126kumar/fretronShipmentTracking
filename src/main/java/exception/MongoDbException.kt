package exception

class MongoDbException :ShipmentException{
    constructor():super(){}
    constructor(msg:String?):super(msg){}
}