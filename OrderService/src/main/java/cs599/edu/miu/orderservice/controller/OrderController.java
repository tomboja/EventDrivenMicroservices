package cs599.edu.miu.orderservice.controller;

import cs599.edu.miu.basedomains.baseDomains.Order;
import cs599.edu.miu.basedomains.baseDomains.OrderEvent;
import cs599.edu.miu.orderservice.service.OrderProducer;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: EventDrivenMicroservices
 * @Author: Temesgen D.
 * @Date: 4/3/24
 */

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }


    // Post Mapping to send OrderEvent to Kafka
    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId("ORD" + System.currentTimeMillis());

        OrderEvent orderEvent = new OrderEvent("New Order", order, "CREATED");
        logger.info(String.format("Producing order -> %s", orderEvent));

        return orderProducer.sendOrder(orderEvent);
    }
}
