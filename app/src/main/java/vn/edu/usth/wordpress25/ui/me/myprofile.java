package vn.edu.usth.wordpress25.ui.me;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.content.SharedPreferences;
import android.widget.TextView;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;

public class myprofile extends Fragment {

    private DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myprofile, container, false);
        setHasOptionsMenu(true);
        LinearLayout first_name_layout = view.findViewById(R.id.myfirstname);
        LinearLayout last_name_layout = view.findViewById(R.id.mylastname);
        LinearLayout public_d_layout = view.findViewById(R.id.Public_dis);
        LinearLayout about_me_layout = view.findViewById(R.id.aboutme);
        first_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFirst();
            }
        });
        last_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogLast();
            }
        });
        public_d_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPublic();
            }
        });
        about_me_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAboutMe();
            }
        });
        Cursor userDataCursor = getUserData(UserManager.getInstance().getLoggedInEmail());

        if (userDataCursor != null && userDataCursor.moveToFirst()) {
            String loggedInFirstname = userDataCursor.getString(3);
            String loggedInLastname = userDataCursor.getString(4);
            String loggedInDisplayname = userDataCursor.getString(5);

            TextView textView = view.findViewById(R.id.textView);
            TextView textView4 = view.findViewById(R.id.textView4);
            TextView textView6 = view.findViewById(R.id.textView6);

            textView6.setText(loggedInDisplayname);
            textView4.setText(loggedInLastname);
            textView.setText(loggedInFirstname);

            userDataCursor.close();
        }

        return view;
    }

    private Cursor getUserData(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                DatabaseHelper.EMAIL + " = ?", new String[]{email});

        return cursor;
    }
    private void showDialogFirst() {
        DialogFragment dialogFragment = new First_Name();
        dialogFragment.show(getChildFragmentManager(), "My First Name");
    }
    private void showDialogLast() {
        DialogFragment dialogFragment = new Last_Name();
        dialogFragment.show(getChildFragmentManager(), "My Last Name");
    }
    private void showDialogPublic() {
        DialogFragment dialogFragment = new Public_Display_Name();
        dialogFragment.show(getChildFragmentManager(), "Public Display Name");
    }
    private void showDialogAboutMe() {
        DialogFragment dialogFragment = new about_me();
        dialogFragment.show(getChildFragmentManager(), "About me");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavHostFragment.findNavController(this).navigateUp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onFirstNameChanged(String newFirstName) {
        TextView textView = getView().findViewById(R.id.textView);
        textView.setText(newFirstName);
    }

}