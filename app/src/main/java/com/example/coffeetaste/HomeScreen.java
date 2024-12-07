package com.example.coffeetaste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetaste.DataBase.Tables.MyDbHelper;
import com.example.coffeetaste.adapters.CustomAdapterOfHome;
import com.example.coffeetaste.modelClasses.CoffeeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {

    RecyclerView recyclerView;

    BottomNavigationView bottomNavigationView;

    private static final String appPref = "appPref";
    private static final String DATA_INSERTED = "dataInserted";

    private SharedPreferences sharedPreferences;

    int spacing = 120;
    MyDbHelper myDbHelper = new MyDbHelper(this);

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

        bottomNavigationView.setSelectedItemId(R.id.home_menu);

        sharedPreferences = getSharedPreferences(appPref, MODE_PRIVATE);
        boolean isInsertedData = sharedPreferences.getBoolean("DataEntered", false);  // Use consistent key

        if (!isInsertedData) {
            // Insert data and mark it as inserted
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("DataEntered", true);  // Use consistent key
            editor.apply();

            // Insert data into the database
            myDbHelper.insertItems(new CoffeeModel(R.drawable.americano_img, "Espressos", "with milk", 3.5F, 4.00F, false));
            myDbHelper.insertItems(new CoffeeModel(R.drawable.capuccinocoffee, "Espressos", "with milk", 3.5F, 4.00F, false));
            myDbHelper.insertItems(new CoffeeModel(R.drawable.americano_img, "Espressos", "with milk", 3.5F, 4.00F, false));
            myDbHelper.insertItems(new CoffeeModel(R.drawable.latte_img, "Espressos", "with milk", 3.5F, 4.00F, false));
            myDbHelper.insertItems(new CoffeeModel(R.drawable.americano_img, "Espressos", "with milk", 3.5F, 4.00F, false));
            myDbHelper.insertItems(new CoffeeModel(R.drawable.americano_img, "Espressos", "with milk", 3.5F, 4.00F, false));
        }

        ArrayList<CoffeeModel> coffeeModelArrayList = myDbHelper.fetchItemData();

        GridLayoutManager gridLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.addItemDecoration(new ItemDec(spacing));
        CustomAdapterOfHome customAdapter = new CustomAdapterOfHome(coffeeModelArrayList, HomeScreen.this,myDbHelper);
        recyclerView.setAdapter(customAdapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.favourite_item_menu) {
                bottomNavigationView.setSelectedItemId(R.id.home_menu);
                Toast.makeText(this, "Welcome to Favourite", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, FavouriteItems.class));
            } else if (itemId == R.id.addToCart_menu) {
                startActivity(new Intent(this, AddToCard.class));
            } else if (itemId == R.id.home_menu) {
                Intent intent = new Intent(this, HomeScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            } else if (itemId == R.id.profile) {
                Toast.makeText(this, "Welcome to profile", Toast.LENGTH_SHORT).show();
                ;
            } else {
                Toast.makeText(this, "Welcome to search bar", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    private long backPressedTime;

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.home_menu) {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                finish(); // Exit app
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
            backPressedTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

}