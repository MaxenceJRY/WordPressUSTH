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
 * Use the {@link Account_settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Account_settings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Account_settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account_settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Account_settings newInstance(String param1, String param2) {
        Account_settings fragment = new Account_settings();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_settings, container, false);
        LinearLayout email_add_layout = view.findViewById(R.id.emailadd);
        LinearLayout web_add_layout = view.findViewById(R.id.web_address);
        LinearLayout change_pass_layout = view.findViewById(R.id.change_pass);
        LinearLayout close_acc_layout = view.findViewById(R.id.close_acc);
        email_add_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogEmailAdd();
            }
        });
        web_add_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWebAdd();
            }
        });
        change_pass_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogChangePass();
            }
        });
        close_acc_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCloseAcc();
            }
        });
        return view;
    }

    private void showDialogEmailAdd() {
        DialogFragment dialogFragment = new Email_address();
        dialogFragment.show(getChildFragmentManager(), "My Email Address");
    }
    private void showDialogWebAdd() {
        DialogFragment dialogFragment = new Web_address();
        dialogFragment.show(getChildFragmentManager(), "My Web Address");
    }
    private void showDialogChangePass() {
        DialogFragment dialogFragment = new Change_password();
        dialogFragment.show(getChildFragmentManager(), "My Password");
    }
    private void showDialogCloseAcc() {
        DialogFragment dialogFragment = new Close_acc();
        dialogFragment.show(getChildFragmentManager(), "Close my account");
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