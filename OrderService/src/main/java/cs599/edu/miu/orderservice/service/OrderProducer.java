package cs599.edu.miu.orderservice.service;

import cs599.edu.miu.basedomains.baseDomains.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: EventDrivenMicroservices
 * @Author: Temesgen D.
 * @Date: 4/3/24
 */

@Service
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    private final NewTopic topic;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendOrder(OrderEvent orderEvent) {
        logger.info(String.format("Producing order -> %s", orderEvent));

        // Create Message to send to Kafka
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);

        return "Order sent to Kafka successfully!";
    }
}
