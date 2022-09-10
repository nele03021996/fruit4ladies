package com.example.fruit4ladiesapp;

public class Address {
    String Name;
    String Street;
    String City;
    String ZipCode;

    //Getter
    public String getName() {
        return Name;
    }

    public String getStreet() {
        return Street;
    }

    public String getCity() {
        return City;
    }

    public String getZipCode() {
        return ZipCode;
    }

    //Setter
    public void setName(String name) {
        Name = name;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }
}
