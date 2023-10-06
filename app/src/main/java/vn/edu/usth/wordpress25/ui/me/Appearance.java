package vn.edu.usth.wordpress25.ui.me;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import vn.edu.usth.wordpress25.R;

public class Appearance extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.fragment_appearance)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Vous pouvez appliquer le thème ici en fonction de la sélection
                        applyTheme();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Fermez le dialogue
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

    // Méthode pour appliquer le thème en fonction de la sélection du RadioButton
    private void applyTheme() {
        View view = getView();
        if (view != null) {
            // Le reste de votre code pour appliquer le thème en fonction de la sélection
        RadioGroup themeRadioGroup = getView().findViewById(R.id.Theme_radio);
        int selectedRadioButtonId = themeRadioGroup.getCheckedRadioButtonId();


        // Utilisez SharedPreferences pour stocker le thème sélectionné
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (selectedRadioButtonId == R.id.Light_radio) {
            editor.putString("theme", "light");
        } else if (selectedRadioButtonId == R.id.dark_radio) {
            editor.putString("theme", "dark");
        }

        editor.apply();
        }
    }
}
