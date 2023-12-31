package vn.edu.usth.wordpress25;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import vn.edu.usth.wordpress25.ui.DatabaseHelper;
import vn.edu.usth.wordpress25.ui.MainActivity;
public class connection extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DatabaseHelper dbHelper;
    private String mParam1;
    private String mParam2;

    public connection() {
    }

    public static connection newInstance(String param1, String param2) {
        connection fragment = new connection();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getContext());
        dbHelper.onCreate(dbHelper.getWritableDatabase());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connection, container, false);

        Button loginButton = view.findViewById(R.id.button_login);
        EditText emailEditText = view.findViewById(R.id.edit_text_email);
        EditText passwordEditText = view.findViewById(R.id.edit_text_password);

    /*    dbHelper.insertData("augustin@gmail.com","123","augustin","andre","augustin","augustin");
        dbHelper.insertDataSITE("https://www.usth.com/","usth","augustin@gmail.com");
        dbHelper.addSiteToMySites("augustin@gmail.com","https://www.usth.com/");

        dbHelper.insertData("fabien@gmail.com","123","fabien","fabien","fabien","fabien");

        dbHelper.insertData("max@gmail.com","123","maxence","juery","juery78","juery78");

        dbHelper.addUserToFollowers("https://www.usth.com/","fabien@gmail.com");
        dbHelper.addUserToFollowers("https://www.usth.com/","max@gmail.com");
        dbHelper.addSiteToFollows("augustin@gmail.com","https://www.grab.com/");
        dbHelper.addSiteToFollows("augustin@gmail.com","https://www.be.com/");
        dbHelper.addSiteToFollows("augustin@gmail.com","https://www.yahoo.com/");
        dbHelper.addSiteToFollows("augustin@gmail.com","https://www.google.com/");*/
        //dbHelper.addSiteToFollows("augustin@gmail.com","https://www.blablacar.com/");
        //dbHelper.insertData("corentin@gmail.com","123","corentin","andre","corentin","corentin");
        //dbHelper.addSiteToFollows("augustin@gmail.com","https://www.be.com/");
        //dbHelper.addSiteToFollows("augustin@gmail.com","https://www.yahoo.com/");
        //dbHelper.addUserToFollowers("https://www.usth.com/","corentin@gmail.com");
        //dbHelper.addSiteToFollows("fabien@gmail.com","https://www.google.com/");
        //dbHelper.addSiteToMySites("fabien@gmail.com","https://www.tinder.com/");
        //dbHelper.insertData("fabrice@gmail.com","123","robin","robin","robin5","robin5");
        //dbHelper.addSiteToFollows("fabrice@gmail.com","https://www.google.com/");
        // dbHelper.addSiteToMySites("augustin@gmail.com","https://www.airbnb.com/");
        //dbHelper.addUserToFollowers("https://www.airbnb.com/","fabrice@gmail.com");
        //dbHelper.addSiteToFollows("fabrice@gmail.com","https://www.airbnb.com/");
        //dbHelper.insertDataSITE("https://www.airbnb.com/","airbnb","augustin@gmail.com");
        //dbHelper.addUserToFollowers("https://www.airbnb.com/","fabrice@gmail.com");
        // dbHelper.addSiteToMySites("fabien@gmail.com","https://www.pizz.com/");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                boolean isAuthenticated = authenticateUser(email, password);

                if (isAuthenticated) {
                    UserManager userManager = UserManager.getInstance();
                    userManager.setLoggedInEmail(email);
                    goToMainActivity(view);
                } else {
                    Toast.makeText(getContext(), "Email or password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void goToMainActivity(View view) {

        Context context = getActivity();
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    private boolean authenticateUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                        DatabaseHelper.EMAIL + " = ? AND " + DatabaseHelper.PASSWORD + " = ?",
                new String[]{email, password});

        boolean isAuthenticated = cursor.moveToFirst();

        cursor.close();
        db.close();

        return isAuthenticated;
    }
}
