package com.example.coffeetaste.DataBase.Tables;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.graphics.drawable.Drawable;

import com.example.coffeetaste.modelClasses.CoffeeModel;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StoreItems";
    private static final int DATABASE_VERSION = 1;
    private ItemsTable itemsTable = new ItemsTable();

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "
                + itemsTable.DATABASE_TABLE
                + " ( "
                + itemsTable.TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + itemsTable.TABLE_ITEM_NAME + " TEXT NOT NULL, "
                + itemsTable.TABLE_ITEM_DESC + " TEXT, "
                + itemsTable.TABLE_ITEM_RATING + " REAL , "
                + itemsTable.TABLE_ITEM_PRICE + " REAL NOT NULL, "
                + itemsTable.TABLE_ITEM_IMAGE + " INTEGER "
                + " ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + itemsTable.DATABASE_TABLE);
    }

    public void insertData(CoffeeModel coffeeModel, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        Drawable drawable = context.getResources().getDrawable(coffeeModel.getImg());

        ContentValues cv = new ContentValues();
        cv.put(itemsTable.TABLE_ITEM_IMAGE, coffeeModel.getImg());
        cv.put(itemsTable.TABLE_ITEM_NAME, coffeeModel.getItemName());
        cv.put(itemsTable.TABLE_ITEM_DESC, coffeeModel.getItemGridients());
        cv.put(itemsTable.TABLE_ITEM_RATING, coffeeModel.getTotalRating());
        cv.put(itemsTable.TABLE_ITEM_PRICE, coffeeModel.getPrice());

        db.insert(itemsTable.DATABASE_TABLE, null, cv);
    }

    public ArrayList<CoffeeModel> fetchItemData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<CoffeeModel> coffeeModelArrayList = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Query to fetch all data from the table
            cursor = db.rawQuery("SELECT * FROM " + itemsTable.DATABASE_TABLE, null);

            // Iterate through the cursor to populate the CoffeeModel list
            while (cursor.moveToNext()) {
                CoffeeModel coffeeModel = new CoffeeModel();
                coffeeModel.setItemName( cursor.getString(1));
                coffeeModel.setItemGridients( cursor.getString(2));
                coffeeModel.setTotalRating(cursor.getFloat(3));
                coffeeModel.setPrice(cursor.getFloat(4));
                coffeeModel.setImg(cursor.getInt(5));
                coffeeModelArrayList.add(coffeeModel); // Add to the list
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

    public void updateItem(CoffeeModel coffeeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(itemsTable.TABLE_ITEM_NAME,coffeeModel.getItemName());
        cv.put(itemsTable.TABLE_ITEM_DESC,coffeeModel.getItemGridients());
        cv.put(itemsTable.TABLE_ITEM_RATING,coffeeModel.getTotalRating());
        cv.put(itemsTable.TABLE_ITEM_PRICE,coffeeModel.getPrice());
        cv.put(itemsTable.TABLE_ITEM_IMAGE,coffeeModel.getImg());
        db.update(itemsTable.DATABASE_TABLE,cv, itemsTable.TABLE_ID + " = " + coffeeModel.getItemId(),null);
    }
    public  void  deleteItem(CoffeeModel coffeeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(itemsTable.DATABASE_TABLE,itemsTable.TABLE_ID + " = " + coffeeModel.getItemId(),null);
    }


}
