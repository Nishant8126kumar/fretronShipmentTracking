package services

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import helper.TestDataSource
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import repositories.ShipmentRepository

class ShipmentServiceShould {

    lateinit var shipmentRepository: ShipmentRepository
    lateinit var shipmentService: ShipmentService
    private val testDataSource=TestDataSource()
    lateinit var shipmentNumber:String
    @Before
    fun configure()
    {
        shipmentRepository=mock()
        shipmentService= ShipmentService(shipmentRepository)
    }

    @Test
    fun testCreateNewShipment()
    {
        val record=testDataSource.getShipment()
        whenever(shipmentRepository.createNewShipment(any())).thenReturn(record)
        val response=shipmentService.createNewShipment(record)
        println("data=:$response")
        Assert.assertNotNull(response)
        verify(shipmentRepository).createNewShipment(any())
        shipmentNumber=response.getShipmentNumber().toString()
        println("ShipmentNumber=:$shipmentNumber")

    }

    @Test
    fun testGetShipmentByShipmentNumber()
    {
        testCreateNewShipment()
        whenever(shipmentRepository.getShipmentByShipmentNumber(shipmentNumber)).thenReturn(testDataSource.getShipment())
        val response=shipmentService.getShipmentByShipmentNumber(shipmentNumber)
        Assert.assertNotNull(response)
        verify(shipmentRepository).getShipmentByShipmentNumber(shipmentNumber)

    }

    @Test
    fun  testUpdateShipment()
    {
        testCreateNewShipment()
        whenever(shipmentRepository.updateShipment(any(), any())).thenReturn(testDataSource.getShipment())
        val response=shipmentService.updateShipment(shipmentNumber,testDataSource.getShipment())
        Assert.assertNotNull(response)
        verify(shipmentRepository).updateShipment(shipmentNumber,testDataSource.getShipment())
    }

    @Test
    fun testDeleteShipment()
    {
        testCreateNewShipment()
        whenever(shipmentRepository.deleteShipment(any())).thenReturn(shipmentNumber)
        val response=shipmentService.deleteShipment(shipmentNumber)
        Assert.assertNotNull(response)
        verify(shipmentRepository).deleteShipment(shipmentNumber)
    }

    @Test
    fun countShipment()
    {
        whenever(shipmentRepository.countShipment()).thenReturn("count")
        val response=shipmentService.countShipment()
        Assert.assertNotNull(response)
        verify(shipmentRepository).countShipment()
    }

}