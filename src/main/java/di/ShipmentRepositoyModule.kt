package di

import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import repositories.ShipmentRepository
import javax.inject.Named


//@Module
class ShipmentRepositoyModule {

//    @Provides
//    fun provideShipmentRepository(objectMapper: ObjectMapper,@Named("mongoDatabase") mongoDatabase: MongoDatabase):ShipmentRepository
//    {
//        return ShipmentRepository(objectMapper,mongoDatabase)
//    }
}