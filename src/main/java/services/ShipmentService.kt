package services

import repositories.Shipment
import repositories.ShipmentRepository
import java.lang.Exception
import javax.inject.Inject

class ShipmentService @Inject constructor(private val shipmentRepository: ShipmentRepository) {

    fun createNewShipment(shipment: Shipment):Shipment
    {
        if (shipment.getShipmentId()==null || shipment.getCreationTime()==null || shipment.getPickupPlace()==null || shipment.getDeliveryPlace()==null)
        {
            throw Exception("require field not found")
        }
        else{
            return shipmentRepository.createNewShipment(shipment)
        }
    }

    fun getShipmentByShipmentNumber(shipmentNumber:String):Shipment
    {
        if (shipmentNumber.isEmpty())
        {
            println("Shipment Id not found")
            throw Exception("Invalid Shipment Id")
        }
        else{
           return shipmentRepository.getShipmentByShipmentNumber(shipmentNumber)
        }
    }

    fun  updateShipment(shipmentId:String,shipment: Shipment)
    {
        shipmentRepository.updateShipment(shipmentId,shipment)
    }

    fun deleteShipment(shipmentId: String)
    {
        shipmentRepository.deleteShipment(shipmentId)
    }

    fun countShipment():String
    {
        return shipmentRepository.countShipment()
    }
}