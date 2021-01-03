package utils.consumer

import org.apache.kafka.clients.consumer.KafkaConsumer
import javax.inject.Inject

class ConsumerReadData @Inject constructor(private val consumer: KafkaConsumer<String, String>) {
    fun readConsumer()
    {
        while(true)
        {
            val records=consumer.poll(1000)
            for (record in records) {
                println("key=:${record.key()}")
                println("data=:${record.value()}")
                println("topic=:${record.topic()}")
                println("offset=:${record.offset()}")
            }
        }
    }
    fun start()
    {
        Thread()
        {
            readConsumer()
        }.start()
    }
}

