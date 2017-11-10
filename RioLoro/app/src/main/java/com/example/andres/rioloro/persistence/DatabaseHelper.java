package com.example.andres.rioloro.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by usuario on 25/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "species_table";
    private static final String COL0 = "id";
    private static final String COL1 = "nombre";
    private static final String COL2 = "linea";
    private static final String COL3 = "fechaHora";
    private static final String COL4 = "imageUrl";

    public DatabaseHelper(Context context) {
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL1 + " TEXT, "+ COL2 + " TEXT, "+ COL3 + " TEXT, "+COL4+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String especie,String linea, String fechaHora,String imageUrl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,especie);
        contentValues.put(COL2,linea);
        contentValues.put(COL3,fechaHora);
        contentValues.put(COL4,imageUrl);

        Log.d(TAG,"addData: Adding "+especie+" to "+TABLE_NAME);

        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();

        /*Si el dato se inserta de manera incorrecta se retorna un -1*/
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return  data;
    }
}
