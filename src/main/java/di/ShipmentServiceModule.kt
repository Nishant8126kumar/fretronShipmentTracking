package di

import dagger.Module
import dagger.Provides
import repositories.ShipmentRepository
import services.ShipmentService
import javax.inject.Named


@Module
class ShipmentServiceModule {

    @Provides
    fun provideService(shipmentRepository: ShipmentRepository):ShipmentService
    {
        return ShipmentService(shipmentRepository)
    }
}