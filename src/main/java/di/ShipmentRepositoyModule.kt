package di

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import repositories.ShipmentRepository
import javax.inject.Named


@Module
class ShipmentRepositoyModule {

    @Provides
    fun provideShipmentRepository(objectMapper: ObjectMapper):ShipmentRepository
    {
        return ShipmentRepository(objectMapper)
    }
}