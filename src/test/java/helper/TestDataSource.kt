package helper

import org.codehaus.jackson.map.ObjectMapper
import repositories.Shipment

class TestDataSource {
    private val mapper=ObjectMapper()
    fun getShipment(): Shipment
    {
        val shipment3="{\n" +
                "    \"shipmentId\" : \"a694dc04-5bef-4620-af69-b639bd2b3184\",\n" +
                "    \"pickupPlace\" : \"Noida\",\n" +
                "    \"deliveryPlace\" : \"Aligarh\",\n" +
                "    \"creationTime\" :1608568710770,\n" +
                "    \"shipmentNumber\" : \"1567289\",\n" +
                "    \"transportationMode\" : \"ByRoad\",\n" +
                "    \"shipmentTrackingStatus\" : \"Enroute For Pickup\",\n" +
                "    \"edd\" :16098523720,\n" +
                "    \"completionTime\" : 1690523720,\n" +
                "    \"externalShipmentId\" : \"null\",\n" +
                "    \"shipmentType\" : \"null\"\n" +
                "}"
        return mapper.readValue(shipment3, Shipment::class.java)
    }
}