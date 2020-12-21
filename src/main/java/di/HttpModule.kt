package di

import dagger.Module
import dagger.Provides
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig
import resources.ShipmentResource
import javax.inject.Named
import javax.ws.rs.core.UriBuilder

@Module
class HttpModule {

    @Provides
    fun provideResourceConfig():ResourceConfig
    {
        return ResourceConfig().register(ShipmentResource::class.java)
    }

    @Provides
    fun provideServerConfig(resourceConfig: ResourceConfig,@Named("hostName") hostName:String,@Named("portName") portName:Int):HttpServer
    {
        val uri=UriBuilder.fromUri(hostName).port(portName).build()
        return GrizzlyHttpServerFactory.createHttpServer(uri,resourceConfig)
    }

}