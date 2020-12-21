package repositories

import com.mongodb.client.MongoDatabase
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Inject
import javax.inject.Named

class ShipmentRepository @Inject constructor(private val objectMapper: ObjectMapper) {

    fun getShipmentByShipmentNumber(shipmentNumber:String):Shipment
    {
        return Shipment()
    }

    fun createNewShipment(shipment:Shipment):Shipment
    {
        val shipment=Shipment()
        return shipment
    }

    fun  updateShipment(shipmentId:String,shipment:Shipment):Shipment
    {
        return Shipment()
    }

    fun deleteShipment(shipmentId: String):String
    {
        return "deleted"

    }

    fun countShipment():String
    {
        return "count"
    }
}