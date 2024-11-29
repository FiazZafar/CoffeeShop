package com.example.coffeetaste.modelClasses;

public class CartModel {
    int imgRes;
    String itemName;
    int ingredient1;
    int ingredient2;
    float price;
    float basicPrice;
    int quantity;

    public CartModel(int imgRes, String itemName, float price, int ingredient1,int ingredient2, int quantity,float basicPrice) {
        this.imgRes = imgRes;
        this.itemName = itemName;
        this.price = price;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.quantity = quantity;
        this.basicPrice = basicPrice;
    }
    public float getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(float basicPrice) {
        this.basicPrice = basicPrice;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(int ingredient1) {
        this.ingredient1 = ingredient1;
    }
    public int getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(int ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
