package com.example.coffeetaste.modelClasses;

public class CoffeeModel {

    int img;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    int itemId;
    String itemName;
    String itemGridients;
    float totalRating;
    float price;



    boolean isFav;
    public CoffeeModel(int img, String itemName, String itemGridients, float totalRating,float price,boolean isFav) {
        this.img = img;
        this.itemName = itemName;
        this.itemGridients = itemGridients;
        this.totalRating = totalRating;
        this.price = price;
        this.isFav = isFav;
    }

    public CoffeeModel() {
    }
    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
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
