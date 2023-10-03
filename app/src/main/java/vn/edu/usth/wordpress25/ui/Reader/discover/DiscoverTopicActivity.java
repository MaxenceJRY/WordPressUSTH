package vn.edu.usth.wordpress25.ui.Reader.discover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.adapter.DiscoverTopicAdapter;
import vn.edu.usth.wordpress25.databinding.ActivityDiscoverTopicBinding;
import vn.edu.usth.wordpress25.model.ChooseTopic;

public class DiscoverTopicActivity extends AppCompatActivity {

    private ActivityDiscoverTopicBinding binding;
    private List<ChooseTopic> discoverTopics = new ArrayList<>();
    private DiscoverTopicAdapter discoverTopicAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiscoverTopicBinding.inflate(getLayoutInflater());
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
        discoverTopics = getDatas();
    }

    private void initViews() {
        setupApdater();
    }

    private void setupApdater() {
        discoverTopicAdapter = new DiscoverTopicAdapter(DiscoverTopicActivity.this, discoverTopics, (data, position) -> {
            Intent intent = new Intent(DiscoverTopicActivity.this, DetailPostActivity.class);
            intent.putExtra("data", new Gson().toJson(data));
            startActivity(intent);
        });
        binding.rvTopic.setAdapter(discoverTopicAdapter);
    }


    private ArrayList<ChooseTopic> getDatas() {
        return new ArrayList<>(Arrays.asList(
                new ChooseTopic(
                        1,
                        R.drawable.meat,
                        "Title of the image",
                        getString(R.string.lorem_ipsum)
                ),
                new ChooseTopic(
                        2,
                        R.drawable.meat,
                        "Title of the image",
                        getString(R.string.lorem_ipsum)
                ),
                new ChooseTopic(
                        3,
                        R.drawable.meat,
                        "Title of the image",
                        getString(R.string.lorem_ipsum)
                ),
                new ChooseTopic(
                        4,
                        R.drawable.meat,
                        "Title of the image",
                        getString(R.string.lorem_ipsum)
                )
        )
        );
    }
}