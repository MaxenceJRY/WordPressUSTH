package vn.edu.usth.wordpress25.ui.Reader.discover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import vn.edu.usth.wordpress25.databinding.ActivityDetailPostBinding;
import vn.edu.usth.wordpress25.model.ChooseTopic;

public class DetailPostActivity extends AppCompatActivity {

    private ActivityDetailPostBinding binding;
    private List<ChooseTopic> storageTheme = new ArrayList<>();

    private ChooseTopic chooseTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initControl();
        initData();
        initViews();


    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initControl() {
        binding.imgBack.setOnClickListener(view -> onBackPressed());
    }



    private void initData() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        if (data == null || data.isEmpty()) {
            chooseTopic = null;
        } else {
            chooseTopic = new Gson().fromJson(data, ChooseTopic.class);
        }
    }

    private void initViews() {
        if (chooseTopic != null) {
            binding.imgTopic.setImageResource(chooseTopic.srcImage);
            binding.tvTopic.setText(chooseTopic.name);
            binding.tvContent.setText(chooseTopic.content);
        }
    }


}