package resources

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import component.DaggerTestComponet
import component.TestComponet
import helper.TestDataSource
import org.codehaus.jettison.json.JSONObject
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import services.ShipmentService
import java.util.*
import javax.ws.rs.client.Entity
import javax.ws.rs.core.Application
import javax.ws.rs.core.MediaType

class ShipmentResourceShould :JerseyTest() {

    private val baseUrl="/apis.fretron.com/v1"
    lateinit var testComponet: TestComponet
    private val testDataSource=TestDataSource()
    lateinit var shipmentNumber:String
    override fun configure(): Application {
        val config=ResourceConfig()
        testComponet=DaggerTestComponet.builder().build()
        val shipmentResource=ShipmentResource(testComponet.shipmentService(),testComponet.mapper())
        config.register(shipmentResource)
        return config.application
    }
    @Test
    fun return_200status_after_testCreateNewShipment()
    {
        val data=testDataSource.getShipment()
        val response=target("$baseUrl/shipment").request().post(Entity.entity(data.toString(),MediaType.APPLICATION_JSON))
        assert(response.status==200)
        val record=response.readEntity(String::class.java)
        shipmentNumber=JSONObject(record).get("shipmentNumber").toString()
        Assert.assertNotNull(shipmentNumber)
        println("data=:$shipmentNumber")
    }
    @Test
    fun return_200status_after_testGetShipmentByShipmentNumber()
    {
        return_200status_after_testCreateNewShipment()
        val response=target("$baseUrl/shipment/$shipmentNumber").request().get()
        assert(response.status==200)
    }

    @Test
    fun return_200status_after_testUpdateShipment()
    {
        return_200status_after_testCreateNewShipment()
        val data=testDataSource.getShipment()
        val response=target("$baseUrl/shipment/update/$shipmentNumber").request().put(Entity.entity(data.toString(),MediaType.APPLICATION_JSON))
        assert(response.status==200)
        println("response=:$response")
        val record=response.readEntity(String::class.java)
        shipmentNumber=JSONObject(record).get("shipmentNumber").toString()
       Assert.assertNotNull(shipmentNumber)
    }

    @Test
    fun return_200status_after_testDeleteShipment()
    {
        return_200status_after_testCreateNewShipment()
        val response=target("$baseUrl/shipment/$shipmentNumber").request().delete()
        assert(response.status==200)
    }

    @Test
    fun return_200status_after_testCountShipment()
    {
        val a=true
        val response=target("$baseUrl/shipment").queryParam("count",a).request().get()
        println("data=:$response")
        assert(response.status==200)
    }
}