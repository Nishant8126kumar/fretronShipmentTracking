package utils.consumer

import org.apache.kafka.clients.consumer.KafkaConsumer
import repositories.ActivityLogRepository
import javax.inject.Inject

class ConsumerReadData @Inject constructor(private val consumer: KafkaConsumer<String, String>,private val activityLogRepository: ActivityLogRepository) {
    fun readConsumer()
    {
        while(true)
        {
            val records=consumer.poll(1000)
            val iterator=records.iterator()
            while (iterator.hasNext())
            {
                val record=iterator.next()
                println("Key=:${record.key()}")
                println("data=:${record.value()}")
                activityLogRepository.saveActivityLog(record.key(),record.value())
            }
//            for (record in records) {
//                println("key=:${record.key()}")
//                println("data=:${record.value()}")
//                println("topic=:${record.topic()}")
//                println("offset=:${record.offset()}")
//            }
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

