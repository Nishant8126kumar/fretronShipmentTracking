package di

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabaseConfig():com.mongodb.MongoClient
    {
        return com.mongodb.MongoClient("localhost",27017)
    }

    @Provides
    fun provideDatabase(mongoClient: MongoClient):MongoDatabase
    {
        return mongoClient.getDatabase("ShipmentTracking")
    }
}