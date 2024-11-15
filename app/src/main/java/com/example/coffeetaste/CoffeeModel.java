package com.example.coffeetaste;

public class CoffeeModel {

    int img;
    String itemName;
    String itemGridients;
    float totalRating;
    float price;
    public CoffeeModel(int img, String itemName, String itemGridients, float totalRating,float price) {
        this.img = img;
        this.itemName = itemName;
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
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
