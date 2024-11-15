package com.example.coffeetaste;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectedItem extends AppCompatActivity {

    SeekBar seekBarMilk,seekBarShugar;
    TextView seekBarValueMilk,seekBarValueShugar
            ,itemNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selected_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        seekBarMilk = findViewById(R.id.seekBarMilk);
        seekBarValueMilk = findViewById(R.id.seekBarValueMilk);
        seekBarShugar = findViewById(R.id.seekBarShugar);
        seekBarValueShugar = findViewById(R.id.seekBarValueShugar);
        itemNames = findViewById(R.id.flavourNameS);




        Intent intent = new Intent();
      int imgRes =intent.getIntExtra("imgRes",-1);
      String itemName = intent.getStringExtra("itemHead");
      String itemGrad = intent.getStringExtra("itemGrad");
      float price = intent.getFloatExtra("price",-2);


        System.out.println(itemName + itemGrad);

      itemNames.setText(itemGrad);

        seekBarMilk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarValueMilk.setText("Milk:" + i + "%" );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarShugar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarValueShugar.setText("Shugar:" + i + "%" );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}