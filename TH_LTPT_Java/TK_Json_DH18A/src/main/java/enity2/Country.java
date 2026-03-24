package enity2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Country {
    private String countryID;
    private List<String> altSpllings;
    private String area;
    private List<String> border;
    private List<String> callingCode;
    private String capital;
    private String cca2;
    private String cioc;
    private List<String> currency;
    private String demonym;
    private Boolean landLocked;
    private  List<Double> latIng;
    private Name name;
    private String region;
    private String subregion;
    private Map<String,TranslationDetail> translation;
}

