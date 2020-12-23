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

//lateinit var classunderTest: UserManagerRepository
//private val testDataSource = TestDataSource()
//lateinit var embeddedMongoDb: EmbeddedMongoDb
//private lateinit var mongoDatabase: MongoDatabase
//private val objectMapper= ObjectMapper()
//private val uuid="07b06f91-284f-42ff-b33b-48c129fc7b3d"
//
//
//
//@Before
//fun configure() {
//
//    startMongoDb()
//    val mongoClient = MongoClient("localhost", embeddedMongoDb.port)
//    mongoDatabase = mongoClient.getDatabase("EmployeeDetails")
//    classunderTest= UserManagerRepository(mongoDatabase,objectMapper)
//}
//
//@After
//fun closeConnection()
//{
//    embeddedMongoDb.stop()
//}
//
//private fun startMongoDb()
//{
//    val rand= Random()
//    val n=rand.nextInt(99)+9900
//    embeddedMongoDb = EmbeddedMongoDb(n)
//    embeddedMongoDb.start()
//}
//
//@Test
//fun testCreateNewUser()
//{
//    val user=testDataSource.getUser()
//    val userFromDb: User = classunderTest.createNewUser(user)
//    println("user From =:$userFromDb")
//    Assert.assertNotNull(userFromDb.getName())
//    Assert.assertNotNull(user.getEmail())
//    Assert.assertNotNull(user.getContact())
//}
//
//@Test
//fun testGetUserRecordByuuid() {
//    val fakeUser=testDataSource.getUser()
//    val userFromDb: User = classunderTest.createNewUser(fakeUser)
//    val user=classunderTest.getUserRecordByuuid(userFromDb.getUuid().toString())
//    println("responce= :$user")
//}
//
//@Test
//fun testDeleteUserRecordByuuid()
//{
//
//    val response=classunderTest.deleteUserRecordByuuid(uuid)
//    println("Response=:$response")
//    Assert.assertNotNull(response)
//
//}
//
//@Test
//fun testUpdateUserData()
//{
//    val user=testDataSource.getUser()
//    classunderTest.updateUserData(uuid,user)
//    Assert.assertNotNull(user.getName())
//    Assert.assertNotNull((user.getEmail()))
//    Assert.assertNotNull(user.getContact())
//}
