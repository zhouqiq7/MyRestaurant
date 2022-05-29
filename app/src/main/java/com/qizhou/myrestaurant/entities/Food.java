package com.qizhou.myrestaurant.entities;

import java.io.Serializable;

public class Food implements Serializable {
    private final long serialVersionUID = -43242543548918589L;

    private String name;
    private int image;
    private Category category;
    private String description;
    private double price;

    public Food(String name, int image, Category category, String description, double price) {
        this.name = name;
        this.image = image;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
