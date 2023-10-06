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

public class Last_Name extends DialogFragment {
    private DatabaseHelper dbHelper;
    String lastname;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dbHelper = new DatabaseHelper(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_last_name, null);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT lastname FROM " + DatabaseHelper.TABLE_NAME + " WHERE email = ?",
                new String[]{UserManager.getInstance().getLoggedInEmail()});

        if (cursor != null && cursor.moveToFirst()) {
            lastname = cursor.getString(0);
            EditText editTextLastName = dialogView.findViewById(R.id.editLastName);

            editTextLastName.setHint(lastname);
        }

        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editTextLastName = dialogView.findViewById(R.id.editLastName);
                        String newLastname = editTextLastName.getText().toString();
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME + " SET " +
                                        DatabaseHelper.LASTNAME + " = ? WHERE " + DatabaseHelper.EMAIL + " = ?",
                                new String[]{newLastname, UserManager.getInstance().getLoggedInEmail()});
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
