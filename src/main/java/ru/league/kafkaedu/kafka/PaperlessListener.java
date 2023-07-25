package ru.league.kafkaedu.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.league.kafkaedu.dto.Paperless;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaperlessListener {

    @KafkaListener(topics = "${paperless.topic.json}", containerFactory = "containerFactory")
    public void consume(ConsumerRecord<String, Paperless> payload) {
        log.info("Consumed message from paperless.topic.json topic: {}", payload.value());
    }
}
