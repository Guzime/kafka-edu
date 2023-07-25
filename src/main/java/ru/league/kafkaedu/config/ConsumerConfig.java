package ru.league.kafkaedu.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.league.kafkaedu.dto.PaperlessTaskResult;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ConsumerConfig {

    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaGroupId;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaBootstrapServers;


    public Map<String, Object> consumerQueueConfigMapJson() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        return props;
    }

    @Bean
    public ConsumerFactory<String, PaperlessTaskResult> consumerFactoryQueueUpdatedClient() {
        return new DefaultKafkaConsumerFactory<>(
                consumerQueueConfigMapJson(),
                new StringDeserializer(),
                new JsonDeserializer<>(PaperlessTaskResult.class));
    }

    @Bean(name = "paperlessListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, PaperlessTaskResult> kafkaListenerContainerFactoryQueueUpdatedClient(ConsumerFactory<String, PaperlessTaskResult> jsonConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, PaperlessTaskResult> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(jsonConsumerFactory);
        return factory;
    }


}
