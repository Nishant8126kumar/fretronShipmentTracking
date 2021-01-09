package repositories

import com.mongodb.client.MongoDatabase
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Inject
import javax.inject.Named


class ActivityLogRepository @Inject constructor(private val objectMapper: ObjectMapper, @Named("mongoDatabase") private val mongoDatabase: MongoDatabase) {

    val mongoCollection=mongoDatabase.getCollection("activityLog")
    fun saveActivityLog(key:String,data:String)
    {
        val doc=org.bson.Document.parse(data)
        doc["uuid"] = key
        mongoCollection.insertOne(doc)
    }
}