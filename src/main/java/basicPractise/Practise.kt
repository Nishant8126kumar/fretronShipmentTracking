package basicPractise

import org.codehaus.jackson.map.ObjectMapper
import repositories.Shipment
import javax.ws.rs.core.Response

class Practise {
    private val request=" {\n" +
            "        \"shipmentId\":\"a694dc04-5bef-4620-af69-b639bd2b3184\",\n" +
            "        \"pickupPlace\":\"Aligarh\",\n" +
            "        \"deliveryPlace\":\"delhi\"\n" +
            "    }"

    val mapper=ObjectMapper()
    fun getData():Response
    {
        var data=mapper.readValue(request,Shipment::class.java)

        return Response.ok(data.toString()).build()
    }
}
fun main()
{
    val obj=Practise();
    val res=obj.getData()
    val string=res.readEntity(String::class.java)
//    println("data=:$res")
}

