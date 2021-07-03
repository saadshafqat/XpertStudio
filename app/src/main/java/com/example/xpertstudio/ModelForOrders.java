package com.example.xpertstudio;

public class ModelForOrders {
    String firstReq,secondReq,thirdReq,price;

    public ModelForOrders(String firstReq, String secondReq, String thirdReq, String price) {
        this.firstReq = firstReq;
        this.secondReq = secondReq;
        this.thirdReq = thirdReq;
        this.price = price;
    }

    public String getFirstReq() {
        return firstReq;
    }

    public void setFirstReq(String firstReq) {
        this.firstReq = firstReq;
    }

    public String getSecondReq() {
        return secondReq;
    }

    public void setSecondReq(String secondReq) {
        this.secondReq = secondReq;
    }

    public String getThirdReq() {
        return thirdReq;
    }

    public void setThirdReq(String thirdReq) {
        this.thirdReq = thirdReq;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
