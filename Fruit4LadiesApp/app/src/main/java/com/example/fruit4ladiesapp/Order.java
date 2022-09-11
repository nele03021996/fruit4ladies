package com.example.fruit4ladiesapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    public int id = 12345;
    public String pack = "test packaging";
    public List<OrderItem> items = Arrays.asList(new OrderItem(3,"apple"), new OrderItem(1, "orange"));
}
