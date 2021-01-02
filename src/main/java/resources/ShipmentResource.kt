package resources

import org.codehaus.jackson.map.ObjectMapper
import repositories.Shipment
import services.ShipmentService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
@Path("/fretron/v1")
class ShipmentResource @Inject constructor(private val shipmentService: ShipmentService,private val objectMapper: ObjectMapper) {

    @GET
    @Path("/shipment/{shipmentNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getShipmentByShipmentNumber(@PathParam("shipmentNumber") shipmentNumber: String): Response {
        println("data $shipmentNumber")
        val record=shipmentService.getShipmentByShipmentNumber(shipmentNumber)
        return Response.ok(record.toString()).build()
    }

    @POST
    @Path("/shipment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun createNewShipment(request: String): Response {
        println("Welcome")
        val shipment=objectMapper.readValue(request, Shipment::class.java)
        val record=shipmentService.createNewShipment(shipment)
        return Response.ok(record.toString()).build()
    }

    @PUT
    @Path("/shipment/update/{shipmentNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateShipment(@PathParam("shipmentNumber") shipmentNumber: String,request: String): Response {

        val shipment=objectMapper.readValue(request,Shipment::class.java)
        val record=shipmentService.updateShipment(shipmentNumber,shipment)
        return Response.ok(record.toString()).build()

    }

    @DELETE
    @Path("/shipment/{shipmentNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun deleteShipment(@PathParam("shipmentNumber") shipmentNumber: String): Response {

        val record=shipmentService.deleteShipment(shipmentNumber)
        return Response.ok("Record Deleted Successfully=:$record").build()

    }

    @GET
    @Path("/shipment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun countShipment(@QueryParam("count") count: Boolean): String? {
        var data="count"
        return if (count)
        {
           val shipmentcount= shipmentService.countShipment()
            println("count=:$shipmentcount")
           return shipmentcount
        }
        else {
          null
        }
    }
}