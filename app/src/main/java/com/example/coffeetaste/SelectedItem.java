package com.example.coffeetaste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
            ,itemNames,totalPrice,addToCart,quantityText;
    Button smallBtn,mediumBtn,largeBtn,increaseQuantiy,decreaseQuantity;
    ImageButton backButton;
    int milkQuanity,shugarQuantity;
    int quantity=1;

    float currentPrice;
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
        totalPrice = findViewById(R.id.totalPrice);
        addToCart = findViewById(R.id.cartBtn);
        increaseQuantiy = findViewById(R.id.addQuantityBtn);
        decreaseQuantity = findViewById(R.id.minusQuantityBtn);
        quantityText = findViewById(R.id.totalQuantity);
        backButton = findViewById(R.id.arrowBackBtnS);

        smallBtn = findViewById(R.id.smallBtn);
        mediumBtn = findViewById(R.id.mediumBtn);
        largeBtn = findViewById(R.id.largeBtn);



        Intent intent = getIntent();
      int imgRes =intent.getIntExtra("imgRes",-1);
      String itemName = intent.getStringExtra("itemHead");
      String itemGrad = intent.getStringExtra("itemGrad");
      float price = intent.getFloatExtra("price",-2);

       currentPrice = price;



        itemNames.setText(itemName);
        totalPrice.setText("$ 0");

        seekBarMilk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarValueMilk.setText("Milk:" + i + "%" );
                milkQuanity = i;
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
                shugarQuantity = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        smallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity=1;
                currentPrice = price;
                quantityText.setText(String.valueOf(quantity));
                totalPrice.setText(("$ "+String.valueOf(currentPrice)));
            }
        });
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity=1;
                currentPrice = (float) (price * 1.5);
                totalPrice.setText(("$ "+String.valueOf(currentPrice)));
            }
        });
        largeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPrice = (float)(price * 2.0);
                quantity=1;
                totalPrice.setText(("$ "+String.valueOf(currentPrice)));
            }
        });

        increaseQuantiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                quantityText.setText(String.valueOf(quantity));
                float totalAmount = currentPrice * quantity;
                totalPrice.setText(("$ "+String.valueOf(totalAmount)));
            }
        });
        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (quantity>1) {
                quantity--;
                float totalAmount = (float) (currentPrice * quantity);
                totalPrice.setText(String.valueOf("$ " + String.valueOf(totalAmount)));
            }
            quantityText.setText(String.valueOf(quantity));
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(SelectedItem.this, AddToCard.class);
            intent.putExtra("itemImage",imgRes);
            intent.putExtra("itemName",itemName);
            intent.putExtra("itemIngradient1",milkQuanity);
            intent.putExtra("itemIngradient2",milkQuanity);
            intent.putExtra("totalPrice",currentPrice);
            intent.putExtra("itemQuantity",itemName);
            startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectedItem.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}