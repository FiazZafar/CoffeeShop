package com.example.coffeetaste;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffeetaste.DataBase.Tables.MyDbHelper;
import com.example.coffeetaste.modelClasses.CartModel;

import java.text.DecimalFormat;

public class SelectedItem extends AppCompatActivity {

    SeekBar seekBarMilk,seekBarShugar;
    TextView seekBarValueMilk,seekBarValueShugar
            ,itemNames,totalPrice,addToCart,quantityText;
    Button smallBtn,mediumBtn,largeBtn,increaseQuantiy,decreaseQuantity;
    ImageButton backButton,favBtn;
    int milkQuanity,shugarQuantity,imgRes;
    int quantity=1;
    float totalAmount;
    String itemName,itemGrad;
    static float currentPrice,price;
    int flag,itemPosition;
    DecimalFormat df = new DecimalFormat("#.##");
    RelativeLayout relativeLayout ;

    MyDbHelper dbHelper = new MyDbHelper(this);


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



      initialzers();


      relativeLayout = findViewById(R.id.relativelayout1S);

      favBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              favBtn.setBackgroundColor(Color.RED);
          }
      });

        Intent intent = getIntent();
       imgRes =intent.getIntExtra("imgRes",0);
       itemName = intent.getStringExtra("itemHead");
       price = intent.getFloatExtra("price",0);
        itemPosition = intent.getIntExtra("position",0);
       flag = intent.getIntExtra("itemFlag",0);


       totalAmount = price;
        itemNames.setText(itemName);
        currentPrice = price;
        totalPrice.setText(String.valueOf(currentPrice));

        seekBarsFunc();
        itemSizeFunct();
        quantitySetFunct();
        impButtonsFuct();
    }

    private void impButtonsFuct() {
            addToCart.setOnClickListener(new View.OnClickListener() {

                String itName = itemNames.getText().toString();
                @Override
                public void onClick(View view) {

                    if (milkQuanity < 1) {
                        Toast.makeText(SelectedItem.this, "Add Milk Quantity", Toast.LENGTH_SHORT).show();
                        Log.w("SelectedItem", "Milk quantity is less than 1");
                    } else if (shugarQuantity < 1) {
                        Toast.makeText(SelectedItem.this, "Add Sugar Quantity", Toast.LENGTH_SHORT).show();
                        Log.w("SelectedItem", "Sugar quantity is less than 1");
                    } else if (currentPrice <= 0) {
                        Toast.makeText(SelectedItem.this, "Select size first", Toast.LENGTH_SHORT).show();
                        Log.w("SelectedItem", "Current price is less than 1");
                    } else {
                        dbHelper.insertCarted(new CartModel(imgRes, itName,totalAmount,milkQuanity,shugarQuantity,quantity,price));
                        Intent intent = new Intent(SelectedItem.this, AddToCard.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getOnBackPressedDispatcher().onBackPressed();
                finish();
            }
        });
    }
    private void quantitySetFunct() {
        increaseQuantiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                quantityText.setText(String.valueOf(quantity));
                totalAmount = currentPrice * quantity;
                totalPrice.setText(("$ "+String.valueOf(df.format(totalAmount))));
            }
        });
        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity>1) {
                    quantity--;
                    totalAmount = (float) (currentPrice * quantity);
                    totalPrice.setText(String.valueOf("$ " + String.valueOf(df.format(totalAmount))));
                }
                quantityText.setText(String.valueOf(quantity));
            }
        });

    }
    private void seekBarsFunc() {

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

    }
    private void initialzers(){
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
        favBtn = findViewById(R.id.fav_itemS);
    }
    private void itemSizeFunct(){

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
    }

}