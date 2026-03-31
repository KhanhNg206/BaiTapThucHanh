package dto;

import entity.Order;
import entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDTO {
    private Order order;
    private Product product;
    private int quantity;
    private double unitPrice;
    private double discount;
}
