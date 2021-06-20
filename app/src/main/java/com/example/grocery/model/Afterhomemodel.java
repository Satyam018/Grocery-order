package com.example.grocery.model;

public class Afterhomemodel {
    String name,imgurl,price,offer,productid;

    public Afterhomemodel(String name, String imgurl, String price, String offer, String productid) {
        this.name = name;
        this.imgurl = imgurl;
        this.price = price;
        this.offer = offer;
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}
