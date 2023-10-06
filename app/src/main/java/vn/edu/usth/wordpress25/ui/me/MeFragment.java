package vn.edu.usth.wordpress25.ui.me;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.databinding.FragmentMeBinding;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;

public class MeFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private FragmentMeBinding binding;
    String firstname;
    String disp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getContext());
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_me, container, false);
        LinearLayout myprofilLinearLayout = view.findViewById(R.id.my_profile);
        LinearLayout logOutLayout = view.findViewById(R.id.click_log_out);

        myprofilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.myprofile);
            }
        });

        LinearLayout secondLinearLayout = view.findViewById(R.id.AccountSettings);
        secondLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.accountsettings);
            }
        });
        LinearLayout thirdLinearLayout = view.findViewById(R.id.click_app_Set);
        thirdLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.app_Settings);
            }
        });
        LinearLayout helplinearlayout = view.findViewById(R.id.click_help);
        helplinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.help);
            }
        });
        LinearLayout clickShare = view.findViewById(R.id.click_share);
        clickShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareText = "Hey! Here is a link to download the Jetpack app. I'm really enjoying it and thought you might too.\n" +
                        "https://jetpack.com/app?campaign=app_share_link";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(shareIntent, "Share with :"));
            }
        });
        logOutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor first = db.rawQuery("SELECT firstname FROM " + DatabaseHelper.TABLE_NAME + " WHERE email = ?",
                new String[]{UserManager.getInstance().getLoggedInEmail()});
        Cursor display = db.rawQuery("SELECT displayname FROM " + DatabaseHelper.TABLE_NAME + " WHERE email = ?",
                new String[]{UserManager.getInstance().getLoggedInEmail()});

        if (first != null && first.moveToFirst()) {
            firstname = first.getString(0);
            TextView name = view.findViewById(R.id.Name);

            name.setText(firstname);
        }
        if (display != null && display.moveToFirst()) {
            disp = display.getString(0);
            TextView surname = view.findViewById(R.id.Surname);

            surname.setText(disp);
        }

       /* Cursor userDataCursor = getUserData(UserManager.getInstance().getLoggedInEmail());

        if (userDataCursor != null && userDataCursor.moveToFirst()) {
            String loggedInFirstname = userDataCursor.getString(3);
            String loggedInDisplayname = userDataCursor.getString(5);

            TextView name = view.findViewById(R.id.Name);
            TextView surname = view.findViewById(R.id.Surname);

            surname.setText(loggedInDisplayname);
            name.setText(loggedInFirstname);

            userDataCursor.close();
        }*/
        return view;
    }

    private void showDialog() {
        DialogFragment dialogFragment = new LogoutDialogFragment();
        dialogFragment.show(getChildFragmentManager(), "logout_dialog");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Cursor getUserData(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                DatabaseHelper.EMAIL + " = ?", new String[]{email});

        return cursor;
    }
}