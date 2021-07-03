package com.example.xpertstudio;

public class Cart {
    String firstreq,price,secondreq,thirdreq,pkg;

    public Cart(String firstreq, String price, String secondreq, String thirdreq, String pkg) {
        this.firstreq = firstreq;
        this.price = price;
        this.secondreq = secondreq;
        this.thirdreq = thirdreq;
        this.pkg = pkg;
    }

    public Cart() {

    }

    public String getFirstreq() {
        return firstreq;
    }

    public void setFirstreq(String firstreq) {
        this.firstreq = firstreq;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSecondreq() {
        return secondreq;
    }

    public void setSecondreq(String secondreq) {
        this.secondreq = secondreq;
    }

    public String getThirdreq() {
        return thirdreq;
    }

    public void setThirdreq(String thirdreq) {
        this.thirdreq = thirdreq;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }
}
