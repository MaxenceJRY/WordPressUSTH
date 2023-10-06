package vn.edu.usth.wordpress25.ui.notifications;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.edu.usth.wordpress25.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Follows_ExempleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Follows_ExempleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Follows_ExempleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param username Parameter 1.
    //* @param param2 Parameter 2.
     * @return A new instance of fragment Follows_ExempleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Follows_ExempleFragment newInstance(String username) {
        Follows_ExempleFragment fragment = new Follows_ExempleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, username);

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_follows__exemple, container, false);
        // Référence au TextView
        TextView usernameTextView = view.findViewById(R.id.username2);

        // Obtenez le nom de l'utilisateur (remplacez ceci par votre propre logique)
        String userName = mParam1;

        // Définissez le texte du TextView avec le nom de l'utilisateur
        usernameTextView.setText(userName);
        return inflater.inflate(R.layout.fragment_follows__exemple, container, false);

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
}
