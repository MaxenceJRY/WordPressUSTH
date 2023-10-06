package vn.edu.usth.wordpress25;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import vn.edu.usth.wordpress25.ui.DatabaseHelper;

public class UserManager {
    private static UserManager instance;
    private String loggedInEmail;
    private SQLiteDatabase db;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void setLoggedInEmail(String email) {
        loggedInEmail = email;
    }

    public String getLoggedInEmail() {
        return loggedInEmail;
    }

    @SuppressLint("Range")
    public String getLoggedInFirstname() {
        String firstname = null;

        if (db != null) {
            Cursor cursor = db.rawQuery("SELECT " + DatabaseHelper.FIRSTNAME + " FROM " + DatabaseHelper.TABLE_NAME +
                    " WHERE " + DatabaseHelper.EMAIL + " = ?", new String[]{loggedInEmail});

            if (cursor.moveToFirst()) {
                firstname = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIRSTNAME));
            }

            cursor.close();
        }

        return firstname;
    }
}
