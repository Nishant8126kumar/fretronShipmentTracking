package repositories

import com.mongodb.BasicDBObject
import com.mongodb.client.MongoDatabase
import com.mongodb.util.JSON
import org.bson.Document
import org.codehaus.jackson.map.ObjectMapper
import javax.inject.Inject
import javax.inject.Named

class ShipmentRepository @Inject constructor(private val objectMapper: ObjectMapper, @Named("mongoDatabase") private val mongoDatabase: MongoDatabase) {

    private val mongoCollection = mongoDatabase.getCollection("fretronShipment")

    @Throws(java.lang.Exception::class)
    fun createNewShipment(shipment: Shipment): Shipment {
        val doc = Document.parse(shipment.toString())
        if (doc == null) {
            throw Exception("Shipment Not Created")
        } else {
            mongoCollection.insertOne(doc)
            return shipment
        }
    }


    @Throws(Exception::class)
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
            throw Exception("No record found for this shipment Number $shipmentNumber")
        }
    }


    @Throws(Exception::class)
    fun updateShipment(shipmentNumber: String, shipment: Shipment): Shipment {
        val doc= Document.parse(shipment.toString()) ?: throw java.lang.Exception("Record not updated")
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