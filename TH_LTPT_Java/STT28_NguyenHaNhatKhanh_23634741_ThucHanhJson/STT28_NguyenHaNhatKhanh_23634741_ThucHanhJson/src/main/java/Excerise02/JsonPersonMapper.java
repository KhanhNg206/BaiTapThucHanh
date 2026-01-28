package Excerise02;

import jakarta.json.Json;
import jakarta.json.stream.JsonParser;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonPersonMapper {

    public static Person fromJson(String json) {

        JsonParser parser = Json.createParser(new StringReader(json));

        Person person = new Person();
        Address address = null;
        PhoneNumber phone = null;

        String currentKey = "";
        boolean inPhoneArray = false;

        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();

            switch (event) {

                case KEY_NAME:
                    currentKey = parser.getString();
                    break;

                case START_OBJECT:
                    if ("address".equals(currentKey)) {
                        address = new Address();
                        person.setAddress(address);
                    } else if (inPhoneArray) {
                        phone = new PhoneNumber();
                    }
                    break;

                case START_ARRAY:
                    if ("phoneNumbers".equals(currentKey)) {
                        inPhoneArray = true;
                    }
                    break;

                case VALUE_STRING:
                    switch (currentKey) {
                        case "firstName":
                            person.setFirstName(parser.getString());
                            break;
                        case "lastName":
                            person.setLastName(parser.getString());
                            break;
                        case "streetAddress":
                            address.setStreetAddress(parser.getString());
                            break;
                        case "city":
                            address.setCity(parser.getString());
                            break;
                        case "state":
                            address.setState(parser.getString());
                            break;
                        case "type":
                            phone.setType(parser.getString());
                            break;
                        case "number":
                            phone.setNumber(parser.getString());
                            break;
                    }
                    break;

                case VALUE_NUMBER:
                    if ("age".equals(currentKey)) {
                        person.setAge(parser.getInt());
                    } else if ("postalCode".equals(currentKey)) {
                        address.setPostalCode(parser.getInt());
                    }
                    break;

                case END_OBJECT:
                    if (phone != null && inPhoneArray) {
                        person.getPhoneNumbers().add(phone);
                        phone = null;
                    }
                    break;

                case END_ARRAY:
                    inPhoneArray = false;
                    break;

                default:
                    break;
            }
        }
        parser.close();
        return person;
    }

    public static void main(String[] args) throws Exception {

//        String json = Files.readString(Paths.get("T:\\STT28_NguyenHaNhatKhanh_23634741_ThucHanhJson\\src\\main\\resources\\Json\\info.json"));
        String json = Files.readString(Paths.get("T:\\STT28_NguyenHaNhatKhanh_23634741_ThucHanhJson\\src\\main\\java\\Excerise02\\Json\\info.json")
        );

        Person person = fromJson(json);
        System.out.println(person);
    }
}
