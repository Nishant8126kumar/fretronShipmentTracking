package utils

import org.apache.kafka.common.serialization.Serializer
import org.codehaus.jackson.map.ObjectMapper
import repositories.Shipment


class KafkaSerializer :Serializer<Shipment> {
    private val objectMapper=ObjectMapper()
    override fun serialize(topic: String?, data: Shipment?): ByteArray? {
       return objectMapper.writeValueAsString(data).toByteArray()
    }
}

//class CustomSerialize : Serializer<User> {
//
//    private val objectMapper= ObjectMapper()
//    override fun serialize(topic: String?, data: User?): ByteArray {
//        return objectMapper.writeValueAsString(data).toByteArray()
//    }
//}
