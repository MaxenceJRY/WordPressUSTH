package vn.edu.usth.wordpress25.ui.me;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;

public class myprofile extends Fragment {

    private DatabaseHelper dbHelper;
    private View view;
    String firstname, lastname, displayname ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myprofile, container, false);
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
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT firstname, lastname, displayname FROM " + DatabaseHelper.TABLE_NAME + " WHERE email = ?",
                new String[]{UserManager.getInstance().getLoggedInEmail()});

        if (cursor != null && cursor.moveToFirst()) {
            firstname = cursor.getString(0);
            lastname = cursor.getString(1);
            displayname = cursor.getString(2);
        }
        TextView textfirst = view.findViewById(R.id.textView);
        TextView textlast = view.findViewById(R.id.textView4);
        TextView textdisplay = view.findViewById(R.id.textView6);
        textfirst.setText(firstname);
        textdisplay.setText(displayname);
        textlast.setText(lastname);
        return view;
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
}