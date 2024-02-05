package com.example.drinkfoodshop.domain;

public class food {
    private int CategoryId;
    private String Description;
    private boolean BestFood;
    private int Id;
    private double Price;
    private int NumberInCart;
    private String Title;
    private String ImagePath;

    public food() {
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isBestFood() {
        return BestFood;
    }

    public void setBestFood(boolean bestFood) {
        BestFood = bestFood;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getNumberInCart() {
        return NumberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        NumberInCart = numberInCart;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
