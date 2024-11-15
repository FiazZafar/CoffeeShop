package com.example.coffeetaste;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    RecyclerView recyclerView;

    int spacing = 120;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.homeRecycler);



        GridLayoutManager gridLayout = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.addItemDecoration(new ItemDec(spacing));
        CustomAdapterOfHome customAdapter = new CustomAdapterOfHome(new GridCoffeList().allCoffees(),HomeScreen.this);
        recyclerView.setAdapter(customAdapter);


    }

}