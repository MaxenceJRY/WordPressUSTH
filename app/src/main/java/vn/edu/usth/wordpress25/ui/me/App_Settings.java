package vn.edu.usth.wordpress25.ui.me;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import vn.edu.usth.wordpress25.R;

public class App_Settings extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public App_Settings() {
        // Required empty public constructor
    }

    public static App_Settings newInstance(String param1, String param2) {
        App_Settings fragment = new App_Settings();
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
        View view = inflater.inflate(R.layout.fragment_app_settings, container, false);
        setHasOptionsMenu(true);

        // Trouver les vues
        LinearLayout themeLayout = view.findViewById(R.id.theme);
        LinearLayout initScreenLayout = view.findViewById(R.id.init_screen);
        LinearLayout MaximumISLayout = view.findViewById(R.id.max_i_s);
        LinearLayout QualityILayout = view.findViewById(R.id.image_q);
        Switch switch4 = view.findViewById(R.id.switch4); // Le premier switch
        TextView MaxIText = MaximumISLayout.findViewById(R.id.textViewMaxI);
        TextView MaxISet = MaximumISLayout.findViewById(R.id.textViewMaxISet);
        TextView ImageQ = QualityILayout.findViewById(R.id.textViewImageQ);
        TextView ImageQSet = QualityILayout.findViewById(R.id.textViewImageQSet);
        LinearLayout MaximumVSLayout = view.findViewById(R.id.max_v_s);
        LinearLayout QualityVLayout = view.findViewById(R.id.video_q);
        Switch switch3 = view.findViewById(R.id.switch3);
        TextView MaxVText = MaximumVSLayout.findViewById(R.id.textViewMaxV);
        TextView MaxVSet = MaximumVSLayout.findViewById(R.id.textViewMaxVS);
        TextView VideoQ = QualityVLayout.findViewById(R.id.textViewVideoQ);
        TextView VideoQSet = QualityVLayout.findViewById(R.id.textViewVQSet);
        // Écouteur de changement d'état pour le premier Switch
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Activer ou désactiver les LinearLayout en fonction de l'état du Switch
                int textColor = isChecked ? Color.BLACK : Color.GRAY; // Couleur différente pour activé/désactivé
                MaxIText.setTextColor(textColor);
                MaxISet.setTextColor(textColor);
                ImageQ.setTextColor(textColor);
                ImageQSet.setTextColor(textColor);
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Activer ou désactiver les LinearLayout en fonction de l'état du Switch
                int textColor = isChecked ? Color.BLACK : Color.GRAY; // Couleur différente pour activé/désactivé
                MaxVText.setTextColor(textColor);
                MaxVSet.setTextColor(textColor);
                VideoQ.setTextColor(textColor);
                VideoQSet.setTextColor(textColor);
            }
        });
        // Gestion des clics sur les LinearLayout pour afficher les dialogues
        themeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDialog();
            }
        });
        initScreenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDialogInit();
            }
        });
        QualityILayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch4.isChecked()) {
                    showDialogImageQ();
                }
            }
        });
        MaximumISLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch4.isChecked()) {
                    showDialogMaxI();
                }
            }
        });
        return view;
    }

    private void showDialog() {
        DialogFragment dialogFragment = new Appearance();
        dialogFragment.show(getChildFragmentManager(), "theme_dialog");
    }
    private void showDialogInit() {
        DialogFragment dialogFragment = new Initial_screen();
        dialogFragment.show(getChildFragmentManager(), "Initial Screen");
    }
    private void showDialogMaxI() {
        DialogFragment dialogFragment = new Maximum_I_S();
        dialogFragment.show(getChildFragmentManager(), "Maximum Image Size");
    }
    private void showDialogImageQ() {
        DialogFragment dialogFragment = new Image_Quality();
        dialogFragment.show(getChildFragmentManager(), "Image Quality");
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
