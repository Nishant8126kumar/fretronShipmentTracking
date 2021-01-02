package di

import dagger.Module
import dagger.Provides
import org.apache.kafka.clients.producer.KafkaProducer
import repositories.Shipment
import repositories.ShipmentRepository
import services.ShipmentService
import services.gateway.KafkaImpl
import javax.inject.Named


@Module
class ShipmentServiceModule {

    @Provides
    fun provideService(shipmentRepository: ShipmentRepository,kafka:KafkaImpl):ShipmentService
    {
        return ShipmentService(shipmentRepository,kafka)
    }
}