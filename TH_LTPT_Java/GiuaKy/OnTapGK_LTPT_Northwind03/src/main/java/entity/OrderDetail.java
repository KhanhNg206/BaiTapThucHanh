package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {
    private Order order;
    private Product product;
    private int quantity;
    private double unitPrice;
    private double discount;
}
