package di

import KAFKA_BROKER
import KAFKA_SERIALIZER
import dagger.Module
import dagger.Provides
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import repositories.Shipment
import utils.KafkaSerializer
import java.util.*


//io.confluent.kafka.serializers.KafkaAvroSerializer
@Module
class KafkaModule {
    @Provides
    fun producerProvide(): KafkaProducer<String, String>
    {
        val properties= Properties()
        properties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG]=KAFKA_BROKER
        properties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG]=KAFKA_SERIALIZER
        properties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG]=StringSerializer::class.java
        return KafkaProducer<String,String>(properties)
    }
}