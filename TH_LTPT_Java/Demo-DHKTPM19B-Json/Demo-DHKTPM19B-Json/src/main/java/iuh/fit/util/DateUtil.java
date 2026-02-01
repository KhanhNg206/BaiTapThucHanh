package iuh.fit.util;

import iuh.fit.entity.Student;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.time.LocalDate;

public class DateUtil {
    public static LocalDate fromJson(JsonObject jo){
        int year = jo.getInt("year");
        int month = jo.getInt("month");
        int day = jo.getInt("day");
        return LocalDate.of(year, month, day);
    }

    public static JsonObject toJsonObject(LocalDate date){
        JsonObjectBuilder  oBuilder = Json.createObjectBuilder();
        return oBuilder.add("year", date.getYear())
                .add("month", date.getMonthValue())
                .add("day", date.getDayOfMonth())
                .build();
    }
}
