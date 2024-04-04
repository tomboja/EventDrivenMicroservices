package cs599.edu.miu.basedomains.baseDomains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ProjectName: EventDrivenMicroservices
 * @Author: Temesgen D.
 * @Date: 4/3/24
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Order {
    private String orderId;
    private String orderName;
    private int quantity;
    private double price;

}
