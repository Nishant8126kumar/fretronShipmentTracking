package di

import KAFKA_BROKER
import KAFKA_DESERIALIZER
import KAFKA_GROUP
import KAFKA_TOPIC
import dagger.Module
import dagger.Provides
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*
@Module
class Consumer {
    @Provides
    fun consumerProvide(): KafkaConsumer<String, String>
    {
        val properties= Properties()
        properties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG]=KAFKA_BROKER
        properties[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG]=KAFKA_DESERIALIZER
        properties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG]=StringDeserializer::class.java
        properties[ConsumerConfig.GROUP_ID_CONFIG]=KAFKA_GROUP
        val kafkaConsumer=KafkaConsumer<String,String>(properties)
        val list= arrayListOf<String>()
        list.add(KAFKA_TOPIC)
        kafkaConsumer.subscribe(list)
        return kafkaConsumer
    }
}