package com.example.coffeetaste;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
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
        holder.itemImg.setImageResource(coffeeModel.getImg());
        holder.itemHeading.setText(coffeeModel.getItemName());
        holder.itemGradientheading.setText(coffeeModel.getItemGridients());
        holder.ratingtxt.setText(String.valueOf(coffeeModel.getTotalRating()));

        holder.addTocartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int imgRes = coffeeModel.img;
                String itemHead = coffeeModel.getItemName();
                String itemGrad = coffeeModel.itemGridients;
                float price = coffeeModel.price;

                Intent intent = new Intent(context, SelectedItem.class);

                intent.putExtra("itemRes",imgRes);
                intent.putExtra("itemHead",itemHead);
                intent.putExtra("itemGrad",itemGrad);
                intent.putExtra("price",price);
                context.startActivity(intent);
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
