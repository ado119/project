package org.techtown.notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MemoDBHelper extends SQLiteOpenHelper {
    private static MemoDBHelper sInstance;

    private static final int DB_VERSION =1;
    private static final String DB_NAME = "Memo.db";
    private static final String SQL_CREATE_ENTRIES =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                    memo_warehouse.MemoEntry.TABLE_NAME,
                    memo_warehouse.MemoEntry._ID,
                    memo_warehouse.MemoEntry.COLUMN_TITLE,
                    memo_warehouse.MemoEntry.COLUMN_CONTENTS);

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + memo_warehouse.MemoEntry.TABLE_NAME;

    public static MemoDBHelper getsInstance(Context context){
        if (sInstance == null){
            sInstance = new MemoDBHelper(context);
        }
        return sInstance;
    }

    public MemoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
