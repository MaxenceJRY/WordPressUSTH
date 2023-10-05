package vn.edu.usth.wordpress25.ui.me;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;

public class First_Name extends DialogFragment {

    private DatabaseHelper dbHelper;
    String firstname ;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dbHelper = new DatabaseHelper(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_first_name, null);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT firstname FROM " + DatabaseHelper.TABLE_NAME + " WHERE email = ?",
                new String[]{UserManager.getInstance().getLoggedInEmail()});

        if (cursor != null && cursor.moveToFirst()) {
            firstname = cursor.getString(0);
            EditText editTextFirstName = dialogView.findViewById(R.id.editFirstName);

            editTextFirstName.setHint(firstname);
        }

        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editTextFirstName = dialogView.findViewById(R.id.editFirstName);
                        String newFirstname = editTextFirstName.getText().toString();
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME + " SET " +
                                        DatabaseHelper.FIRSTNAME + " = ? WHERE " + DatabaseHelper.EMAIL + " = ?",
                                new String[]{newFirstname, UserManager.getInstance().getLoggedInEmail()});

                        // Fermez le dialogue
                        dialog.dismiss();
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
    private Cursor getUserData(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " +
                DatabaseHelper.EMAIL + " = ?", new String[]{email});

        return cursor;
    }

}