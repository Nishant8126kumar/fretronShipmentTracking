package di

import dagger.Module
import dagger.Provides


@Module
class ShipmentResourceTest {

    @Provides
    fun provideShipmentResourceTest():ShipmentResourceTest
    {
        return ShipmentResourceTest()
    }
}