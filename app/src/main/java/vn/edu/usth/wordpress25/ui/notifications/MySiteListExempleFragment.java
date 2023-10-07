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
 * Use the {@link MySiteListExempleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MySiteListExempleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MySiteListExempleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sitename Parameter 1.

     * @return A new instance of fragment MySiteListExempleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MySiteListExempleFragment newInstance(String sitename) {
        MySiteListExempleFragment fragment = new MySiteListExempleFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_site_list_exemple, container, false);

        TextView sitenameTextView = view.findViewById(R.id.mysitename);
        String sitename = mParam1;
        sitenameTextView.setText(sitename);



        return view;
    }
}