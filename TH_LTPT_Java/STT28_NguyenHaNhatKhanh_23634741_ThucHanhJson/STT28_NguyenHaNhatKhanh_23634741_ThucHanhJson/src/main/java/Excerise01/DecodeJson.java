package Excerise01;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.FileInputStream;
import java.io.InputStream;

public class DecodeJson {

    public static void main(String[] args) throws Exception {

        InputStream in = new FileInputStream("json/emp.json");
        JsonReader reader = Json.createReader(in);

        JsonObject jo = reader.readObject();

        long id = jo.getJsonNumber("id").longValue();
        String name = jo.getString("name");
        double salary = jo.getJsonNumber("salary").doubleValue();

        Employee emp = new Employee(id, name, salary);

        System.out.println(emp);

        reader.close();
        in.close();
    }
}

