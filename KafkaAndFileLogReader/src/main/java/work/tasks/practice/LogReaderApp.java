package work.tasks.practice;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class LogReaderApp {
    public static void main(String[] args) {
        // Конфигурация Kafka Consumer
        Properties kafkaProps = new Properties();
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(kafkaProps);
        kafkaConsumer.subscribe(Collections.singletonList("KafkaTopicThird"));


        // Путь к файлу, который нужно читать
        String filePath = "C:\\Users\\Admin\\Desktop\\Java practis\\fromOnetoFiveStages\\SecondTrySecondStage\\src\\main\\resources\\FileForInfo.txt";

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath)) ) {
            while (true) {
                // Чтение из Kafka
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Сообщение из Kafka: " + record.value());
                }

                // Чтение из файла
                String line;
                while ((line = fileReader.readLine()) != null) {
                    System.out.println("Сообщение из файла: " + line);
                    System.out.println("------------------------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }
}
