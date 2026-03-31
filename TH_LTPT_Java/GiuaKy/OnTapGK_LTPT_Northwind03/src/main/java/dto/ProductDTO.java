package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
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
    @JsonProperty("supplier_id")
    private String supplierID;
}
