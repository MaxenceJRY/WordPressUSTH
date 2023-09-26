package vn.edu.usth.wordpress25;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Lire le contenu du fichier JSON et vérifier les identifiants
                try {
                    String jsonContent = readJsonFile("Account.json");
                    JSONArray jsonArray = new JSONArray(jsonContent);

                    boolean isAuthenticated = false;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String jsonEmail = jsonObject.getString("email");
                        String jsonPassword = jsonObject.getString("password");

                        if (email.equals(jsonEmail) && password.equals(jsonPassword)) {
                            isAuthenticated = true;

                            // Stocker toutes les valeurs de l'utilisateur dans les préférences partagées
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("loggedInEmail", email);
                            editor.putString("loggedInPassword", password);
                            editor.putString("loggedInFirstname", jsonObject.getString("firstname"));
                            editor.putString("loggedInLastname", jsonObject.getString("lastname"));
                            editor.putString("loggedInDisplayname", jsonObject.getString("displayname"));
                            editor.putString("loggedInUsername", jsonObject.getString("username"));
                            editor.apply();

                            break;
                        }
                    }

                    if (isAuthenticated) {
                        goToMainActivity(view);
                    } else {
                        Toast.makeText(getContext(), "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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

    // Méthode pour lire le contenu d'un fichier JSON
    private String readJsonFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getContext().getAssets().open(filePath)));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();
            return jsonString.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
