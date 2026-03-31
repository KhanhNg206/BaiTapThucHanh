package core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    @JsonProperty("product_name")
    private String productName;
    private String unit;
    @JsonProperty("unit_price")
    private double unitPrice;
    @JsonProperty("supplier_id")
    private String supplier_id;
    @JsonProperty("units_in_stock")
    private int UnitInStock;
}
