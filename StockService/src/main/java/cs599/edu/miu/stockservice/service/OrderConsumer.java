package cs599.edu.miu.stockservice.service;

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
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public String consumeMessage(OrderEvent orderEvent) {
        LOGGER.info(String.format("Consumed order -> %s", orderEvent));

        // Business logic to process and add order to db
        return String.format("Order processed and added to db -> %s", orderEvent);
    }

}
