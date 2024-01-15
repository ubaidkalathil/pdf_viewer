package com.example.testproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pdf_database";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PDFS = "pdfs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PDF_DATA = "pdf_data";
    public static final String COLUMN_PDF_ID = "pdf_data";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PDFS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PDF_DATA + COLUMN_PDF_ID + " BLOB);";

    public DBHelper(@Nullable Context context) {
        super(context, TABLE_PDFS , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
//        DB.execSQL("create Table Userdatas(name TEXT primary key, url TEXT, id TEXT)");
        DB.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
;
    }
    public  Boolean insetruserdata(byte[] pdfData,String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PDF_DATA,pdfData);
        contentValues.put(COLUMN_PDF_DATA,id);
        long result = DB.insert(TABLE_PDFS ,null,contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

//    public  Boolean updateuserdata(String name,String id,String url){
//        SQLiteDatabase DB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name",name);
//        contentValues.put("url",url);
//        contentValues.put("id",id);
//        long result = DB.update("Userdatas",null,contentValues);
//
//        if (result == -1){
//            return false;
//        }else{
//            return true;
//        }
//    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from "+TABLE_PDFS,null);
        return  cursor;
    }
}
