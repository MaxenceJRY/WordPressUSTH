package vn.edu.usth.wordpress25.ui.notifications;

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
 * Use the {@link Commentsononthersites#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Commentsononthersites extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Commentsononthersites() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Commentsononthersites.
     */
    // TODO: Rename and change types and number of parameters
    public static Commentsononthersites newInstance(String param1, String param2) {
        Commentsononthersites fragment = new Commentsononthersites();
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

        View view = inflater.inflate(R.layout.fragment_commentsononthersites, container, false);

        LinearLayout notiftab_layout = view.findViewById(R.id.notiftab1);
        notiftab_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogNotiftab();
            }
        });
        return view;
    }
    private void showDialogNotiftab() {
        DialogFragment dialogFragment = new notificationstab();
        dialogFragment.show(getChildFragmentManager(), "Notiftab");
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
