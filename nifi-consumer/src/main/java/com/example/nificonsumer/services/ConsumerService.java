package com.example.nificonsumer.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsumerService {
    @KafkaListener(topics = "${topics.message}")
    public void listen(String jsonPayload) {
        log.info("raw JSON: {}", jsonPayload);
    }
}