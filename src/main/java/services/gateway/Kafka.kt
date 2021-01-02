package services.gateway

import repositories.Shipment

interface Kafka {
    fun produceShipment(key:String,shipment: String)
}