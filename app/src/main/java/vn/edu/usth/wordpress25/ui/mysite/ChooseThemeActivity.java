package vn.edu.usth.wordpress25.ui.mysite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.adapter.ChooseThemeAdapter;
import vn.edu.usth.wordpress25.databinding.ActivityChooseThemeBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;
import vn.edu.usth.wordpress25.model.ChooseTopic;

public class ChooseThemeActivity extends AppCompatActivity {

    private ActivityChooseThemeBinding binding;
    private List<ChooseTopic> themes = new ArrayList<>(Arrays.asList(
            new ChooseTopic(
                    1,
                    R.drawable.img_theme_1,
                    "Fewer",
                    ""
            ),
            new ChooseTopic(
                    2,
                    R.drawable.img_theme_2,
                    "Mpho",
                    ""
            ),
            new ChooseTopic(
                    3,
                    R.drawable.img_theme_3,
                    "Al Dente",
                    ""
            ),
            new ChooseTopic(
                    4,
                    R.drawable.img_theme_4,
                    "Skatepark",
                    ""
            )
    )
    );
    private ChooseThemeAdapter chooseThemeAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseThemeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initControl();
        initData();
        initViews();


    }

    @SuppressLint({"ClickableViewAccessibility", "NotifyDataSetChanged"})
    private void initControl() {
        binding.imgBack.setOnClickListener(view -> onBackPressed());
    }

    private void initData() {
    }

    private void initViews() {
        setupApdater();
    }

    private void setupApdater() {
        chooseThemeAdapter = new ChooseThemeAdapter(ChooseThemeActivity.this, themes, false, new ChooseThemeAdapter.OnClick() {
            @Override
            public void onClick(ChooseTopic data, int position) {
                Intent intent = new Intent(ChooseThemeActivity.this, PreviewActivity.class);
                intent.putExtra("data", new Gson().toJson(data));
                startActivity(intent);
            }

            @Override
            public void onDeleteItem(ChooseTopic data, int position) {

            }
        });
        binding.rvChooseTopic.setAdapter(chooseThemeAdapter);
    }


    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        if (event.getMsg().contentEquals(MessageEvent.CHOOSED_SITE)) {
            finish();
        }
    }

}