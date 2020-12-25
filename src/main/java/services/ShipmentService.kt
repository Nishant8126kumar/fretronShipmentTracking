package services

import exception.NotAllowedException
import exception.ShipmentException
import repositories.Shipment
import repositories.ShipmentRepository
import java.lang.Exception
import java.util.*
import javax.inject.Inject
import kotlin.jvm.Throws

class ShipmentService @Inject constructor(private val shipmentRepository: ShipmentRepository) {

    @Throws(ShipmentException::class)
    fun createNewShipment(shipment: Shipment):Shipment
    {
        if (shipment.getShipmentNumber()==null || shipment.getPickupPlace()==null || shipment.getDeliveryPlace()==null)
        {
            throw NotAllowedException("require field not found")
        }
        else{
            shipment.setShipmentId(UUID.randomUUID().toString())
            shipment.setCreationTime(System.currentTimeMillis())
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

    @Throws(ShipmentException::class)
    fun  updateShipment(shipmentNumber:String,shipment: Shipment)
    {
        if (shipmentNumber.isEmpty())
        {
            throw ShipmentException("ShipmentNumber not found $shipmentNumber")
        }
        else {
            shipmentRepository.updateShipment(shipmentNumber, shipment)
        }
    }

    fun deleteShipment(shipmentNumber: String):String
    {
       return shipmentRepository.deleteShipment(shipmentNumber)
    }

    fun countShipment():String
    {
        return shipmentRepository.countShipment()
    }
}