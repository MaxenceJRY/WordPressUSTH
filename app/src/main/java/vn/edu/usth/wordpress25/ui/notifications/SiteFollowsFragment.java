package vn.edu.usth.wordpress25.ui.notifications;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.edu.usth.wordpress25.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SiteFollowsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SiteFollowsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SiteFollowsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sitename Parameter 1.

     * @return A new instance of fragment SiteFollowsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SiteFollowsFragment newInstance(String sitename) {
        SiteFollowsFragment fragment = new SiteFollowsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, sitename);

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
        View view = inflater.inflate(R.layout.fragment_site_follows, container, false);

        // Référence au TextView
        TextView sitenameTextView = view.findViewById(R.id.sitename3);

        // Obtenez le nom de l'utilisateur (remplacez ceci par votre propre logique)
        String sitename = mParam1;

        // Définissez le texte du TextView avec le nom de l'utilisateur
        sitenameTextView.setText(sitename);



        Bundle args = getArguments();

        final String username = args.getString("userName");



        return view;
    }
}