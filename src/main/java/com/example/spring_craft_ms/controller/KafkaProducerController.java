package com.example.spring_craft_ms.controller;

import com.example.spring_craft_ms.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** KafkaProducerController is a REST controller that handles HTTP requests for sending messages to a Kafka topic.
 * It uses the KafkaProducerService to send messages asynchronously.
 */
@RestController
@RequestMapping(value = "/api/v1/kafka")
@Slf4j
public class KafkaProducerController {
    /** KafkaProducerService is injected to handle the business logic of sending messages to Kafka.
     * It is responsible for interacting with the KafkaTemplate to send messages.
     */
    @Autowired
    private KafkaProducerService kafkaProducerService;

    /** This method handles POST requests to the /publish endpoint.
     * It receives a message in the request body and sends it to the Kafka topic using the KafkaProducerService.
     * @param message the message to be sent to Kafka.
     * @return a confirmation message indicating whether the message was sent successfully or not.
     */
    @PostMapping(value = "/publish")
    public String sendMessageToKafka(@RequestBody String message) {
        try {
            kafkaProducerService.sendMessage(message);
            return "Message Sent to Kafka: " + message;
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Message not Sent";
        }
    }
}
