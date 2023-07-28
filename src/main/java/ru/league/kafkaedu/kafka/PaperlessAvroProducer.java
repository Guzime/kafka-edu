package ru.league.kafkaedu.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.apache.avro.generic.GenericRecord;

import java.util.Properties;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaperlessAvroProducer {

    @Value("${paperless.topic.avro}")
    public String topic;

    @Value("${BOOTSTRAP_SERVERS_PRODUCER:http://localhost:29092}")
    private String bootstrapServers;

    @Value("${REGISTRY_SCHEMA_URL:http://localhost:8081}")
    private String registrySchemaUrl;

    public void pushMessage(GenericRecord avroRecord) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        props.put("schema.registry.url", registrySchemaUrl);

        KafkaProducer producer = new KafkaProducer(props);
        ProducerRecord<Object, Object> record = new ProducerRecord<>(topic, "", avroRecord);
        producer.send(record);
        log.info("Producer send resord: {}", record);
        producer.flush();
        producer.close();
    }
}
