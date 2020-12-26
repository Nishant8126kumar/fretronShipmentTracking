package repositories

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import helper.TestDataSource
import org.codehaus.jackson.map.ObjectMapper
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import utils.EmbeddedMongoDb
import java.util.*
import javax.ws.rs.core.MultivaluedHashMap


class ShipmentRepositoryShould {
    lateinit var shipmentRepository: ShipmentRepository
    private val testDataSource = TestDataSource()
    lateinit var embeddedMongoDb: EmbeddedMongoDb
    private lateinit var mongoDatabase: MongoDatabase
    private val objectMapper = ObjectMapper()
    lateinit var shipmentNumber:String

    @Before
    fun configure() {
        startMongoDb()
        val mongoClient = MongoClient("localhost",embeddedMongoDb.port )
        mongoDatabase=mongoClient.getDatabase("ShipmentTracking")
        shipmentRepository=ShipmentRepository(objectMapper,mongoDatabase)
    }

    private fun startMongoDb() {
        val rand = Random()
        val n = rand.nextInt(99) + 9900
        embeddedMongoDb = EmbeddedMongoDb(n)
        embeddedMongoDb.start()
    }

    @Test
    fun testCreateNewShipment()
    {
        val response=shipmentRepository.createNewShipment(testDataSource.getShipment())
        println("data=:$response")
        Assert.assertNotNull(response.getShipmentId())
        Assert.assertNotNull(response.getPickupPlace())
        Assert.assertNotNull(response.getShipmentNumber())
        shipmentNumber=response.getShipmentNumber().toString()
    }

    @Test
    fun testGetShipmentByShipmentNumber()
    {
         testCreateNewShipment()
         val response=shipmentRepository.getShipmentByShipmentNumber(shipmentNumber)
         Assert.assertNotNull(response.getShipmentId())
         Assert.assertNotNull(response.getPickupPlace())
         Assert.assertNotNull(response.getShipmentNumber())
         println("Second Data=:$response")
    }

    @Test
    fun testUpdateShipment()
    {
        testCreateNewShipment()
        val shipment=testDataSource.getShipment()
        val response=shipmentRepository.updateShipment(shipmentNumber,shipment)
        Assert.assertNotNull(response)
    }

    @Test
    fun testDeleteShipment()
    {
        testCreateNewShipment()
        val response=shipmentRepository.deleteShipment(shipmentNumber)
        println("delete=:$response")
        Assert.assertNotNull(response)
    }

    @Test
    fun testShipmentCount()
    {
        testCreateNewShipment()
        val response=shipmentRepository.countShipment()
        println("Count=:$response")
        Assert.assertNotNull(response)
    }

    @After
    fun closeConnection()
    {
        embeddedMongoDb.stop()
    }

}
