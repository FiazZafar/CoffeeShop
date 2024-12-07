package com.example.coffeetaste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetaste.DataBase.Tables.MyDbHelper;
import com.example.coffeetaste.adapters.CartAdapter;
import com.example.coffeetaste.modelClasses.CartModel;

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
    ArrayList<CartModel> cartModelArrayList;
    MyDbHelper dbHelper = new MyDbHelper(this);
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


          cartModelArrayList = dbHelper.fetchCartItemData();
        backCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             getOnBackPressedDispatcher().onBackPressed();
                finish();
            }
        });
        CartAdapter cartAdapter = new CartAdapter(cartModelArrayList,AddToCard.this);
        recyclerView.setAdapter(cartAdapter);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddToCard.this, "Order Done", Toast.LENGTH_SHORT).show();
                cartModelArrayList.clear();
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
        for (int i = 0; i < cartModelArrayList.size(); i++) {
          float currentItemPrice = cartModelArrayList.get(i).getPrice();
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