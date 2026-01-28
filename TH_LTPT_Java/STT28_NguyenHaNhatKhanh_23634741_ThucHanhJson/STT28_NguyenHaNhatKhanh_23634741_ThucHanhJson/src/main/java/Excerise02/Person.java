package Excerise02;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Address address;
    private List<PhoneNumber> phoneList = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName, int age,
                  Address address, List<PhoneNumber> phoneList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phoneList = phoneList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneList(List<PhoneNumber> phoneList) {
        this.phoneList = phoneList;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public Address getAddress() { return address; }
    public List<PhoneNumber> getPhoneNumbers() { return phoneList; }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", age=" + age +
                "\nAddress: " + address +
                "\nPhones: " + phoneList;
    }
}
