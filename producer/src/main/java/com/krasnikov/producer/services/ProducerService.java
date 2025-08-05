package com.krasnikov.producer.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.ivan.ver.avro.MessageEvent;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProducerService {

    final KafkaTemplate<String, MessageEvent> kafkaTemplate;
    final AtomicInteger idCounter = new AtomicInteger(1);

    @Value("${topics.message}")
    String topicName;

    @Scheduled(fixedDelay = 1000)
    public void sendMessage() {
        String key = UUID.randomUUID().toString();
        MessageEvent event = new MessageEvent(
                idCounter.getAndIncrement(),
                "personal",
                generateText()
        );

        kafkaTemplate.send(topicName, key, event)
                .thenAccept(this::logSuccess)
                .exceptionally(ex -> {
                    log.error("Failed to send message to topic {} with key {}: {}",
                            topicName, key, ex.getMessage(), ex);
                    throw new KafkaException("Error sending MessageEvent", ex);
                });
    }

    private void logSuccess(SendResult<String, MessageEvent> result) {
        log.info("Successfully sent to topic {} [partition={}, offset={}], key={}, event={}",
                result.getRecordMetadata().topic(),
                result.getRecordMetadata().partition(),
                result.getRecordMetadata().offset(),
                result.getProducerRecord().key(),
                result.getProducerRecord().value()
        );
    }

    private String generateText() {
        return IntStream.range(0, 3)
                .mapToObj(i -> UUID.randomUUID().toString())
                .collect(Collectors.joining(" "));
    }
}
