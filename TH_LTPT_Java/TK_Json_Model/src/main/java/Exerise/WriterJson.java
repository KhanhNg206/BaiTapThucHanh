package Exerise;

import enity.Country;
import jakarta.json.*;

import java.io.FileWriter;

public class WriterJson {
    public static void Writer(Country c,String fileName){
        try {
            FileWriter file = new FileWriter(fileName);
            JsonObject jo = Json.createObjectBuilder()
                    .add("id",c.getId())
                    .add("altSpellings",buildArray(c.getAltSpellings()))
                    .add("area",c.getArea())
                    .add("borders",buildArray(c.getBorders()))
                    .add("callingCode",buildArray(c.getCallingCode()))
                    .add("capital",c.getCapital())
                    .add("cca2",c.getCca2())
                    .add("cioc",c.getCioc())
                    .add("currency",buildArray(c.getCurrency()))
                    .add("demonym",c.getDemonym())
                    .add("landLocked",c.getLandLocked())
                    .add("latlng",doubleArray(c.getLatlng()))
                    .add("name",Json.createObjectBuilder().add("common",c.getName().getCommon()).add("official",c.getName().getOffical()))
                    .add("region",c.getRegion())
                    .add("subregion",c.getSubregion())
                    .add("translations",Json.createObjectBuilder()
                            .add("fra",Json.createObjectBuilder()
                                    .add("common",c.getTrans().getFra().getCommon())
                                    .add("official",c.getTrans().getFra().getOffical()))
                            .add("ita",Json.createObjectBuilder()
                                    .add("common",c.getTrans().getIta().getCommon())
                                    .add("official",c.getTrans().getIta().getOffical()))).build();

            JsonArray arr = Json.createArrayBuilder().add(jo).build();

            Json.createWriter(file).writeArray(arr);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static JsonArray buildArray(Iterable<String> list) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        list.forEach(builder::add);
        return builder.build();
    }

    private static JsonArray doubleArray(Iterable<Double> list){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        list.forEach(builder::add);
        return builder.build();
    }
}
