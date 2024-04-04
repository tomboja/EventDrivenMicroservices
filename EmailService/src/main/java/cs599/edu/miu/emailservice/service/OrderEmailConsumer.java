package cs599.edu.miu.emailservice.service;

import cs599.edu.miu.basedomains.baseDomains.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: EventDrivenMicroservices
 * @Author: Temesgen D.
 * @Date: 4/3/24
 */

@Service
public class OrderEmailConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEmailConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public String consumeMessage(OrderEvent orderEvent) {
        LOGGER.info(String.format("Consumed order and sending email -> %s", orderEvent));

        // Business logic to send email
        return String.format("Email sent to customer with order of -> %s", orderEvent);
    }
}
