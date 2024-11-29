package com.example.coffeetaste;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetaste.DataBase.Tables.MyDbHelper;
import com.example.coffeetaste.modelClasses.CoffeeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    RecyclerView recyclerView;

    BottomNavigationView bottomNavigationView;
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
        bottomNavigationView = findViewById(R.id.navigation);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setItemIconSize(48); // Example size
        bottomNavigationView.setPadding(0, 0, 0, 0); // Remove extra padding


        MyDbHelper myDbHelper = new MyDbHelper(this);
        myDbHelper.insertData(new CoffeeModel(R.drawable.americano_img,"Espressos","with milk",3.5F,4.00F),this);
        myDbHelper.insertData(new CoffeeModel(R.drawable.americano_img,"Espressos","with milk",3.5F,4.00F),this);

        ArrayList<CoffeeModel> coffeeModelArrayList = myDbHelper.fetchItemData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        GridLayoutManager gridLayout = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL,false);
          GridLayoutManager gridLayout = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.addItemDecoration(new ItemDec(spacing));
        CustomAdapterOfHome customAdapter = new CustomAdapterOfHome(coffeeModelArrayList,HomeScreen.this);
        recyclerView.setAdapter(customAdapter);



    }

}