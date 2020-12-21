package di

import dagger.Module
import dagger.Provides
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Named


@Module
class MapperModule {

    @Provides
    fun provideObjectMapper():ObjectMapper
    {
        return ObjectMapper()
    }

}