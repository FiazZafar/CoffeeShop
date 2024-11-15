package com.example.coffeetaste;

public class CoffeeModel {

    int img;
    String itemNam;
    String itemGridients;
    float totalRating;
    float price;
    public CoffeeModel(int img, String itemName, String itemGridients, float totalRating,float price) {
        this.img = img;
        this.itemNam = itemName;
        this.itemGridients = itemGridients;
        this.totalRating = totalRating;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getItemName() {
        return itemNam;
    }

    public void setItemName(String itemName) {
        this.itemNam = itemName;
    }

    public String getItemGridients() {
        return itemGridients;
    }

    public void setItemGridients(String itemGridients) {
        this.itemGridients = itemGridients;
    }

    public float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(float totalRating) {
        this.totalRating = totalRating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
