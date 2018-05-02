package com.example.bloodbank.bloodbank;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";
    public static final String TABLE_NAME = "database_table";
    public static final String COL_1 = "UserName";
    public static final String COL_2 = "Password";
    public static final String COL_3 = "Name";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Address";
    public static final String COL_6 = "Phone";
    public static final String COL_7 = "Government";
    public static final String COL_8 = "Age";
    public static final String COL_9 = "Sex";
    public static final String COL_10 = "Donor";
    public static final String COL_11 = "KindBlood";
    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (UserName TEXT PRIMARY KEY,Password TEXT,NAME TEXT,Address TEXT,Email TEXT,Phone INTEGER,Government TEXT,Age INTEGER,Sex TEXT,Donor TEXT,KindBlood CHAR(4))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String UserName,String Password,String Name ,String Email,String Address,String Phone,String Gov,String Age,String Sex,String Donor,String KindBlood ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,UserName);
        contentValues.put(COL_2,Password);
        contentValues.put(COL_3,Name);
        contentValues.put(COL_4,Email);
        contentValues.put(COL_5,Address);
        contentValues.put(COL_6,Phone);
        contentValues.put(COL_7,Gov);
        contentValues.put(COL_8,Age);
        contentValues.put(COL_9,Sex);
        contentValues.put(COL_10,Donor);
        contentValues.put(COL_11,KindBlood);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updateData(String UserName,String Password,String Name ,String Email,String Address,String Phone,String Gov,String Age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,UserName);
        contentValues.put(COL_2,Password);
        contentValues.put(COL_3,Name);
        contentValues.put(COL_4,Email);
        contentValues.put(COL_5,Address);
        contentValues.put(COL_6,Phone);
        contentValues.put(COL_7,Gov);
        contentValues.put(COL_8,Age);
        db.update(TABLE_NAME, contentValues, "UserName = ?",new String[] { UserName });
        return true;
    }


}
