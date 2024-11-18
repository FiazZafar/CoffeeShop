package com.example.coffeetaste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterOfHome extends RecyclerView.Adapter<CustomAdapterOfHome.ItemView> {

    ArrayList<CoffeeModel> coffeeModelArrayList;
    Context context;
    public CustomAdapterOfHome(ArrayList<CoffeeModel> coffeeModelArrayList1,Context context) {
        this.coffeeModelArrayList = coffeeModelArrayList1;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =  LayoutInflater.from(context).inflate(R.layout.grid_layout_itemlist,parent,false);
        ItemView itemView = new ItemView(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemView holder, int position) {

        CoffeeModel coffeeModel = coffeeModelArrayList.get(position);
        holder.itemPrice.setText("$ " + String.valueOf(coffeeModel.price));
        if (holder.itemImg != null){
            holder.itemImg.setImageResource(coffeeModel.getImg());
        }else {
            Log.e("CustomAdapter","Itemimage is null");
        }
        if (holder.itemHeading != null){
            holder.itemHeading.setText(coffeeModel.getItemName());
        }else {
            Log.e("CustomAdapter","Item Name is null");
        }
        if (holder.itemGradientheading != null){
            holder.itemGradientheading.setText(coffeeModel.getItemGridients());
        }else {
            Log.e("CustomAdapter","item Gradient heading is null");
        }
        if (holder.ratingtxt != null){
            holder.ratingtxt.setText(String.valueOf(coffeeModel.getTotalRating()));
        }else {
            Log.e("CustomAdapter","item ratingtxt is null");
        }

        holder.addTocartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ensure coffeeModel is not null before accessing its fields
                if (coffeeModel != null) {
                    int imgResource = coffeeModel.img;
                    String itemHead = coffeeModel.itemName;
                    String itemGrad = coffeeModel.itemGridients;
                    float price = coffeeModel.price;

                    Intent intent = new Intent(context, SelectedItem.class);

                    // Use consistent keys
                    intent.putExtra("imgRes", imgResource);
                    intent.putExtra("itemHead", itemHead);
                    intent.putExtra("price", price);

                    // Ensure proper context
                    if (context instanceof Activity) {
                        context.startActivity(intent);
                    } else {
                        Log.e("CartAdapter", "Invalid context for starting activity");
                    }
                } else {
                    Log.e("CartAdapter", "CoffeeModel is null");
                }
            }
        });
    }

        @Override
    public int getItemCount() {
        return coffeeModelArrayList.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        ImageView itemImg,ratingStar;
        TextView itemHeading,itemPrice,itemGradientheading,ratingtxt;
        ImageButton addTocartBtn;

        ItemView(View itemView){
            super(itemView);

            itemImg = itemView.findViewById(R.id.item_img);
            itemHeading = itemView.findViewById(R.id.item_name);
            itemGradientheading = itemView.findViewById(R.id.gradientQuantity);
            ratingtxt = itemView.findViewById(R.id.ratingId);
            ratingStar = itemView.findViewById(R.id.ratingStar);
            addTocartBtn = itemView.findViewById(R.id.addToCartBtn);
            itemPrice = itemView.findViewById(R.id.itemPrice);
        }


    }
}
