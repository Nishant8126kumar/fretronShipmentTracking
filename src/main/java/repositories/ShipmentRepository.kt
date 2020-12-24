package repositories

import com.mongodb.BasicDBObject
import com.mongodb.client.MongoDatabase
import com.mongodb.util.JSON
import exception.MongoDbException
import org.bson.Document
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Inject
import javax.inject.Named

class ShipmentRepository @Inject constructor(private val objectMapper: ObjectMapper, @Named("mongoDatabase") private val mongoDatabase: MongoDatabase) {

    private val mongoCollection = mongoDatabase.getCollection("fretronShipment")

    @Throws(MongoDbException::class)
    fun createNewShipment(shipment: Shipment): Shipment {
        val doc = Document.parse(shipment.toString())
        if (doc == null) {
            throw MongoDbException("Shipment Not Created")
        } else {
            mongoCollection.insertOne(doc)
            return shipment
        }
    }


    @Throws(MongoDbException::class)
    fun getShipmentByShipmentNumber(shipmentNumber: String): Shipment {
        val basicDBObject = BasicDBObject()
        basicDBObject["shipmentNumber"] = shipmentNumber
        val mongoCursor = mongoCollection.find(basicDBObject).iterator()
        if (mongoCursor.hasNext()) {
            println("reached")
            val doc = mongoCursor.next()
            doc.remove("_id")
            val json = JSON.serialize(doc)
            val data=objectMapper.readValue(json, Shipment::class.java)
            println("data ok=:$data")
            return data
        } else {
            throw MongoDbException("No record found on this shipment Number $shipmentNumber")
        }
    }


    @Throws(MongoDbException::class)
    fun updateShipment(shipmentNumber: String, shipment: Shipment): Shipment {
        val doc= Document.parse(shipment.toString()) ?: throw MongoDbException("Record not updated")
        doc["shipmentNumber"] = shipmentNumber
        doc["creationTime"]=System.currentTimeMillis()
        val basicDBObject=BasicDBObject()
        basicDBObject["shipmentNumber"] = shipmentNumber
        val update=Document("\$set",doc)
        mongoCollection.findOneAndUpdate(basicDBObject,update)
        return shipment
    }

    fun deleteShipment(shipmentNumber: String): String {
        val basicDBObject=BasicDBObject()
        basicDBObject["shipmentNumber"] = shipmentNumber
        mongoCollection.deleteMany(basicDBObject)
        return shipmentNumber
    }

    fun countShipment(): String {
        val count= mongoCollection.countDocuments()
        return count.toString()
    }
}