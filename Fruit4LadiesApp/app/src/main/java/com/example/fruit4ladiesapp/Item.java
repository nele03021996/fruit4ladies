package com.example.fruit4ladiesapp;

public class Item {
    String name;
    String bia;
    String barcode;
    Integer amount;

    //getter
    public String getName() {
        return name;
    }

    public String getBia() {
        return bia;
    }

    public String getBarcode() {
        return barcode;
    }

    public Integer getAmount() {
        return amount;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }

    public void setBia(String bia) {
        this.bia = bia;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
