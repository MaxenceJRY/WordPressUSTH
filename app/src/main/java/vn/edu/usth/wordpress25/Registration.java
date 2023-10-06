package vn.edu.usth.wordpress25;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.usth.wordpress25.ui.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Registration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registration extends AppCompatActivity {

    EditText editemail, editpassword, editconfpassword, editfirstname,editlastname, editusername, editIdEmail ;
    Button confirm, viewall, btnupdate , btnDelete;
    DatabaseHelper myDb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Registration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registration.
     */
    // TODO: Rename and change types and number of parameters
    public static Registration newInstance(String param1, String param2) {
        Registration fragment = new Registration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registration);
        myDb = new DatabaseHelper(this);
        editemail = (EditText) findViewById(R.id.editTextEmail);
        editpassword = (EditText) findViewById(R.id.editTextPassword);
        editfirstname = (EditText) findViewById(R.id.editTextFirstname);
        editlastname = (EditText) findViewById(R.id.editTextLastname);
        editusername = (EditText) findViewById(R.id.editTextUsername);
        editIdEmail = (EditText) findViewById(R.id.editTextUpdate);
        confirm = (Button) findViewById(R.id.Confirm);
        viewall = (Button) findViewById(R.id.viewdata);
        btnupdate = (Button) findViewById(R.id.btnUpgrade);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        AddData();
        ViewAllData ();
        UpdateData();
        DeleteData();
    }
    public void DeleteData () {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDb.deleteData(editIdEmail.getText().toString());
                if (deleteRows > 0)
                    Toast.makeText(Registration.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Registration.this, "Data not deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void UpdateData () {
        btnupdate.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.upgradeData(editIdEmail.getText().toString(),
                        editpassword.getText().toString(), editfirstname.getText().toString(),
                        editlastname.getText().toString(),
                        editfirstname.getText().toString() + " " + editlastname.getText().toString(),
                        editusername.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(Registration.this, "Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Registration.this, "Data not updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void AddData() {
        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInsert = myDb.insertData(editemail.getText().toString(),
                                editpassword.getText().toString(),
                                editfirstname.getText().toString(),
                                editlastname.getText().toString(),
                                editfirstname.getText().toString()+" "+editlastname.getText().toString(),
                                editusername.getText().toString());
                        if (isInsert == true)
                            Toast.makeText(Registration.this, "Data inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Registration.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
    public void ViewAllData () {
        viewall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("Error", "Nothing found");
                            return;
                        }else {
                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Email :"+res.getString(0)+"\n");
                                buffer.append("Password :" + res.getString(1)+"\n");
                                buffer.append("Firstname :" + res.getString(2)+"\n");
                                buffer.append("Lastname :" + res.getString(3)+"\n");
                                buffer.append("DisplayName :" + res.getString(4)+"\n");
                                buffer.append("Username :" + res.getString(5)+"\n\n");
                            }
                            showMessage("Data", buffer.toString());

                        }
                    }
                }
        );
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}