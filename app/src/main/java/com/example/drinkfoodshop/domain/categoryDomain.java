package com.example.drinkfoodshop.domain;

import java.io.Serializable;

public class categoryDomain implements Serializable {
    private String title;
    private String pic;
    private int categoryId;
    private String description;
    private boolean bestFood;
    private int id;
    private int locationId;
    private double price;
    private int priceId;
    private int numberInCart;

    public categoryDomain(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    // Thêm các phương thức getter và setter

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBestFood() {
        return bestFood;
    }

    public void setBestFood(boolean bestFood) {
        this.bestFood = bestFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
