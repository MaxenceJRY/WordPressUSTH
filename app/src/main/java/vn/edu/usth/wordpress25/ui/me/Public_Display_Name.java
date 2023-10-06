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

public class Public_Display_Name extends DialogFragment {
    private DatabaseHelper dbHelper;
    String display ;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dbHelper = new DatabaseHelper(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_public_display_name, null);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT displayname FROM " + DatabaseHelper.TABLE_NAME + " WHERE email = ?",
                new String[]{UserManager.getInstance().getLoggedInEmail()});

        if (cursor != null && cursor.moveToFirst()) {
            display = cursor.getString(0);
            EditText editTextDisplay = dialogView.findViewById(R.id.editDisplayName);

            editTextDisplay.setHint(display);
        }

        builder.setView(dialogView)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText editTextDisplayName = dialogView.findViewById(R.id.editDisplayName);
                    String newname = editTextDisplayName.getText().toString();
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME + " SET " +
                                    DatabaseHelper.DISPLAYNAME + " = ? WHERE " + DatabaseHelper.EMAIL + " = ?",
                            new String[]{newname, UserManager.getInstance().getLoggedInEmail()});

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
