package di

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ConfigModule {

    private val hostName= "http://localhost"
    private val port=9092

    @Provides
    @Named("hostName")
    fun provideHostName():String
    {
        return hostName
    }

    @Provides
    @Named("portName")
    fun providePort():Int
    {
        return port
    }
}