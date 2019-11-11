package com.sixt.rental.messagesubscriber.integration.kafka;

import com.sixt.rental.demo.events.RawBookEvent;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class KafkaSubscriber {

    @KafkaListener(
            autoStartup = "true",
            topics = {"${rental-service.topics.event-listener}"},
            containerFactory = "containerFactory"
    )
    public void onCommandRecord(ConsumerRecord<String, RawBookEvent> record, Acknowledgment acknowledgment) {
        final RawBookEvent message = record.value();
        MDC.put("correlation_id", message.getCorrelationId().getValue());
        if (message.hasIdentifier()) {
            var bookeId = message.getIdentifier().getValue();
            if (bookeId != null) {
                MDC.put("bookID", bookeId);
            }
        }
        log.info("process incoming book lifecycle event {}", message.toString());
        MDC.clear();
    }
}
