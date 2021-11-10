/**
 * Created by Jimmy.
 * */

package com.example.recipeapp.favorites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    //may add category
    private static final String TABLE_NAME = "my_favorite_recipes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_IMAGE_URL = "image_URL";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SUMMARY = "summary";
    private static final String COLUMN_NOTES = "notes";

    public FavoritesDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RECIPE_ID + " INTEGER, " +
                COLUMN_IMAGE_URL + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_SUMMARY + " TEXT, " +
                COLUMN_NOTES + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRecipe(int recipe_id, String image_URL, String title, String summary, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RECIPE_ID, recipe_id);
        cv.put(COLUMN_IMAGE_URL, image_URL);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_SUMMARY, summary);
        cv.put(COLUMN_NOTES, notes);

        long result = db.insert(TABLE_NAME, null, cv);
        //if fail to insert data
        if(result == -1){
            Toast.makeText(context, "Failed to add recipe to favorites!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //implementing sorting databasehelper towards the end
    // For now i will make it sorted by most recent added
    public Cursor readAllDataSorted(String col, String order){
        //String query = "SELECT * FROM " + TABLE_NAME+ " Order By "+ col + " "+ order;
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }


    //updates notes on database
    public void updateNotes(String row_id, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOTES, notes);


        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to update notes!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    // helper function to delete a favorited recipe from database
    public void deleteRecipe(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }




    //get count of rows of recipe database
    public long getDatabaseRowCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count;
    }

}
