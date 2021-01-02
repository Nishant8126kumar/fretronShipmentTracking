package utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer
import repositories.Shipment

class KafkaDeserilizer : Deserializer<Any> {
    private val objectMapper = ObjectMapper()
    override fun deserialize(topic: String?, data: ByteArray?): Any? {
        try {
            val shipment = return objectMapper.readValue(data, Shipment::class.java)
            return shipment as Any
        } catch (e: Exception) {
        }
        return null
    }
}


//private val objectMapper= ObjectMapper()
//override fun deserialize(topic: String?, data: ByteArray?): Any? {
//    try {
//        val record=objectMapper.readValue(data, User::class.java)
//        return record as Any
//    } catch (e: Exception) {
//        println("Exception are occured=:${e.message}")
//    }
//    return null
//}