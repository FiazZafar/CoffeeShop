package com.example.coffeetaste.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeetaste.DataBase.Tables.MyDbHelper;
import com.example.coffeetaste.R;
import com.example.coffeetaste.SelectedItem;
import com.example.coffeetaste.modelClasses.CartModel;
import com.example.coffeetaste.modelClasses.CoffeeModel;
import com.example.coffeetaste.modelClasses.Fav_Item_Model;

import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    Context context;
    ArrayList<Fav_Item_Model> favArrayList;
    MyDbHelper myDbHelper;

    public FavAdapter(ArrayList<Fav_Item_Model> favArray, Context context, MyDbHelper myDbHelper){
        this.favArrayList = favArray ;
        this.context = context;
        this.myDbHelper = myDbHelper;
    }
    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favlist_layout,parent,false);
       ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {
        Fav_Item_Model favItemModel = favArrayList.get(position);
        holder.itemImage.setImageResource(favItemModel.getImg());
        holder.itemName.setText(favItemModel.getItemName());
        holder.itemDesc.setText(favItemModel.getItemGridients());
        holder.itemPrice.setText("Price: $" + String.valueOf(favItemModel.getPrice()));
        holder.ratingTxt.setText(String.valueOf(favItemModel.getTotalRating()));

        holder.addToCart_Btn.setOnClickListener(View -> {
            Intent intent = new Intent(context, SelectedItem.class);
            // Use consistent keys
            intent.putExtra("imgRes", favItemModel.getImg());
            intent.putExtra("itemHead", favItemModel.getItemName());
            intent.putExtra("price", favItemModel.getPrice());
            context.startActivity(intent);

        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Dislike Item");
                builder.setMessage("are you sure you want to unlike this item");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDbHelper.deletefavItem(favItemModel);
                        CoffeeModel coffeeModel = new CoffeeModel();
                        coffeeModel.setItemId(favItemModel.getItemId());
                        coffeeModel.setImg(favItemModel.getImg());
                        coffeeModel.setItemName(favItemModel.getItemName());
                        coffeeModel.setItemGridients(favItemModel.getItemGridients());
                        coffeeModel.setTotalRating(favItemModel.getTotalRating());
                        coffeeModel.setPrice(favItemModel.getPrice());
                        coffeeModel.setFav(false);
                        myDbHelper.updateItem(coffeeModel);
                            favArrayList.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(context, "Item Removed",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return favArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName,itemDesc,itemPrice,ratingTxt;
        ImageButton addToCart_Btn;
        ImageView heartFav;
        boolean isFav;
        ViewHolder(View itemView){
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemimage_fav);
            itemName = itemView.findViewById(R.id.itemNamefav);
            itemDesc = itemView.findViewById(R.id.fav_ingradients);
            itemPrice = itemView.findViewById(R.id.fav_price_id);
            ratingTxt = itemView.findViewById(R.id.ratingId);
            heartFav = itemView.findViewById(R.id.fav_btn);
            addToCart_Btn = itemView.findViewById(R.id.fav_add_item_btn);
        }
    }
}
