package vn.edu.usth.wordpress25.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "wordpress.db";
    public static final String TABLE_NAME = "user_table";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String DISPLAYNAME = "displayname";
    public static final String USERNAME = "username";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(EMAIL STRING PRIMARY KEY, PASSWORD STRING, " +
                "FIRSTNAME STRING, LASTNAME STRING, DISPLAYNAME STRING, USERNAME STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String email, String password, String firstname,
                              String lastname, String displayname, String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL,email);
        contentValues.put(PASSWORD,password);
        contentValues.put(FIRSTNAME,firstname);
        contentValues.put(LASTNAME,lastname);
        contentValues.put(DISPLAYNAME,displayname);
        contentValues.put(USERNAME,username);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }

    public boolean upgradeData (String IDemail, String password, String firstname,
                                String lastname, String displayname, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL,IDemail);
        contentValues.put(PASSWORD,password);
        contentValues.put(FIRSTNAME,firstname);
        contentValues.put(LASTNAME,lastname);
        contentValues.put(DISPLAYNAME,displayname);
        contentValues.put(USERNAME,username);
        db.update(TABLE_NAME, contentValues, "email = ?", new String [] { IDemail });
        return true;
    }

    public Integer deleteData (String IdEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "email = ?", new String[] {IdEmail});
    }
}
