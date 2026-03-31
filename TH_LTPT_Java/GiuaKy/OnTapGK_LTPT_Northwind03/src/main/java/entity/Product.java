package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @JsonProperty("product_id")
    private String id;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("unit_price")
    private double unitPrice;
    @JsonProperty("units_in_stock")
    private int unitInStock;
    private Supplier supplier;
}
