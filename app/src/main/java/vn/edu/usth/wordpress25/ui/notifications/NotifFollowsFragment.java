package vn.edu.usth.wordpress25.ui.notifications;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.ui.Site;
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

        // Stockez la référence à Site1 dans le fragment
        //fragment.site1Reference = site1Reference;

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
        View view = inflater.inflate(R.layout.fragment_notif_follows, container, false);



        users User1 = new users("vlad@gmail.com","12345","Leo",Tabuser1folow);
        users User2 = new users("vlad@gmail.com","12345","Vlad",Tabuser2folow);
        Site Site1 = new Site("YT",Tabsite1folow);
        Site Site2 = new Site("FC",Tabsite2folow);
//adding users
        Tabusers.add(User1);
        Tabusers.add(User2);
//adding sites
        Tabsites.add(Site1);
        Tabsites.add(Site2);
//adding followers to site
        Site1.getFollowersList().add(User1);
        Site1.getFollowersList().add(User2);
        Site2.getFollowersList().add(User1);
        Site2.getFollowersList().add(User2);
//adding follows for users
        User1.getFollowsList().add(Site1);
        User1.getFollowsList().add(Site2);
        User2.getFollowsList().add(Site1);
        User2.getFollowsList().add(Site2);
  /*      Button button = view.findViewById(R.id.buttonreaderf); // Assurez-vous que le bouton a l'ID correct
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Accédez à l'activité parente
                AppCompatActivity activity = (AppCompatActivity) getActivity();

                // Accédez à la barre de navigation
                BottomNavigationView bottomNavigationView = activity.findViewById(R.id.nav_view); // Remplacez par l'ID de votre barre de navigation

                // Sélectionnez l'onglet "Dashboard"
                bottomNavigationView.setSelectedItemId(R.id.navigation_reader);
            }
        });
*/
        // Utilisez la référence à Site1


        ArrayList<users> Tabuser1folow = Site1.getFollowersList(); // Assurez-vous d'avoir une référence à Site1

        // Parcourez la liste des utilisateurs
        for (users user : Tabuser1folow) {
            // Créez un nouveau fragment pour chaque utilisateur
            UsersFragment userFragment = UsersFragment.newInstance(user.getName()); // Vous devrez créer un UserFragment pour afficher le nom de l'utilisateur

            // Utilisez un FragmentManager pour ajouter le fragment à l'interface utilisateur
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.conteneurusers, userFragment); // R.id.fragment_container est l'ID de la vue où vous voulez ajouter le fragment
            fragmentTransaction.commit();
        }

        return view;
    }
}