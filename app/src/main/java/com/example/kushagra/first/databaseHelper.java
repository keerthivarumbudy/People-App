package com.example.kushagra.first;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kushagra on 04-04-2018.
 */

public class databaseHelper extends SQLiteOpenHelper {

    public static final String databaseName= "history";
    public static final String tableName= "History_table";
    public static final String COL_1= "name";
    public static final String COL_2= "history";




    public databaseHelper(Context context) {
        super(context, databaseName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tableName +" (name TEXT,history TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);
    }

    public boolean insertData(String name, String history){

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,history);
        long result = db.insert(tableName,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;


    }


    public Cursor getListContents(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data= db.rawQuery(" SELECT * FROM " + tableName,null);
        return data;
    }

}
