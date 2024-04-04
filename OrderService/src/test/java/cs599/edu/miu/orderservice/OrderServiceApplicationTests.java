package cs599.edu.miu.orderservice;

import cs599.edu.miu.basedomains.baseDomains.Order;
import cs599.edu.miu.basedomains.baseDomains.OrderEvent;
import cs599.edu.miu.orderservice.service.OrderProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class OrderServiceApplicationTests {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    @Autowired
    private OrderProducer orderProducer;

    @Value("${spring.kafka.topic.name}")
    private String TOPIC;

    @Test
    void contextLoads() {
    }

    @Test
    void givenEmbeddedKafkaBroker_whenOrderEventIsSent()
            throws Exception {
         Order order = new Order("1", "Book order", 10, 100.0);
         OrderEvent orderEvent = new OrderEvent();
         orderEvent.setMessage("Test message");
         orderEvent.setStatus("CREATED");
         orderEvent.setOrder(order);

         String result = orderProducer.sendOrder(orderEvent);
         assert result.equals("Order sent to Kafka successfully!");
    }

}
