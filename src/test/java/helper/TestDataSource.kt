package helper

import org.codehaus.jackson.map.ObjectMapper
import repositories.Shipment

class TestDataSource {
    private val mapper=ObjectMapper()
    fun getShipment():Shipment
    {
        val shipmentData="{\n"+
            "    \"shipmentId\" : \"BKSY1563878\",\n" +
            "    \"pickupPlace\" : \"Noida\",\n" +
            "    \"deliveryPlace\" : \"Aligahr\",\n" +
            "    \"creationTime\" : \"1608523720\",\n" +
            "    \"shipmentNumber\" : \"1567289\",\n" +
            "    \"transportationMode\" : \"ByRoad\",\n" +
            "    \"shipmentTrackingStatus\" : \"Enroute For Pickup\",\n" +
            "    \"edd\" : \"16098523720\",\n" +
            "    \"completionTime\" : \"1690523720\",\n" +
            "    \"externalShipmentId\" : \"null\",\n" +
            "    \"shipmentType\" : \"null\"\n" +
            "}"
        val shipment2="{\n" +
                "    \"shipmentId\" : \"BKSY1563878\",\n" +
                "    \"pickupPlace\" : \"Noida\",\n" +
                "    \"deliveryPlace\" : \"Aligahr\",\n" +
                "    \"creationTime\" : 1608523720.0,\n" +
                "    \"shipmentNumber\" : \"1567289\",\n" +
                "    \"transportationMode\" : \"ByRoad\",\n" +
                "    \"shipmentTrackingStatus\" : \"Enroute For Pickup\",\n" +
                "    \"edd\" : \"16098523720\",\n" +
                "    \"completionTime\" : 1690523720.0,\n" +
                "    \"externalShipmentId\" : \"null\",\n" +
                "    \"shipmentType\" : \"null\"\n" +
                "}"
        return mapper.readValue(shipment2, Shipment::class.java)
    }
}