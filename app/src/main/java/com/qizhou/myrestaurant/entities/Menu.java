package com.qizhou.myrestaurant.entities;

public class Menu {
    private Category category;
    private int image;
    private String name;

    public Menu(Category category, int image, String name) {
        this.category = category;
        this.image = image;
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
