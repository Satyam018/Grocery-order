package com.example.grocery.model;

public class Specifcproductcommentmodel {
    String accountid,comments;

    public Specifcproductcommentmodel(String accountid, String comments) {
        this.accountid = accountid;
        this.comments = comments;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
