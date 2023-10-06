package vn.edu.usth.wordpress25.ui.notifications;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifAllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifAllFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseHelper dbHelper;
    public NotifAllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifAllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifAllFragment newInstance(String param1, String param2) {
        NotifAllFragment fragment = new NotifAllFragment();
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

            // Créer la table site_table si elle n'existe pas encore
            dbHelper.onCreate(dbHelper.getWritableDatabase());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_notif_all,container,false);


        LinearLayout mail1 = view.findViewById(R.id.conteneurusers2);

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
        if (sitename != null) {
        Cursor cursor = db.rawQuery("SELECT TABFOLLOWERS FROM " + DatabaseHelper.TABLE_NAME2 + " WHERE " +
                DatabaseHelper.URL + " = ?", new String[]{sitename});


        String tabfollowers=dbHelper.fetchStringFromCursor(cursor);

            String[] tabfollowerstab = dbHelper.stringToArray(tabfollowers);

            // Parcourez la liste des utilisateurs

            for (String user : tabfollowerstab) {

                UsersFragment userFragment = UsersFragment.newInstance(user); // Vous devrez créer un UserFragment pour afficher le nom de l'utilisateur

                // Utilisez un FragmentManager pour ajouter le fragment à l'interface utilisateur
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.conteneurusers2, userFragment); // R.id.fragment_container est l'ID de la vue où vous voulez ajouter le fragment

                fragmentTransaction.commit();

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