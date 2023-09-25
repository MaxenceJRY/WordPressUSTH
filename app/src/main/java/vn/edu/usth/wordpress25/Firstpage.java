package vn.edu.usth.wordpress25;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.edu.usth.wordpress25.ui.MainActivity;

public class Firstpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_firstpage);
        getSupportActionBar().hide();
        Button loginButton = findViewById(R.id.button7);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity(view);
            }
        });
    }

    public void goToMainActivity(View view) {
        Intent intent = new Intent(Firstpage.this, MainActivity.class);
        startActivity(intent);
    }
}