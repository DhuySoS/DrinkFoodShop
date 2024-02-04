package com.example.drinkfoodshop.domain;

public class Price {
    private String value;
    private int Id;

    public Price(){
    }
    @Override
    public String toString(){
        return value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
