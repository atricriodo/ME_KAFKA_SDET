package com.crio.qscore.KafkaServices;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.crio.qscore.Utilities.AppIsDown;

@EnableKafka
@Service
public class KafkaConsumerService {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    // TODO: Uncomment
    // @KafkaListener(topics = "scores", groupId = "sports-fans-web")
    public void listen(String message) throws Exception {
        AppIsDown.checkIfDown();
        System.out.println("Received message in group sports-fans-web: " + message);
        System.out.println(ANSI_GREEN + "SENT OUT NOTIFICATION WITH THE HELP OF KAFKA: " + message + ANSI_RESET);
    }
}