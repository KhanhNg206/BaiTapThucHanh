package Excerise01;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

import java.io.StringReader;

public class StreamingDemo {

    public static void main(String[] args) {

        String json = "{\"name\":\"Falco\",\"age\":3,\"bitable\":false}";
        JsonParser parser = Json.createParser(new StringReader(json));

        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();

            switch (event) {
                case KEY_NAME:
                    System.out.println("KEY: " + parser.getString());
                    break;
                case VALUE_STRING:
                    System.out.println("VALUE STRING: " + parser.getString());
                    break;
                case VALUE_NUMBER:
                    System.out.println("VALUE NUMBER: " + parser.getInt());
                    break;
                case VALUE_FALSE:
                    System.out.println("VALUE FALSE");
                    break;
                default:
                    break;
            }
        }
        parser.close();
    }
}

