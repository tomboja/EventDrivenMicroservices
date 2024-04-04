package cs599.edu.miu.emailservice;

import cs599.edu.miu.basedomains.baseDomains.Order;
import cs599.edu.miu.basedomains.baseDomains.OrderEvent;
import cs599.edu.miu.emailservice.service.OrderEmailConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class EmailServiceApplicationTests {

	@Autowired
	private OrderEmailConsumer orderEmailConsumer;

	@Test
	void contextLoads() {
	}

	@Test
	void givenEmbeddedKafkaBroker_whenEmailEventIsSent()
			throws Exception {
		Order order = new Order("1", "Book order", 10, 100.0);
		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setOrder(order);
		orderEvent.setStatus("CREATED");
		orderEvent.setMessage("Order created successfully and email sent!");

		String result = orderEmailConsumer.consumeMessage(orderEvent);
		assert result.equals("Email sent to customer with order of -> " + orderEvent);
	}

}
