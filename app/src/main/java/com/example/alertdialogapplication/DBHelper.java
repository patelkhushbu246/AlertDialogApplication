package com.example.alertdialogapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="logindb";
    private static final String TABLE_REG="user";
    private static final String KEY_ID="ID";
    private static final String KEY_UNAME="Uname";
    private static final String KEY_PASS="Password";
    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_REG+"("+KEY_ID+" INTEGER PRIMARY KEY ,"+KEY_UNAME+" TEXT,"+KEY_PASS+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_REG+"");
        onCreate(db);
    }
    public long adduser(String uname,String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Uname",uname);
        cv.put("Password",pass);
        long res=db.insert(TABLE_REG,null,cv);
        db.close();
        return res;
    }
    public boolean checklogin(String username, String password){
        String[] column={KEY_ID};
        SQLiteDatabase db=this.getReadableDatabase();
        String selection=KEY_UNAME+"=?"+"AND "+KEY_PASS+"=?";
        String[] selectionArgs={username,password};
        Cursor c=db.query("user",column,selection,selectionArgs,null,null,null);
        int res=c.getCount();
        db.close();
        if(res>0) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkuser(String username){
        String[] column={KEY_ID};
        SQLiteDatabase db= this.getReadableDatabase();
        String selection=KEY_UNAME+"=?";
        String[] selectionArgs={username};
        Cursor c=db.query("user",column,selection,selectionArgs,null,null,null);
        int res=c.getCount();
        db.close();
        if(res>0) {
            return true;
        }
        else {
            return false;
        }

    }
    public boolean updatepass(String uname, String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Password",pass);
        Cursor c=db.rawQuery("select * from user where Uname = ?",new String[]{uname});
        if (c.getCount()>0) {
            long no = db.update(TABLE_REG, cv, "Uname = ?", new String[]{uname});
            if (no==-1)
                return false;
            else
                return true;
        }else {
            return false;
        }

    }
}
