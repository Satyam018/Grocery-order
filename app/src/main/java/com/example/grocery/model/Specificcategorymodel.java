package com.example.grocery.model;

public class Specificcategorymodel {
   private String category,imageurl,name,offer,price,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Specificcategorymodel(String category, String imageurl, String name, String offer, String price, String id) {
        this.category = category;
        this.imageurl = imageurl;
        this.name = name;
        this.offer = offer;
        this.price = price;
        this.id=id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
