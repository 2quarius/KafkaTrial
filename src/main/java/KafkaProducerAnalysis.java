import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerAnalysis {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static Properties initConfig(){
        Properties prop = new Properties();
        prop.put("bootstrap.servers",brokerList);
        prop.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        prop.put("client.id","producer.client.id.demo");
        return prop;
    }

    public static void main(String[] args){
        Properties props = initConfig();
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,"hello,kafka!");
        try{
            producer.send(record);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
