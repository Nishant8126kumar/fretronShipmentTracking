package services.gateway

import KAFKA_TOPIC
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import repositories.Shipment
import javax.inject.Inject

class KafkaImpl @Inject constructor(private val shipmentProduce: KafkaProducer<String, String>) : Kafka {
    override fun produceShipment(key: String, shipment: String) {
        try {
            println("----ok Data----")
            shipmentProduce.send(ProducerRecord(KAFKA_TOPIC,key,shipment))
        }
        catch (e:Exception)
        {
            println("Some Exception Are occur "+e)
        }
    }
}