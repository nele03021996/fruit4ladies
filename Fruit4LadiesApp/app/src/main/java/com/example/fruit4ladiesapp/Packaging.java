package com.example.fruit4ladiesapp;

//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.annotations.SerializedName;

public class Packaging {
    String variant;
    @SerializedName("basket-color")
    String basketColor;
    @SerializedName("paper-color")
    String paperColor;
    String message;
    @SerializedName("customer-comment")
    String customerComment;

    //Getter
    public String getVariant() {
        return variant;
    }

    public String getBasketColor() {
        return basketColor;
    }

    public String getPaperColor() {
        return paperColor;
    }

    public String getMessage() {
        return message;
    }

    public String getCustomerComment() {
        return customerComment;
    }

    //Setter
    public void setVariant(String variant) {
        this.variant = variant;
    }

    public void setBasketColor(String basketColor) {
        this.basketColor = basketColor;
    }

    public void setPaperColor(String paperColor) {
        this.paperColor = paperColor;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }

}
