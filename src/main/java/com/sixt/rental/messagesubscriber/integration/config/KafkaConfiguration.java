package com.sixt.rental.messagesubscriber.integration.config;

import com.sixt.rental.demo.events.RawBookEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.retry.support.RetryTemplate;

import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL_IMMEDIATE;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@AllArgsConstructor
public class KafkaConfiguration {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> containerFactory(
            KafkaTemplate<Object, Object> template,
            ConsumerFactory<String, RawBookEvent> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, RawBookEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckOnError(false);
        factory.getContainerProperties().setAckMode(MANUAL_IMMEDIATE);

        SeekToCurrentErrorHandler errorHandler = new SeekToCurrentErrorHandler(-1);
        errorHandler.setCommitRecovered(true);
        factory.setErrorHandler(errorHandler);

        RetryTemplate retryTemplate = new RetryTemplate();

        factory.setRetryTemplate(retryTemplate);
        factory.setStatefulRetry(true);

        return factory;
    }
}
