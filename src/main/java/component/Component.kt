package component

import dagger.Component
import di.*
import org.glassfish.grizzly.http.server.HttpServer
import utils.consumer.ConsumerReadData


@Component(modules = [ConfigModule::class,HttpModule::class,MapperModule::class,ShipmentServiceModule::class,DatabaseModule::class,KafkaModule::class,Consumer::class])
interface Component {
    fun server():HttpServer
    fun shipmentConsumer():ConsumerReadData
}