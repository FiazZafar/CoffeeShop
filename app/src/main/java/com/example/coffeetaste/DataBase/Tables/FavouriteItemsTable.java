package com.example.coffeetaste.DataBase.Tables;

public class FavouriteItemsTable {
    public static final String DATABASE_TABLE = "FAVOURITE_Items";            // Primary key ID
    public static final String TABLE_FAV_ID = "favId";            // Primary key ID
    public static final String TABLE_ITEM_ID = "favItemId";            //ItemTABle id(Forign Key)
    public static final String TABLE_USER_ID = "userId";           // User ID (Foreign key, if needed)
    public static final String TABLE_ITEM_NAME = "favItemName";    // Item name
    public static final String TABLE_ITEM_DESC = "favItemDescription"; // Item description
    public static final String TABLE_ITEM_PRICE = "favItemPrice";  // Item price
    public static final String TABLE_ITEM_IMAGE = "favItemImage";  // Item image URL
    public static final String TABLE_ITEM_RATING = "favItemRating"; // Item rating
}
