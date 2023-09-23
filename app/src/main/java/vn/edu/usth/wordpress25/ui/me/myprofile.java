package vn.edu.usth.wordpress25.ui.me;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import vn.edu.usth.wordpress25.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myprofile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myprofile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public myprofile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myprofil.
     */
    // TODO: Rename and change types and number of parameters
    public static myprofile newInstance(String param1, String param2) {
        myprofile fragment = new myprofile();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myprofile, container, false);
        LinearLayout first_name_layout = view.findViewById(R.id.myfirstname);
        LinearLayout last_name_layout = view.findViewById(R.id.mylastname);
        LinearLayout public_d_layout = view.findViewById(R.id.Public_dis);
        LinearLayout about_me_layout = view.findViewById(R.id.aboutme);
        first_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFirst();
            }
        });
        last_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogLast();
            }
        });
        public_d_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPublic();
            }
        });
        about_me_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAboutMe();
            }
        });
        return view;
    }

    private void showDialogFirst() {
        DialogFragment dialogFragment = new First_Name();
        dialogFragment.show(getChildFragmentManager(), "My First Name");
    }
    private void showDialogLast() {
        DialogFragment dialogFragment = new Last_Name();
        dialogFragment.show(getChildFragmentManager(), "My Last Name");
    }
    private void showDialogPublic() {
        DialogFragment dialogFragment = new Public_Display_Name();
        dialogFragment.show(getChildFragmentManager(), "Public Display Name");
    }
    private void showDialogAboutMe() {
        DialogFragment dialogFragment = new about_me();
        dialogFragment.show(getChildFragmentManager(), "About me");
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