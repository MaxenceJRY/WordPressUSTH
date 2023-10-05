package vn.edu.usth.wordpress25.ui.me;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import vn.edu.usth.wordpress25.Firstpage;
import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.Registration;
import vn.edu.usth.wordpress25.UserManager;
import vn.edu.usth.wordpress25.ui.DatabaseHelper;

public class Close_acc extends DialogFragment {
    private DatabaseHelper dbHelper;
    String username, email;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dbHelper = new DatabaseHelper(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_close_acc, null);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username FROM " + DatabaseHelper.TABLE_NAME + " WHERE email = ?",
                new String[]{UserManager.getInstance().getLoggedInEmail()});

        if (cursor != null && cursor.moveToFirst()) {
            username = cursor.getString(0);
            EditText editTextLastName = dialogView.findViewById(R.id.editCloseAcc);

            editTextLastName.setHint(username);
        }

        builder.setView(dialogView)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = dialogView.findViewById(R.id.editCloseAcc);
                        String editclose = editText.getText().toString();
                        Log.d("USERNAME",username);
                        Log.d("USERNAME",editclose);
                        if (editText.getText().toString().equals(username)) {
                            db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.EMAIL + " = ?",
                                    new String[]{UserManager.getInstance().getLoggedInEmail()});
                            Intent intent = new Intent(getActivity(), Firstpage.class);
                            startActivity(intent);

                        }
                        Toast.makeText(getActivity(), "Wrong username", Toast.LENGTH_SHORT).show();
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
