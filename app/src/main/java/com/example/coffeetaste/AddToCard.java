package com.example.coffeetaste;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AddToCard extends AppCompatActivity {

    TextView subtotalPrice,shopCoast,taxRate,totalAmount;
    ImageButton backCartBtn;
    RecyclerView recyclerView;
    float itemPrices=0
            ,shopCoaste=0
            ,taxAmount=0
            ,totalPrice=0;
Button orderBtn;


   static ArrayList<CartModel> cartModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_to_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        intializer();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        int imgRes = intent.getIntExtra("itemImage", 0);
        String itemName = intent.getStringExtra("itemName");
        int ingradient1 = intent.getIntExtra("itemIngradient1", 0);
        int ingradient2 = intent.getIntExtra("itemIngradient2", 0);
        float price = intent.getFloatExtra("totalPrice", 0);
        int itemQuantity = intent.getIntExtra("itemQuantity", 0);
        float basicPrice = intent.getFloatExtra("basicPrice", 0);
        int position = intent.getIntExtra("itemPosition", 0);
        int flag = intent.getIntExtra("flag", 0);

        if (itemName == null) {
            Toast.makeText(this, "ItemName is null", Toast.LENGTH_SHORT).show();
        } else if (imgRes == 0) {
            Toast.makeText(this, "ImageResource is not accessible", Toast.LENGTH_SHORT).show();
        } else if (price < 0) {
            Toast.makeText(this, "Invalid prices", Toast.LENGTH_SHORT).show();
        } else if (flag == 1) {
                cartModels.set(position, new CartModel(imgRes, itemName, price, ingradient1, ingradient2, itemQuantity, basicPrice));
        } else {
            cartModels.add(new CartModel(imgRes, itemName, price, ingradient1, ingradient2, itemQuantity, basicPrice));
        }

        backCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             getOnBackPressedDispatcher().onBackPressed();
                finish();
            }
        });
        CartAdapter cartAdapter = new CartAdapter(cartModels,AddToCard.this);
        recyclerView.setAdapter(cartAdapter);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddToCard.this, "Order Done", Toast.LENGTH_SHORT).show();
                cartModels.clear();
                subtotalPrice.setText("$ 0");
                shopCoast.setText("$ 0");
                taxRate.setText("$ 0");
                totalAmount.setText("$ 0");
                cartAdapter.notifyDataSetChanged();

            }

        });

    totalPriceCalculater();

    }

    private void totalPriceCalculater() {
        for (int i = 0; i < cartModels.size(); i++) {
          float currentItemPrice = cartModels.get(i).getPrice();
          shopCoaste =shopCoaste + currentItemPrice * 8/100;
          taxAmount = taxAmount + currentItemPrice * 10/100;
            itemPrices = itemPrices + currentItemPrice;

        }

        DecimalFormat df = new DecimalFormat("#.##");
        subtotalPrice.setText(df.format(itemPrices));
        shopCoast.setText(df.format(shopCoaste));
        taxRate.setText(df.format(taxAmount));
        totalAmount.setText(df.format(totalPrice));


    }

    private void intializer() {
            recyclerView = findViewById(R.id.cardRecycler);
            subtotalPrice = findViewById(R.id.subTotalPrice);
            shopCoast = findViewById(R.id.shopCoastPrice);
            taxRate = findViewById(R.id.taxesPrice);
            totalAmount = findViewById(R.id.totalPrices);
            backCartBtn = findViewById(R.id.addToCartBackBtn);
            orderBtn = findViewById(R.id.orderBtn);
    }
}