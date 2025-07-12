package com.example.spring_craft_ms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/** KafkaProducerService is responsible for sending messages to a Kafka topic.
 * It uses KafkaTemplate to send messages asynchronously.
 */
@Service
@Slf4j
public class KafkaProducerService {
    /** KafkaTemplate is used to send messages to Kafka.
     * It is configured with the necessary properties to connect to the Kafka cluster. */
    private final KafkaTemplate<String, String> kafkaTemplate;

    /** The topic to which messages will be sent.
     * This should match the topic configured in the Kafka cluster. */
    private static final String TOPIC = "example_topic";

    /** Constructor for KafkaProducerService.
     * @param kafkaTemplate the KafkaTemplate to be used for sending messages.
     */
    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /** Sends a message to the configured Kafka topic.
     * @param message the message to be sent.
     */
    public void sendMessage(String message) {
         this.kafkaTemplate.send(TOPIC, message);
        log.info("Message sent : " + message);
    }
}
