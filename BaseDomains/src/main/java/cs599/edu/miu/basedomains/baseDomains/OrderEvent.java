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

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEvent {
    private String message;
    private Order order;
    private String status;
}
