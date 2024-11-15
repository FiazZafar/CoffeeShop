package com.example.coffeetaste;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class GridCoffeList {

    public ArrayList<CoffeeModel> allCoffees(){
        ArrayList<CoffeeModel> list = new ArrayList<>();
        list.add(new CoffeeModel(R.drawable.americano_img,"Espressos","with milk",3.5F,4.00F));
        list.add(new CoffeeModel(R.drawable.capuccinocoffee,"Cappuccino","with 2% milk",4.5F,3.00F));
        list.add(new CoffeeModel(R.drawable.capuccinocoffee,"Lattee","with bakery",4.5F,2.40F));
        list.add(new CoffeeModel(R.drawable.americano_img,"Americano","with milk",2.5F,2.80F));
        list.add(new CoffeeModel(R.drawable.americano_img,"Espresso","with milk",3.5F,3.5F));
        list.add(new CoffeeModel(R.drawable.capuccinocoffee,"Cappuccino","with 2% milk",4.5F,5.00F));
        list.add(new CoffeeModel(R.drawable.capuccinocoffee,"Lattee","with bakery",4.5F,4.10F));
        list.add(new CoffeeModel(R.drawable.americano_img,"Americano","with milk",2.5F,3.50F));
        return list;
    }
}
