package com.crio.qscore;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crio.qscore.KafkaServices.KafkaProducerService;
import com.crio.qscore.Utilities.AppDownException;
import com.crio.qscore.Utilities.AppIsDown;
import com.crio.qscore.Utilities.NonKafkaWriter;

@RestController
@RequestMapping("/api/v1")  // Base path for all mappings in this controller
public class QscoreController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/normalscore")
    public String postScore(@RequestBody Map<String, Object> scoreData) {

        AppIsDown.checkIfDown();

        // Convert the scoreData to a JSON string for printing
        String json = scoreData.toString();

        NonKafkaWriter.write(json);
        return "Score notification sent out successfully: " + json;
    }

    @PostMapping("/kafkascore")
    public ResponseEntity<String> postKafkaScore(@RequestBody Map<String, Object> scoreData) {
        
        
        String key = (String) scoreData.get("key");
        String value = scoreData.get("value").toString();

        kafkaProducerService.sendMessage(key, value);

        return ResponseEntity.ok("Score data sent to Kafka topic 'scores': " + scoreData);
    }

    @ExceptionHandler(AppDownException.class)
    public ResponseEntity<String> handleAppDownException(AppDownException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}