package com.krasnikov.consumer.services;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import src.main.avro.MessageEvent;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsumerService {
    @KafkaListener(topics = "${topics.message}")
    public void getMessagesFromCorporateTopic(MessageEvent recordValue,
                                              @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.info("key {}, value: {}", key, recordValue);
    }
}