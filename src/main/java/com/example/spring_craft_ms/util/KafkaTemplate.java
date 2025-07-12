package com.example.spring_craft_ms.util;

import org.apache.kafka.streams.kstream.Produced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.PriorityQueue;

@RestController
@RequestMapping
public class KafkaTemplate {
    //Produced<>
    PriorityQueue<Integer> heapOfStones = new PriorityQueue<>(Collections.reverseOrder());
}
