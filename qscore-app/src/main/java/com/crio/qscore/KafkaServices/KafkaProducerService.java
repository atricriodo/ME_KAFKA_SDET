package com.crio.qscore.KafkaServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendMessage(String key, String value) {
        System.out.printf("Sending consumer event: %s to topic: %s with key: %s%n", value, "scores", key);
        // TODO: Add Key to argument list "new ProducerRecord<>("scores", key, value);"
        ProducerRecord<String, String> message = new ProducerRecord<>("scores", value);
        kafkaTemplate.send(message);
    }
}