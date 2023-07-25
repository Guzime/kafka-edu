package ru.league.kafkaedu.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class PaperlessProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${paperless.topic.json}")
    private String topic;

    public void pushMessage(Paperless paperless) {

        log.info("Produced message to topic paperless.topic.json : {}", paperless);

        kafkaTemplate.send(topic, "{\n" +
                "  \"instanceId\": \"testing\",\n" +
                "  \"siebelId\": \"testing 2\"\n" +
                "}");
    }
}
