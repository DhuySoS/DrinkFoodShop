package com.example.drinkfoodshop.domain;

public class function {

    private String title;
    private String description;

    public function(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
