package com.example.coffeetaste;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<CartModel> arrayList;
    CartAdapter(ArrayList<CartModel> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_recycler_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel itemPosition = arrayList.get(position);
        Log.d("CartAdapter", "Binding position: " + position);

        if (holder.itemImage != null) {
            holder.itemImage.setImageResource(itemPosition.getImgRes());
        } else {
            Log.e("CartAdapter", "itemImage ImageView is null");
        }
        if (holder.itemName != null) {
            holder.itemName.setText(itemPosition.getItemName());
        } else {
            Log.e("CartAdapter", "itemName TextView is null");
        }
        if (holder.itemGrad != null){
            holder.itemGrad.setText("with milk " + String.valueOf(itemPosition.getIngredient1()) + "%,\n" + "with shugar" + String.valueOf(itemPosition.getIngredient2()) + "%");
        }else {
            Log.e("CartAdapter","ItemGradient TextView is null");
        }
        if (holder.itemPrice != null){
            holder.itemPrice.setText("Price: $ " + String.valueOf(itemPosition.getPrice()));
        }else {
            Log.e("CartAdapter","ItemPrice TextView is null");
        }
        if (holder.itemQuantity != null){
            holder.itemQuantity.setText("Quantity: " + String.valueOf(itemPosition.getQuantity()));
        }else {
            Log.e("CartAdapter","ItemQuantity TextView is null");
        }
        holder.itemIngradient.setText(String.valueOf("with milk " + itemPosition.ingredient1 + "%,\n with shugar " + itemPosition.ingredient2  + "%"));
        holder.editbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentImage = itemPosition.getImgRes();
                String itemName = itemPosition.getItemName();
                float price = itemPosition.basicPrice;
//                int quantity = itemPosition.getQuantity();
                int itemPosition = position;
                int flag = 1;

                Intent intent = new Intent(context, SelectedItem.class);
                intent.putExtra("imgRes", currentImage);
                intent.putExtra("itemHead", itemName);
                intent.putExtra("position",itemPosition);
                intent.putExtra("price",price);
                intent.putExtra("itemFlag",flag);
                context.startActivity(intent);
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName,itemGrad,itemPrice,itemQuantity,itemIngradient;
        ImageView itemImage;
        ImageButton editbuton;
        ViewHolder(View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.itemNameCart);
            itemQuantity = itemView.findViewById(R.id.cart_quantity_id);
            itemPrice = itemView.findViewById(R.id.cart_price_id);
            editbuton = itemView.findViewById(R.id.cart_edit_item_btn);
            itemImage = itemView.findViewById(R.id.itemimage_cart);
            itemIngradient = itemView.findViewById(R.id.cart_ingradients);
        }

    }
}
