package core.dto;

import core.entity.Order;
import core.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Order order;
    private Product product;
    private int quantity;
    private double unitPrice;
    private double discount;
}
