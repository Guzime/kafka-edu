package ru.league.kafkaedu.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.league.kafkaedu.dto.Paperless;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaperlessObjectProducer {

    @Qualifier("kafkaTemplatePaperless")
    private final KafkaTemplate<String, Paperless> kafkaTemplate;

    @Value("${paperless.topic.json}")
    private String topic;

    @SneakyThrows
    public void pushMessage(Paperless paperless) {
        log.info("Produced message Paperless as Object to topic {} : {}", topic, paperless);
        kafkaTemplate.send(topic, paperless);
    }
}
