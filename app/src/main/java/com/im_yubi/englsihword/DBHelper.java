package com.im_yubi.englsihword;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper {
    private static final String DATABASE_NAME = "VocaApp(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
        db.execSQL(DataBases.CreateDB._CREATE0);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ DataBases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DBHelper(Context context){
        this.mCtx = context;
    }

    public void open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        Log.i("123","DB를 Open했습니다.");
    }

    public void create(){
        mDBHelper.onCreate(mDB);
        Log.i("123","DB를 Create했습니다.");
    }

    public void close(){
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String e_word, String k_word){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.e_word, e_word);
        values.put(DataBases.CreateDB.k_word, k_word);
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String e_word, String k_word){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.e_word, e_word);
        values.put(DataBases.CreateDB.k_word, k_word);
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }
    // Select DB
    public Cursor selectColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }
}


//public class DBHelper extends SQLiteOpenHelper {
//    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE if not exists wordtable ("
//                + "ID int primary key autoincrement,"
//                + "e_word text not null,"
//                + "k_word text not null"
//                + ");";
//        db.execSQL(sql);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "DROP TABLE if exists wordtable";
//        db.execSQL(sql);
//        onCreate(db);
//    }
//
//}