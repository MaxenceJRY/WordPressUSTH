package vn.edu.usth.wordpress25.ui.mysite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.adapter.ChooseTopicAdapter;
import vn.edu.usth.wordpress25.databinding.ActivityAddSiteBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;
import vn.edu.usth.wordpress25.model.ChooseTopic;

public class AddSiteActivity extends AppCompatActivity {

    private ActivityAddSiteBinding binding;
    private List<ChooseTopic> filterChooseTopics = new ArrayList<>();
    private ChooseTopicAdapter chooseTopicAdapter;

    private boolean firstTouch = false;

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
        binding = ActivityAddSiteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initControl();
        initData();
        initViews();


    }

    @SuppressLint({"ClickableViewAccessibility", "NotifyDataSetChanged"})
    private void initControl() {
        binding.imgBack.setOnClickListener(view -> onBackPressed());
        binding.searchSiteTopic.setOnTouchListener((view, motionEvent) -> {
            binding.lnTitle.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.VISIBLE);
            binding.searchSiteTopic.setFocusable(true);
            binding.searchSiteTopic.requestFocus();
            //change to full list in first touch
            if (!firstTouch) {
                filterChooseTopics.clear();
                filterChooseTopics.addAll(searchDatas());
                chooseTopicAdapter.notifyDataSetChanged();
                firstTouch = true;
            }
            return false;
        });

        binding.searchSiteTopic.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
                ArrayList<ChooseTopic> filterdNames = new ArrayList<>();
                for (ChooseTopic s : filterChooseTopics) {
                    if (s.name.toLowerCase().contains(editable.toString().toLowerCase())) {
                        filterdNames.add(s);
                    }
                }
                chooseTopicAdapter.setFilter(filterdNames);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("NotifyDataSetChanged")
            public void onTextChanged(CharSequence query, int start, int before, int count) {

            }
        });
    }

    private void initData() {
        //short list to show
        filterChooseTopics = showDatas();
    }

    private void initViews() {
        setupApdater();
        //disable before click to search edittext
        binding.rvChooseTopic.setEnabled(false);
    }

    private void setupApdater() {
        chooseTopicAdapter = new ChooseTopicAdapter(AddSiteActivity.this, filterChooseTopics, new ChooseTopicAdapter.OnClick() {
            @Override
            public void onClick(ChooseTopic data) {
                gotoChooseTheme();
            }
        });
        binding.rvChooseTopic.setAdapter(chooseTopicAdapter);
    }

    private void gotoChooseTheme(){
        Intent intent = new Intent(this, ChooseThemeActivity.class);
        startActivity(intent);
    }

    private ArrayList<ChooseTopic> showDatas() {
        return new ArrayList<>(Arrays.asList(
                new ChooseTopic(
                        R.drawable.ic_food,
                        "Food"
                ),
                new ChooseTopic(
                        R.drawable.ic_lifestyle,
                        "Lifestyle"
                ),
                new ChooseTopic(
                        R.drawable.ic_news,
                        "News"
                ),
                new ChooseTopic(
                        R.drawable.ic_personal,
                        "Personal"
                ),
                new ChooseTopic(
                        R.drawable.ic_photography,
                        "Photography"
                ),
                new ChooseTopic(
                        R.drawable.ic_travel,
                        "Travel"
                )
        )
        );
    }


    private ArrayList<ChooseTopic> searchDatas() {
        return new ArrayList<>(Arrays.asList(
                new ChooseTopic(
                        R.drawable.ic_art,
                        "Art"
                ),
                new ChooseTopic(
                        R.drawable.ic_automotive,
                        "Automotive"
                ),
                new ChooseTopic(
                        R.drawable.ic_food,
                        "Food"
                ),
                new ChooseTopic(
                        R.drawable.ic_beauty,
                        "Beauty"
                ),
                new ChooseTopic(
                        R.drawable.ic_lifestyle,
                        "Lifestyle"
                ),
                new ChooseTopic(
                        R.drawable.ic_book,
                        "Book"
                ),
                new ChooseTopic(
                        R.drawable.ic_personal,
                        "Personal"
                ),
                new ChooseTopic(
                        R.drawable.ic_business,
                        "Business"
                ),
                new ChooseTopic(
                        R.drawable.ic_community,
                        "Community & Non-Profit"
                ),
                new ChooseTopic(
                        R.drawable.ic_education,
                        "Education"
                ),
                new ChooseTopic(
                        R.drawable.ic_fashion,
                        "Fashion"
                ),
                new ChooseTopic(
                        R.drawable.ic_news,
                        "News"
                ),
                new ChooseTopic(
                        R.drawable.ic_finance,
                        "Finance"
                ),
                new ChooseTopic(
                        R.drawable.ic_photography,
                        "Photography"
                ),
                new ChooseTopic(
                        R.drawable.ic_gaming,
                        "Gaming"
                ),
                new ChooseTopic(
                        R.drawable.ic_travel,
                        "Travel"
                )
        )
        );
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        if (event.getMsg().contentEquals(MessageEvent.CHOOSED_SITE)) {
            finish();
        }
    }
}