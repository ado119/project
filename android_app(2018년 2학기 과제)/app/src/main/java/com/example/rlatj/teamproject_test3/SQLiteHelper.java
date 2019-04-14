package com.example.rlatj.teamproject_test3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(Context context,
                        String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    //insertData
    public void insertData(String place, String people, Double latitude, Double longtitude,byte[] image){
        SQLiteDatabase database=getWritableDatabase();
        //query to insert record in db table
        String sql="INSERT INTO RECORDS VALUES(NULL,?,?,?,?,?)"; //RECORD IS TABLE NAME AddActivity에서 만들 것

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,place);
        statement.bindString(2,people);
        statement.bindDouble(3,latitude);
        statement.bindDouble(4,longtitude);
        statement.bindBlob(5,image);

        statement.executeInsert();
    }

    public  void updateData(String place, String people, Double latitude, Double longtitude,byte[] image,int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to update records
        String sql= "UPDATE RECORDS SET place=?, people=?, latitude=?, longtitude=?, image=? WHERE id=?";

        SQLiteStatement statement=database.compileStatement(sql);

        statement.bindString(1,place);
        statement.bindString(2,people);
        statement.bindDouble(3,latitude);
        statement.bindDouble(4,longtitude);
        statement.bindBlob(5,image);
        statement.bindDouble(6,(double)id);

        statement.executeInsert();
        database.close();
    }

    //delete Data
    public void deleteData(int id){
        SQLiteDatabase database=getWritableDatabase();
        //  query to delete record using id
        String sql= "DELETE FROM RECORDS WHERE id=?";

        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
