package component

import dagger.Component
import di.*
import org.codehaus.jackson.map.ObjectMapper
import repositories.ShipmentRepository
import services.ShipmentService

@Component(modules = [ShipmentResourceTest::class, ShipmentServiceModule::class,ShipmentServiceModule::class,MapperModule::class, DatabaseModule::class,KafkaModule::class])
interface TestComponet {
    fun shipmentResourceTest():ShipmentResourceTest
    fun  shipmentService():ShipmentService
    fun shipmentRepository():ShipmentRepository
    fun mapper(): ObjectMapper
}