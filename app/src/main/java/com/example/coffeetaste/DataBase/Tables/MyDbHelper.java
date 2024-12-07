package com.example.coffeetaste.DataBase.Tables;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.coffeetaste.modelClasses.CartModel;
import com.example.coffeetaste.modelClasses.CoffeeModel;
import com.example.coffeetaste.modelClasses.Fav_Item_Model;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StoreItems";
    private static final int DATABASE_VERSION = 2;

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + ItemsTable.DATABASE_TABLE
                + " ( "
                + ItemsTable.ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemsTable.TABLE_ITEM_NAME + " TEXT NOT NULL, "
                + ItemsTable.TABLE_ITEM_DESC + " TEXT, "
                + ItemsTable.TABLE_ITEM_RATING + " REAL , "
                + ItemsTable.TABLE_ITEM_PRICE + " REAL NOT NULL, "
                + ItemsTable.TABLE_ITEM_IMAGE + " INTEGER, "
                + ItemsTable.TABLE_ITEM_FAV + " INTEGER "
                + " ) ");
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + FavouriteItemsTable.DATABASE_TABLE + " ( "
                + FavouriteItemsTable.TABLE_FAV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FavouriteItemsTable.TABLE_ITEM_ID + " INTEGER NOT NULL, "
                + FavouriteItemsTable.TABLE_USER_ID + " INTEGER NOT NULL, "
                + FavouriteItemsTable.TABLE_ITEM_NAME + " TEXT NOT NULL, "
                + FavouriteItemsTable.TABLE_ITEM_DESC + " TEXT, "
                + FavouriteItemsTable.TABLE_ITEM_RATING + " REAL, "
                + FavouriteItemsTable.TABLE_ITEM_PRICE + " REAL NOT NULL, "
                + FavouriteItemsTable.TABLE_ITEM_IMAGE + " TEXT, "   // Assuming image is stored as a URL
                + "FOREIGN KEY (" + FavouriteItemsTable.TABLE_USER_ID + ") REFERENCES Users(userId), "
                + "FOREIGN KEY (" + FavouriteItemsTable.TABLE_ITEM_ID + ") REFERENCES ItemsTable(itemId) "
                + ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + CartedItemTable.DATABASE_TABLE
                + " ( "
                + CartedItemTable.TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CartedItemTable.TABLE_ITEM_NAME + " TEXT NOT NULL, "
                + CartedItemTable.TABLE_ITEM_INGR1 + " INTEGER, "
                + CartedItemTable.TABLE_ITEM_INGR2 + " INTEGER, "
                + CartedItemTable.TABLE_ITEM_PRICE + " REAL NOT NULL, "
                + CartedItemTable.TABLE_ITEM_IMAGE + " INTEGER, "
                + CartedItemTable.TABLE_ITEM_QUANTITY + " INTEGER "
                + " ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ItemsTable.DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FavouriteItemsTable.DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CartedItemTable.DATABASE_TABLE);
    }

    public void insertItems(CoffeeModel coffeeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ItemsTable.TABLE_ITEM_IMAGE, coffeeModel.getImg());
        cv.put(ItemsTable.TABLE_ITEM_NAME, coffeeModel.getItemName());
        cv.put(ItemsTable.TABLE_ITEM_DESC, coffeeModel.getItemGridients());
        cv.put(ItemsTable.TABLE_ITEM_RATING, coffeeModel.getTotalRating());
        cv.put(ItemsTable.TABLE_ITEM_PRICE, coffeeModel.getPrice());
        cv.put(ItemsTable.TABLE_ITEM_FAV, coffeeModel.isFav());

        db.insert(ItemsTable.DATABASE_TABLE, null, cv);
        db.close();
    }
    public void insertFavItems(Fav_Item_Model favItemModel) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cv = new ContentValues();
        cv.put(FavouriteItemsTable.TABLE_ITEM_IMAGE, favItemModel.getImg());
        cv.put(FavouriteItemsTable.TABLE_ITEM_ID, favItemModel.getItemId());
        cv.put(FavouriteItemsTable.TABLE_USER_ID, favItemModel.getUserId());
        cv.put(FavouriteItemsTable.TABLE_ITEM_NAME, favItemModel.getItemName());
        cv.put(FavouriteItemsTable.TABLE_ITEM_DESC, favItemModel.getItemGridients());
        cv.put(FavouriteItemsTable.TABLE_ITEM_RATING, favItemModel.getTotalRating());
        cv.put(FavouriteItemsTable.TABLE_ITEM_PRICE, favItemModel.getPrice());

        db.insert(FavouriteItemsTable.DATABASE_TABLE, null, cv);
        db.close();
    }
    public void insertCarted(CartModel cartModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CartedItemTable.TABLE_ITEM_IMAGE, cartModel.getImgRes());
        cv.put(CartedItemTable.TABLE_ITEM_NAME, cartModel.getItemName());
        cv.put(CartedItemTable.TABLE_ITEM_INGR1, cartModel.getIngredient1());
        cv.put(CartedItemTable.TABLE_ITEM_INGR2, cartModel.getIngredient2());
        cv.put(CartedItemTable.TABLE_ITEM_PRICE, cartModel.getPrice());
        cv.put(CartedItemTable.TABLE_ITEM_QUANTITY, cartModel.getQuantity());
        db.insert(CartedItemTable.DATABASE_TABLE, null, cv);
        db.close();
    }

    //fetching data operations
    public ArrayList<CoffeeModel> fetchItemData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CoffeeModel> coffeeModelArrayList = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Query to fetch all data from the table
            cursor = db.query(ItemsTable.DATABASE_TABLE, null, null,null,null,null,null);

            // Iterate through the cursor to populate the CoffeeModel list
            while (cursor.moveToNext()) {
                CoffeeModel coffeeModel = new CoffeeModel();
                coffeeModel.setItemId( cursor.getInt(0));
                coffeeModel.setItemName( cursor.getString(1));
                coffeeModel.setItemGridients( cursor.getString(2));
                coffeeModel.setTotalRating(cursor.getFloat(3));
                coffeeModel.setPrice(cursor.getFloat(4));
                coffeeModel.setImg(cursor.getInt(5));
                coffeeModel.setFav(cursor.getInt(6)==1);
                coffeeModelArrayList.add(coffeeModel); // Add to the list

                Log.d("FETCH_DATA", "Fetched item with ID: " + coffeeModel.getItemId());

            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor
            }
            db.close(); // Close the database
        }

        return coffeeModelArrayList; // Return the list
    }
    public ArrayList<Fav_Item_Model> fetchFavItemData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Fav_Item_Model> favItemModelArrayList = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Query to fetch all data from the table
            cursor = db.query(FavouriteItemsTable.DATABASE_TABLE, null, null,null,null,null,null);

            // Iterate through the cursor to populate the CoffeeModel list
            while (cursor.moveToNext()) {
                Fav_Item_Model favItemModel = new Fav_Item_Model();

                favItemModel.setItemId( cursor.getInt(1));
                favItemModel.setUserId( cursor.getInt(2));
                favItemModel.setItemName( cursor.getString(3));
                favItemModel.setItemGridients( cursor.getString(4));
                favItemModel.setTotalRating(cursor.getFloat(5));
                favItemModel.setPrice(cursor.getFloat(6));
                favItemModel.setImg(cursor.getInt(7));
                favItemModelArrayList.add(favItemModel); // Add to the list
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor
            }
            db.close(); // Close the database
        }

        return favItemModelArrayList; // Return the list
    }
    public ArrayList<CartModel> fetchCartItemData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CartModel> cartModelArrayList = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Query to fetch all data from the table
            cursor = db.query(CartedItemTable.DATABASE_TABLE, null, null,null,null,null,null);

            // Iterate through the cursor to populate the CoffeeModel list
            while (cursor.moveToNext()) {
                CartModel cartModel = new CartModel();
                cartModel.setItemName( cursor.getString(1));
                cartModel.setIngredient1( cursor.getInt(2));
                cartModel.setIngredient2( cursor.getInt(3));
                cartModel.setPrice(cursor.getFloat(4));
                cartModel.setImgRes(cursor.getInt(5));
                cartModel.setQuantity(cursor.getInt(6));
                cartModelArrayList.add(cartModel); // Add to the list
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor
            }
            db.close(); // Close the database
        }

        return cartModelArrayList; // Return the list
    }


    //Update Operations
    public void updateItem(CoffeeModel coffeeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Put all the updated values
        cv.put(ItemsTable.TABLE_ITEM_NAME, coffeeModel.getItemName());
        cv.put(ItemsTable.TABLE_ITEM_DESC, coffeeModel.getItemGridients());
        cv.put(ItemsTable.TABLE_ITEM_RATING, coffeeModel.getTotalRating());
        cv.put(ItemsTable.TABLE_ITEM_PRICE, coffeeModel.getPrice());
        cv.put(ItemsTable.TABLE_ITEM_IMAGE, coffeeModel.getImg());
        cv.put(ItemsTable.TABLE_ITEM_FAV, coffeeModel.isFav() ? 1 : 0);

        // Check if the ID is valid
        if (coffeeModel.getItemId() > 0) {
            // Update the item using ITEM_ID
            int rowsAffected = db.update(ItemsTable.DATABASE_TABLE, cv,
                    ItemsTable.ITEM_ID + " = ?", new String[]{String.valueOf(coffeeModel.getItemId())});

            Log.d("DB_UPDATE", "Rows updated: " + rowsAffected);
        } else {
            Log.e("DB_UPDATE", "Invalid item ID: " + coffeeModel.getItemId());
        }

        db.close();
    }

    public void updateFavItem(Fav_Item_Model favItemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FavouriteItemsTable.TABLE_ITEM_NAME,favItemModel.getItemName());
        cv.put(FavouriteItemsTable.TABLE_ITEM_DESC,favItemModel.getItemGridients());
        cv.put(FavouriteItemsTable.TABLE_ITEM_RATING,favItemModel.getTotalRating());
        cv.put(FavouriteItemsTable.TABLE_ITEM_PRICE,favItemModel.getPrice());
        cv.put(FavouriteItemsTable.TABLE_ITEM_IMAGE,favItemModel.getImg());
        db.update(FavouriteItemsTable.DATABASE_TABLE,cv,
                FavouriteItemsTable.TABLE_FAV_ID + " = " + favItemModel.getItemId(),null);
        db.close();
    }
    public void updateCartItem(CartModel cartModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CartedItemTable.TABLE_ITEM_NAME,cartModel.getItemName());
        cv.put(CartedItemTable.TABLE_ITEM_INGR1,cartModel.getIngredient1());
        cv.put(CartedItemTable.TABLE_ITEM_INGR2,cartModel.getIngredient2());
        cv.put(CartedItemTable.TABLE_ITEM_PRICE,cartModel.getPrice());
        cv.put(CartedItemTable.TABLE_ITEM_IMAGE,cartModel.getImgRes());
        db.update(CartedItemTable.DATABASE_TABLE,cv,
                CartedItemTable.TABLE_ID + " = " + cartModel.getItemId(),null);
        db.close();
    }

    //Delete Operations
    public  void  deleteItem(CoffeeModel coffeeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ItemsTable.DATABASE_TABLE,
                ItemsTable.ITEM_ID + " = " + coffeeModel.getItemId(),null);
        db.close();
    }
    public  void  deletefavItem(Fav_Item_Model favItemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FavouriteItemsTable.DATABASE_TABLE,
                FavouriteItemsTable.TABLE_ITEM_ID + " = " + favItemModel.getItemId(),null);
        db.close();
    }
    public  void  deleteCartItem(CartModel cartModel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CartedItemTable.DATABASE_TABLE,
                CartedItemTable.TABLE_ID + " = " + cartModel.getItemId(),null);
        db.close();
    }


}
