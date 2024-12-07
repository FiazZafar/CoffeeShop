package com.example.coffeetaste.adapters;

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

import com.example.coffeetaste.DataBase.Tables.MyDbHelper;
import com.example.coffeetaste.R;
import com.example.coffeetaste.SelectedItem;
import com.example.coffeetaste.modelClasses.CoffeeModel;
import com.example.coffeetaste.modelClasses.Fav_Item_Model;

import java.util.ArrayList;

public class CustomAdapterOfHome extends RecyclerView.Adapter<CustomAdapterOfHome.ItemView> {

    ArrayList<CoffeeModel> coffeeModelArrayList;
    Context context;
    MyDbHelper dbHelper;
    public CustomAdapterOfHome(ArrayList<CoffeeModel> coffeeModelArrayList1, Context context, MyDbHelper dbHelper) {
        this.coffeeModelArrayList = coffeeModelArrayList1;
        this.context = context;
        this.dbHelper = dbHelper;
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
        holder.itemPrice.setText("$ " + String.valueOf(coffeeModel.getPrice()));
        holder.isfav = coffeeModel.isFav();
        int userId=1;

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

        if (holder.isfav == false){
            holder.favBtn.setImageResource(R.drawable.heart);
        }else {
            holder.favBtn.setImageResource(R.drawable.heart_filled);
        }
        holder.addTocartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ensure coffeeModel is not null before accessing its fields
                if (coffeeModel != null) {
                    int imgResource = coffeeModel.getImg();
                    String itemHead = coffeeModel.getItemName();
                    String itemGrad = coffeeModel.getItemGridients();
                    float price = coffeeModel.getPrice();

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
        holder.favBtn.setOnClickListener(view ->{
            if (holder.isfav == false){
                holder.favBtn.setImageResource(R.drawable.heart_filled);
                holder.isfav = true;
                CoffeeModel coffeeModel1 = new CoffeeModel();
                coffeeModel1.setItemName(coffeeModel.getItemName());
                coffeeModel1.setItemGridients(coffeeModel.getItemGridients());
                coffeeModel1.setTotalRating(coffeeModel.getTotalRating());
                coffeeModel1.setPrice(coffeeModel.getPrice());
                coffeeModel1.setImg(coffeeModel.getImg());
                coffeeModel1.setItemId(coffeeModel.getItemId());
                coffeeModel1.setFav(holder.isfav);
                dbHelper.updateItem(coffeeModel1);
                Log.d("DB_UPDATE", "Updating item with ID: " + coffeeModel.getItemId());


               Fav_Item_Model favItemModel = new Fav_Item_Model(coffeeModel1.getImg(),coffeeModel1.getItemId(),userId, coffeeModel1.getItemName(), coffeeModel1.getItemGridients(),
                       coffeeModel1.getTotalRating(),coffeeModel1.getPrice());
               dbHelper.insertFavItems(favItemModel);
            } else {

                holder.favBtn.setImageResource(R.drawable.heart);
                holder.isfav = false;

                coffeeModel.setItemName(coffeeModel.getItemName());
                coffeeModel.setItemGridients(coffeeModel.getItemGridients());
                coffeeModel.setTotalRating(coffeeModel.getTotalRating());
                coffeeModel.setPrice(coffeeModel.getPrice());
                coffeeModel.setImg(coffeeModel.getImg());
                coffeeModel.setItemId(coffeeModel.getItemId());
                coffeeModel.setFav(holder.isfav);
                dbHelper.updateItem(coffeeModel);

            }
        });
    }

        @Override
    public int getItemCount() {
        return coffeeModelArrayList.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        ImageView itemImg,ratingStar,favBtn;
        TextView itemHeading,itemPrice,itemGradientheading,ratingtxt;
        ImageButton addTocartBtn;
        boolean isfav;
        ItemView(View itemView){
            super(itemView);

            itemImg = itemView.findViewById(R.id.item_img);
            itemHeading = itemView.findViewById(R.id.item_name);
            itemGradientheading = itemView.findViewById(R.id.gradientQuantity);
            ratingtxt = itemView.findViewById(R.id.ratingId);
            ratingStar = itemView.findViewById(R.id.ratingStar);
            addTocartBtn = itemView.findViewById(R.id.addToCartBtn);
            favBtn = itemView.findViewById(R.id.fav_btn);
            itemPrice = itemView.findViewById(R.id.itemPrice);
        }


    }
}
