package com.example.grocery.model;

public class Myordermodel {
    String url;
    String productid;
    String name;

    public Myordermodel(String url, String productid, String name) {
        this.url = url;
        this.productid = productid;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
