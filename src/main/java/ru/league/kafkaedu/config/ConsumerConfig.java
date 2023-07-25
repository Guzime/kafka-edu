package ru.league.kafkaedu.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.league.kafkaedu.dto.Paperless;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ConsumerConfig {

    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaGroupId;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaBootstrapServers;


    public Map<String, Object> consumerConfigMap() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        return props;
    }

    @Bean(name = "consumerFactory")
    public ConsumerFactory<String, Paperless> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigMap(),
                new StringDeserializer(),
                new JsonDeserializer<>(Paperless.class));
    }

    @Bean(name = "containerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Paperless> containerFactory(
            @Qualifier("consumerFactory") ConsumerFactory<String, Paperless> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Paperless> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
