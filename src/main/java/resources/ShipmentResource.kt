package resources

import org.codehaus.jackson.map.ObjectMapper
import repositories.Shipment
import services.ShipmentService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
@Path("/apis.fretron.com/v1")
class ShipmentResource @Inject constructor(private val shipmentService: ShipmentService,private val objectMapper: ObjectMapper) {

    @GET
    @Path("/shipment/{shipmentNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getShipmentByShipmentNumber(@PathParam("shipmentNumber") shipmentNumber: String): Response {
        println("ok $shipmentNumber")
        shipmentService.getShipmentByShipmentNumber(shipmentNumber)
        return Response.ok("Welcome").build()
    }

    @POST
    @Path("/shipment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createNewShipment(request: String): Response {
        val shipment=objectMapper.readValue(request, Shipment::class.java)
        val record=shipmentService.createNewShipment(shipment)
        return Response.ok(shipment.toString()).build()
    }

    @PUT
    @Path("/shipment/update/{shipmentNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateShipment(@PathParam("shipmentNumber") shipmentId: String,request: String): Response {

        val shipment=objectMapper.readValue(request,Shipment::class.java)
        val record=shipmentService.updateShipment(shipmentId,shipment)
        return Response.ok(shipment.toString()).build()
    }

    @DELETE
    @Path("/shipment/{shipmentNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteShipment(@PathParam("shipmentNumber") shipmentNumber: String): Response {

        val record=shipmentService.deleteShipment(shipmentNumber)
        return Response.ok("Welcome").build()
    }

    @GET
    @Path("/shipment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun countShipment(@QueryParam("count") count: Boolean): String? {
        var data="count"
        return if (count)
        {
            val count=shipmentService.countShipment()
            Response.ok(data).toString()
        }
        else {
          null
        }
    }
}