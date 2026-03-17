package enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {
    private int id;
    private List<String> altSpellings;
    private int area;
    private List<String> borders;
    private List<String> callingCode;
    private String capital;
    private String cca2;
    private String cioc;
    private List<String> currency;
    private String demonym;
    private Boolean landLocked;
    private List<Double> latlng;
    private Name name;
    private String region;
    private String subregion;
    private Translations trans;
}
