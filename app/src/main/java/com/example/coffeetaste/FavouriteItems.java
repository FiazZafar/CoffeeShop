package com.example.coffeetaste;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetaste.DataBase.Tables.MyDbHelper;
import com.example.coffeetaste.adapters.FavAdapter;
import com.example.coffeetaste.modelClasses.Fav_Item_Model;

import java.util.ArrayList;

public class FavouriteItems extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    MyDbHelper dbHelper;
    ArrayList<Fav_Item_Model> favArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favourite_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




    toolbar = findViewById(R.id.toolFavBar);
    setSupportActionBar(toolbar);

    recyclerView = findViewById(R.id.favRecycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    dbHelper = new MyDbHelper(this);
    favArrayList = dbHelper.fetchFavItemData();
    if (favArrayList!=null){
        FavAdapter adapter = new FavAdapter(favArrayList,this,dbHelper);
        recyclerView.setAdapter(adapter);
    }else {
        Toast.makeText(this, "Fav list is null", Toast.LENGTH_SHORT).show();
    }

    }
    }
