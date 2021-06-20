package com.example.grocery.model;

public class Categorymodel {
    String category;
    int Image;

    public Categorymodel(String category, int image) {
        this.category = category;
        Image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
