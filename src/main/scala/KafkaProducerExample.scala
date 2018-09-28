import java.util.Properties

import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

object KafkaProducerExample {

  def main(args: Array[String]): Unit = {

    val conf = ConfigFactory.load(this.getClass().getClassLoader())
    val envConfig = conf.getConfig(args(0))
    val props = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, envConfig.getString("bootstrap.server"))
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducerExample")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)

    val data = new ProducerRecord[String, String](envConfig.getString("topic"), "hello", "Universe")
    producer.send(data)
    if (producer != null) {
      producer.close()
    }
  }
}
