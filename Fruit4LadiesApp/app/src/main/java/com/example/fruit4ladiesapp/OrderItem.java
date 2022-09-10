package com.example.fruit4ladiesapp;

public class OrderItem {
    private int amount;
    private String name;

    OrderItem(int amount, String name){
        this.amount = amount;
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
