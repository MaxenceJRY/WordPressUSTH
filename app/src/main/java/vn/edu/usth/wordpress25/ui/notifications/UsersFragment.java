package vn.edu.usth.wordpress25.ui.notifications;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import vn.edu.usth.wordpress25.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param username Parameter 1.
   //  * @param param2 Parameter 2.
     * @return A new instance of fragment UsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersFragment newInstance(String username) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, username);
        //args.putString(ARG_PARAM2, param2);
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
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_users, container, false);
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        // Référence au TextView
        TextView usernameTextView = view.findViewById(R.id.username);

        // Obtenez le nom de l'utilisateur (remplacez ceci par votre propre logique)
        String userName = mParam1;

        // Définissez le texte du TextView avec le nom de l'utilisateur
        usernameTextView.setText(userName);



        Bundle args = getArguments();

            final String username = args.getString("userName");

            // Trouvez le bouton dans la vue du fragment
        LinearLayout followclic = view.findViewById(R.id.followclic);

            followclic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Ouvrez le fragment follows_ExempleFragment en utilisant la navigation
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", userName);

                    Navigation.findNavController(v).navigate(R.id.follows_ExempleFragment, bundle);
                }
            });


        return view;
    }
}