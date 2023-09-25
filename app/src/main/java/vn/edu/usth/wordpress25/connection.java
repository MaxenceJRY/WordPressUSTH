package vn.edu.usth.wordpress25;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.usth.wordpress25.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link connection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class connection extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public connection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment connection.
     */
    // TODO: Rename and change types and number of parameters
    public static connection newInstance(String param1, String param2) {
        connection fragment = new connection();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connection, container, false);

        Button loginButton = view.findViewById(R.id.button_login);
        EditText emailEditText = view.findViewById(R.id.edit_text_email);
        EditText passwordEditText = view.findViewById(R.id.edit_text_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("connection", "emailEditText = " + emailEditText);

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                if (email.equals("admin") && password.equals("admin")) {
                    goToMainActivity(view);
                } else {
                    Toast.makeText(getContext(), "Email or password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void goToMainActivity(View view) {
        Context context = getActivity();
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
}