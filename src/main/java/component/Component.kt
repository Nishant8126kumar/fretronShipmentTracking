package component

import dagger.Component
import di.*
import org.glassfish.grizzly.http.server.HttpServer


@Component(modules = [ConfigModule::class,HttpModule::class,MapperModule::class,ShipmentServiceModule::class,ShipmentRepositoyModule::class,DatabaseModule::class])
interface Component {
    fun server():HttpServer
}