package services

import exception.NotAllowedException
import exception.ShipmentException
import repositories.Shipment
import repositories.ShipmentRepository
import services.gateway.Kafka
import java.util.*
import javax.inject.Inject

class ShipmentService @Inject constructor(private val shipmentRepository: ShipmentRepository, private val kafka: Kafka) {

    @Throws(ShipmentException::class)
    fun createNewShipment(shipment: Shipment): Shipment {
        if (shipment.getShipmentNumber() == null || shipment.getPickupPlace() == null || shipment.getDeliveryPlace() == null) {
            throw NotAllowedException("require field not found")
        } else {
            shipment.setShipmentId(UUID.randomUUID().toString())
            shipment.setCreationTime(System.currentTimeMillis())
            val shipmentData= shipmentRepository.createNewShipment(shipment)
            kafka.produceShipment(shipmentData.getShipmentNumber().toString(), shipmentData.toString())
            return shipmentData
        }
    }

    fun getShipmentByShipmentNumber(shipmentNumber: String): Shipment {
        if (shipmentNumber.isEmpty()) {
            println("Shipment Id not found")
            throw Exception("Invalid Shipment Id")
        } else {
            val shipment = shipmentRepository.getShipmentByShipmentNumber(shipmentNumber)
            kafka.produceShipment(shipmentNumber, shipment.toString())
            return shipment
        }
    }

    @Throws(ShipmentException::class)
    fun updateShipment(shipmentNumber: String, shipment: Shipment) {
        if (shipmentNumber.isEmpty()) {
            throw ShipmentException("ShipmentNumber not found $shipmentNumber")
        } else {
            shipmentRepository.updateShipment(shipmentNumber, shipment)
            kafka.produceShipment(shipmentNumber, shipment.toString())
        }
    }

    fun deleteShipment(shipmentNumber: String): String {
        return shipmentRepository.deleteShipment(shipmentNumber)
    }

    fun countShipment(): String {
        return shipmentRepository.countShipment()
    }
}