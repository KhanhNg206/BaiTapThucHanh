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
public class Supplier {
    private String id;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("contact_name")
    private String contactName;
    @JsonProperty("country")
    private String country;
}
