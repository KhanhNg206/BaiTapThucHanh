package Excerise01;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class EncodeJson {

    public static void main(String[] args) throws Exception {
        EncodeJson ej = new EncodeJson();

        Employee e = new Employee(10001, "Thân Thị Đẹt", 10000d);

        String json = ej.genJson(e);
        ej.export("json/emp.json", json);

        System.out.println("Đã ghi JSON ra file emp.json");
    }

    public String genJson(Employee e) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", e.getId());
        builder.add("name", e.getName());
        builder.add("salary", e.getSalary());

        JsonObject jo = builder.build();
        return jo.toString();
    }

    public void export(String filePath, String json) throws Exception {
        PrintWriter out = new PrintWriter(new FileOutputStream(filePath), true);
        out.println(json);
        out.close();
    }
}

