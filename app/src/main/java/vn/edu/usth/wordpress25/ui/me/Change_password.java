package vn.edu.usth.wordpress25.ui.me;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;

public class Change_password extends DialogFragment {
    private DatabaseHelper dbHelper;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dbHelper = new DatabaseHelper(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_change_password, null);
        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editTextPassword = dialogView.findViewById(R.id.editPass);
                        String newPassword = editTextPassword.getText().toString();
                        Log.d("motdepasse",newPassword);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME + " SET " +
                                        DatabaseHelper.PASSWORD + " = ? WHERE " + DatabaseHelper.EMAIL + " = ?",
                                new String[]{newPassword, UserManager.getInstance().getLoggedInEmail()});

                        // Fermez le dialogue
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }
}
