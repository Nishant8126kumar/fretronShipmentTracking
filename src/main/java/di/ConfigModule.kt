package di

import dagger.Module
import dagger.Provides
import org.apache.kafka.clients.producer.KafkaProducer
import repositories.Shipment
import services.gateway.Kafka
import services.gateway.KafkaImpl
import javax.inject.Named

@Module
class ConfigModule {

    private val hostName= "http://localhost"
    private val port=9099

    @Provides
    @Named("hostName")
    fun provideHostName():String
    {
        return hostName
    }

    @Provides
    @Named("portName")
    fun providePort():Int
    {
        return port
    }
    @Provides
    fun kafka(kafkaProducer: KafkaProducer<String, String>):Kafka
    {
        return KafkaImpl(kafkaProducer)
    }
}