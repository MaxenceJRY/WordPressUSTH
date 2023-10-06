package vn.edu.usth.wordpress25.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;

public class NotifUnreadFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private DatabaseHelper dbHelper;
    private LinearLayout mail1;
    private boolean isMail1Clicked = false;

    public NotifUnreadFragment() {
        // Required empty public constructor
    }

    public static NotifUnreadFragment newInstance(String param1, String param2) {
        NotifUnreadFragment fragment = new NotifUnreadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notif_unread, container, false);

        mail1 = view.findViewById(R.id.conteneurusers3);

        // Lisez l'état du clic dans les préférences partagées
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        isMail1Clicked = sharedPreferences.getBoolean("mail1_clicked", false);

        // Si le LinearLayout a été cliqué, masquez-le définitivement
        if (isMail1Clicked) {
            mail1.setVisibility(View.GONE);
        } else {
            // Si le LinearLayout n'a pas encore été cliqué, configurez le clic
            mail1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleMail1Visibility();
                    Navigation.findNavController(v).navigate(R.id.follows_ExempleFragment);

                    // Enregistrez dans les préférences partagées que le LinearLayout a été cliqué
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("mail1_clicked", true);
                    editor.apply();
                }
            });
        }

        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(getContext());
        }
        removeAllFragments();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor userDataCursor = getUserData(UserManager.getInstance().getLoggedInEmail());
        String userdata = dbHelper.fetchStringFromCursor(userDataCursor);
        Cursor cursorsite = db.rawQuery("SELECT TABMYSITES FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                DatabaseHelper.EMAIL + " = ?", new String[]{userdata});

        String sitename = dbHelper.fetchStringFromCursor(cursorsite);
        if (sitename != ""&& sitename !=null) {
            String[] tabmysites = dbHelper.stringToArray(sitename);
            for (String site : tabmysites) {


                Cursor cursor = db.rawQuery("SELECT TABFOLLOWERS FROM " + DatabaseHelper.TABLE_NAME2 + " WHERE " +
                        DatabaseHelper.URL + " = ?", new String[]{sitename});

                String tabfollowers = dbHelper.fetchStringFromCursor(cursor);
                if (tabfollowers != "" && tabfollowers != null) {
                    String[] tabfollowerstab = dbHelper.stringToArray(tabfollowers);
                    // Parcourez la liste des utilisateurs
                    for (String user : tabfollowerstab) {
                        Cursor cursordisplayname = db.rawQuery("SELECT USERNAME FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                                DatabaseHelper.EMAIL + " = ?", new String[]{user});
                        String displayname = dbHelper.fetchStringFromCursor(cursordisplayname);

                        Cursor cursorsitename = db.rawQuery("SELECT NAMESITE FROM " + DatabaseHelper.TABLE_NAME2 + " WHERE " +
                                DatabaseHelper.URL + " = ?", new String[]{site});
                        String sitename2 = dbHelper.fetchStringFromCursor(cursorsitename);


                        UsersFragment userFragment = UsersFragment.newInstance(displayname,sitename2);
                        FragmentManager fragmentManager = getChildFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.conteneurusers3, userFragment);
                        fragmentTransaction.commit();
                    }
                }
            }
        }
        return view;
    }

    private Cursor getUserData(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                DatabaseHelper.EMAIL + " = ?", new String[]{email});
        return cursor;
    }

    private void removeAllFragments() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (Fragment fragment : fragmentManager.getFragments()) {
            fragmentTransaction.remove(fragment);
        }

        fragmentTransaction.commit();
    }

    private void toggleMail1Visibility() {
        if (mail1.getVisibility() == View.VISIBLE) {
            mail1.setVisibility(View.GONE);
            isMail1Clicked = true;
        } else {
            mail1.setVisibility(View.VISIBLE);
            isMail1Clicked = false;
        }
    }
}
