package com.im_yubi.englsihword;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {
    private static final String DATABASE_NAME = "myDB.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBases.CreateDB._CREATE1);
            db.execSQL(DataBases.CreateDB._CREATE2);
            db.execSQL(DataBases.CreateDB._CREATE3);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME1);
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME2);
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME3);
            onCreate(db);
        }
    }

    public  DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public void open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void create() {
        mDBHelper.onCreate(mDB);
    }

//    public void close(){
//        mDB.close();
//    }

    // Insert DB
    public void officerinsertColumn(String e_word, String k_word){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.E_Word, e_word);
        values.put(DataBases.CreateDB.K_Word, k_word);
        mDB.insert(DataBases.CreateDB._TABLENAME1, null, values);
    }

    public void satinsertColumn(String e_word, String k_word){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.E_Word, e_word);
        values.put(DataBases.CreateDB.K_Word, k_word);
        mDB.insert(DataBases.CreateDB._TABLENAME2, null, values);
    }
    public void toeicinsertColumn(String e_word, String k_word){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.E_Word, e_word);
        values.put(DataBases.CreateDB.K_Word, k_word);
        mDB.insert(DataBases.CreateDB._TABLENAME3, null, values);
    }

//     Delete All
//    public void officerdeleteAllColumns() { mDB.delete(DataBases.CreateDB._TABLENAME1, null, null); }
//    public void satdeleteAllColumns() {
//        mDB.delete(DataBases.CreateDB._TABLENAME2, null, null);
//    }
//    public void toeicdeleteAllColumns() {
//        mDB.delete(DataBases.CreateDB._TABLENAME3, null, null);
//    }
//
//     Delete DB
//    public boolean officerdeleteColumn(long id){
//        return mDB.delete(DataBases.CreateDB._TABLENAME1, "_id="+id, null) > 0;
//    }
//    public boolean satdeleteColumn(long id){
//        return mDB.delete(DataBases.CreateDB._TABLENAME2, "_id="+id, null) > 0;
//    }
//    public boolean toeicdeleteColumn(long id){
//        return mDB.delete(DataBases.CreateDB._TABLENAME3, "_id="+id, null) > 0;
//    }

    // Select DB
    // public Cursor selectColumns(){
    //     return mDB.query(DataBases.CreateDB._TABLENAME, null, null, null, null, null, null);
    // }

    // sort by column
    // public Cursor sortColumn(String sort){
    //     Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
    //     return c;
    // }
}
