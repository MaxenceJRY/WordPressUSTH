package vn.edu.usth.wordpress25.ui.notifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;
import vn.edu.usth.wordpress25.ui.Site;
import vn.edu.usth.wordpress25.ui.me.MeFragment;
import vn.edu.usth.wordpress25.ui.users;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifFollowsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifFollowsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private Site site1Reference;

    private DatabaseHelper dbHelper;

    //data
    ArrayList<Site> Tabsites = new ArrayList<>();
    ArrayList<users> Tabusers = new ArrayList<>();

    ArrayList<users> Tabsite1folow = new ArrayList<>();//users that follow the site (abonnées)
    ArrayList<users> Tabsite2folow = new ArrayList<>();
    ArrayList<Site> Tabuser1folow = new ArrayList<>(); //sites that users follows (abonnements)
    ArrayList<Site> Tabuser2folow = new ArrayList<>();
    public NotifFollowsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifFollowsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifFollowsFragment newInstance(String param1, String param2) {
        NotifFollowsFragment fragment = new NotifFollowsFragment();
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

            dbHelper = new DatabaseHelper(getContext());


            dbHelper.onCreate(dbHelper.getWritableDatabase());



        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notif_follows, container, false);
        LinearLayout mail1 = view.findViewById(R.id.conteneurusers);

        mail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.follows_ExempleFragment);
            }
        });

        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(getContext());
        }
        removeAllFragments();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor userDataCursor = getUserData(UserManager.getInstance().getLoggedInEmail());
        String userdata=dbHelper.fetchStringFromCursor(userDataCursor);
        Cursor cursorsite = db.rawQuery("SELECT TABMYSITES FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                DatabaseHelper.EMAIL + " = ?", new String[]{userdata});

        String sitename = dbHelper.fetchStringFromCursor(cursorsite);
        if (sitename != "" && sitename !=null) {
            String[] tabmysites = dbHelper.stringToArray(sitename);
            for (String site : tabmysites){



                Cursor cursor = db.rawQuery("SELECT TABFOLLOWERS FROM " + DatabaseHelper.TABLE_NAME2 + " WHERE " +
                        DatabaseHelper.URL + " = ?", new String[]{site});


                String tabfollowers = dbHelper.fetchStringFromCursor(cursor);
                if (tabfollowers != "" && tabfollowers !=null) {
                    String[] tabfollowerstab = dbHelper.stringToArray(tabfollowers);



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
                        fragmentTransaction.add(R.id.conteneurusers, userFragment);

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

}