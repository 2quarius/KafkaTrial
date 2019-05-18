import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerAnalysis {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static Properties initConfig(){
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        prop.put(ProducerConfig.CLIENT_ID_CONFIG,"producer.client.id.demo");
        return prop;
    }

    public static void main(String[] args){
        Properties props = initConfig();
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,"hello,kafka!");
        try{
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata metadata = future.get();
            System.out.println(metadata.topic()+'-'+metadata.partition()+":"+metadata.offset());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
