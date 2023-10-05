package vn.edu.usth.wordpress25.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "wordpress.db";
    //DATA USER
    public static final String TABLE_NAME = "user_table";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String DISPLAYNAME = "displayname";
    public static final String USERNAME = "username";
    public static final String TABFOLLOWS = "tabfollows";
    public static final String TABMYSITES = "tabmysites";

    //DATA SITE
    public static final String TABLE_NAME2 = "site_table";
    public static final String URL = "url";
    public static final String NAMESITE = "namesite";
    public static final String AUTHOR = "author";
    public static final String TABTAGS = "tabtags";
    public static final String TABFOLLOWERS = "tabfollowers";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Vérifier si les tables existent déjà
        if (!isTableExists(db, TABLE_NAME) && !isTableExists(db, TABLE_NAME2)) {
            // Créer la table user_table si elle n'existe pas
            db.execSQL("create table " + TABLE_NAME + "(EMAIL STRING PRIMARY KEY, PASSWORD STRING, " +
                    "FIRSTNAME STRING, LASTNAME STRING, DISPLAYNAME STRING, USERNAME STRING,TABFOLLOWS STRING, TABMYSITES STRING)");

            // Créer la table site_table si elle n'existe pas
            db.execSQL("create table " + TABLE_NAME2 + "(URL STRING PRIMARY KEY, NAMESITE STRING, " +
                    "AUTHOR STRING, TABTAGS STRING, TABFOLLOWERS STRING)");
        }
    }

    // Méthode pour vérifier si une table existe déjà dans la base de données
    private boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
        boolean tableExists = cursor.getCount() > 0;
        cursor.close();
        return tableExists;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void onUpgrade2(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
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
    public void insertDataSITE(String url, String namesite, String author) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(URL,url);
        contentValues.put(NAMESITE,namesite);
        contentValues.put(AUTHOR,author);


        db.insert(TABLE_NAME2, null, contentValues);
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

    public void addUserToFollowers(String siteUrl, String userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Vérifier si l'URL du site existe déjà dans la table site_table
        Cursor siteCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + URL + "=?", new String[]{siteUrl});
        if (siteCursor.getCount() > 0) {
            // L'URL du site existe, nous pouvons ajouter l'utilisateur à TABFOLLOWERS

            // Tout d'abord, obtenir la liste actuelle des followers pour ce site
            siteCursor.moveToFirst();
            String currentFollowers = siteCursor.getString(4);

            // Ajouter l'ID de l'utilisateur à la liste des followers
            if (currentFollowers == null || currentFollowers.isEmpty()) {
                currentFollowers = userId;
            } else {
                currentFollowers += "," + userId;
            }

            // Mettre à jour la liste des followers dans la table site_table
            ContentValues contentValues = new ContentValues();
            contentValues.put(TABFOLLOWERS, currentFollowers);
            db.update(TABLE_NAME2, contentValues, URL + "=?", new String[]{siteUrl});
        }

        siteCursor.close();
    }
    public void addSiteToMySites(String userId, String siteUrl) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Vérifier si l'utilisateur avec userId existe déjà dans la table user_table
        Cursor userCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL + "=?", new String[]{userId});
        if (userCursor.getCount() > 0) {
            // L'utilisateur existe, nous pouvons ajouter siteUrl à TABMYSITES

            // Tout d'abord, obtenir la liste actuelle de TABMYSITES pour cet utilisateur
            userCursor.moveToFirst();
            String currentMySites = userCursor.getString(6);

            // Ajouter siteUrl à la liste de TABMYSITES de l'utilisateur
            if (currentMySites == null || currentMySites.isEmpty()) {
                currentMySites = siteUrl;
            } else {
                currentMySites += "," + siteUrl;
            }

            // Mettre à jour la liste de TABMYSITES dans la table user_table
            ContentValues contentValues = new ContentValues();
            contentValues.put(TABMYSITES, currentMySites);
            db.update(TABLE_NAME, contentValues, EMAIL + "=?", new String[]{userId});
        }

        userCursor.close();
    }
    public void addSiteToFollows(String userId, String siteUrl) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Vérifier si l'utilisateur avec userId existe déjà dans la table user_table
        Cursor userCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL + "=?", new String[]{userId});
        if (userCursor.getCount() > 0) {
            // L'utilisateur existe, nous pouvons ajouter siteUrl à TABFOLLOWS

            // Tout d'abord, obtenir la liste actuelle de TABFOLLOWS pour cet utilisateur
            userCursor.moveToFirst();
            String currentFollows = userCursor.getString(5);

            // Ajouter siteUrl à la liste de TABFOLLOWS de l'utilisateur
            if (currentFollows == null || currentFollows.isEmpty()) {
                currentFollows = siteUrl;
            } else {
                currentFollows += "," + siteUrl;
            }

            // Mettre à jour la liste de TABFOLLOWS dans la table user_table
            ContentValues contentValues = new ContentValues();
            contentValues.put(TABFOLLOWS, currentFollows);
            db.update(TABLE_NAME, contentValues, EMAIL + "=?", new String[]{userId});
        }

        userCursor.close();
    }


    public static String[] stringToArray(String inputString) {
        // Utilisez la méthode split() pour diviser la chaîne en utilisant des virgules comme séparateurs
        String[] elements = inputString.split(",");

        // Retournez le tableau résultant
        return elements;
    }

    public String[] getTabFollowersFromCursor(Cursor cursor) {
        String[] tabFollowers = null;

        if (cursor != null && cursor.moveToFirst()) {
            String tabFollowersString = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.TABFOLLOWERS));
            tabFollowers = tabFollowersString.split(",");
        }

        return tabFollowers;
    }
    public static String[] fetchStringsFromCursor(Cursor cursor) {
        List<String> stringList = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Supposons que la colonne que vous souhaitez extraire soit la première colonne.
                // Vous pouvez ajuster cela en fonction de votre schéma de base de données.
                String stringValue = cursor.getString(0);
                stringList.add(stringValue);
            } while (cursor.moveToNext());
        }

        // Convertir la liste en tableau de chaînes de caractères
        String[] stringArray = stringList.toArray(new String[0]);

        return stringArray;
    }
    public static String fetchStringFromCursor(Cursor cursor) {
        String stringValue = "";

        if (cursor != null && cursor.moveToFirst()) {
            // Supposons que la colonne que vous souhaitez extraire soit la première colonne.
            // Vous pouvez ajuster cela en fonction de votre schéma de base de données.
            stringValue = cursor.getString(0);
        }

        return stringValue;
    }

}
