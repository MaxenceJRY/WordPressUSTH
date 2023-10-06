package vn.edu.usth.wordpress25.ui.notifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifSettings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Switch switchButton;
    private DatabaseHelper dbHelper;
    private LinearLayout linearLayout1;
    public NotifSettings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifSettings newInstance(String param1, String param2) {
        NotifSettings fragment = new NotifSettings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dbHelper = new DatabaseHelper(getContext());

        // Créer la table site_table si elle n'existe pas encore
       // dbHelper.onCreate(dbHelper.getWritableDatabase());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_notif_settings,container,false);
        LinearLayout other = view.findViewById(R.id.other);

        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(getContext());
        }
        removeAllFragments();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.commentsononthersites);
            }
        });

        switchButton = view.findViewById(R.id.switch1);
        linearLayout1 = view.findViewById(R.id.linearlayoutnotifsett);
        // Définir le Switch par défaut sur "ON"
        switchButton.setChecked(true);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Si le Switch est activé (ON), affichez les LinearLayout, sinon cachez-les
                if (isChecked) {
                    linearLayout1.setVisibility(View.VISIBLE);

                } else {
                    linearLayout1.setVisibility(View.GONE);

                }
            }
        });

        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(getContext());
        }
        removeAllFragments();

        Cursor userDataCursor = getUserData(UserManager.getInstance().getLoggedInEmail());
        String userdata=dbHelper.fetchStringFromCursor(userDataCursor);
        Cursor cursorsite = db.rawQuery("SELECT TABFOLLOWS FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                DatabaseHelper.EMAIL + " = ?", new String[]{userdata});

        String tabsite = dbHelper.fetchStringFromCursor(cursorsite);
        if (tabsite != "" && tabsite != null) {
            String[] tabfollows = dbHelper.stringToArray(tabsite);

            // Parcourez la liste des utilisateurs
            for (String site : tabfollows) {

                SiteFollowsFragment siteFollowsFragment = SiteFollowsFragment.newInstance(site); // Vous devrez créer un UserFragment pour afficher le nom de l'utilisateur
                // Utilisez un FragmentManager pour ajouter le fragment à l'interface utilisateur
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container5, siteFollowsFragment); // R.id.fragment_container est l'ID de la vue où vous voulez ajouter le fragment
                fragmentTransaction.commit();

            }

        }

        Cursor cursormysite = db.rawQuery("SELECT TABMYSITES FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                DatabaseHelper.EMAIL + " = ?", new String[]{userdata});

        String tabmysites = dbHelper.fetchStringFromCursor(cursormysite);
        if (tabmysites != ""&& tabmysites !=null) {
            String[] tabmysite = dbHelper.stringToArray(tabmysites);

            // Parcourez la liste des utilisateurs
            for (String sites : tabmysite) {

                MySiteListExempleFragment mySiteListExempleFragment = MySiteListExempleFragment.newInstance(sites); // Vous devrez créer un UserFragment pour afficher le nom de l'utilisateur
                // Utilisez un FragmentManager pour ajouter le fragment à l'interface utilisateur
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container9, mySiteListExempleFragment); // R.id.fragment_container est l'ID de la vue où vous voulez ajouter le fragment
                fragmentTransaction.commit();

            }

        }

        return view;


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Utilisez NavController pour revenir en arrière
            NavHostFragment.findNavController(this).navigateUp();
            return true;
        }
        return super.onOptionsItemSelected(item);
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