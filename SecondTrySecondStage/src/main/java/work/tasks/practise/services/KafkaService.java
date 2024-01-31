package work.tasks.practise.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopic;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate, @Value("KafkaTopicThird") String kafkaTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(kafkaTopic, message);
    }
}