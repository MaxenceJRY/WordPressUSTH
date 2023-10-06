package vn.edu.usth.wordpress25.ui.mysite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.adapter.ChooseDomainAdapter;
import vn.edu.usth.wordpress25.databinding.ActivityChooseDomainBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;
import vn.edu.usth.wordpress25.eventbus.PreferenceUtils;
import vn.edu.usth.wordpress25.model.ChooseTopic;
import vn.edu.usth.wordpress25.model.Domain;

public class ChooseDomainActivity extends AppCompatActivity {

    private ActivityChooseDomainBinding binding;
    private List<ChooseTopic> storageTheme = new ArrayList<>();

    private List<Domain> filterChooseDomains = new ArrayList<>();
    private ChooseDomainAdapter chooseDomainAdapter;
    private boolean firstTouch = false;

    String[] domains = {".wordpress.com", ".com", ".blog", ".org", ".store", ".info",
            "88.com", ".online", ".space", ".tech", "games.com", "games.com", ".website"};

    private ChooseTopic chooseTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseDomainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initControl();
        initData();
        initViews();


    }

    @SuppressLint({"ClickableViewAccessibility", "NotifyDataSetChanged"})
    private void initControl() {
        binding.imgBack.setOnClickListener(view -> onBackPressed());
        binding.btnCreateSite.setOnClickListener(view -> {
            if (chooseTopic.website != null && !chooseTopic.website.isEmpty()){
                if (getIndexChoosedTheme(storageTheme, chooseTopic) == -1 || (getIndexChoosedTheme(storageTheme, chooseTopic) != -1 && !findDomainExist(chooseTopic.website))) {
                    storageTheme.add(chooseTopic);
                    PreferenceUtils.saveChooseThemes(storageTheme);
                }
                EventBus.getDefault().post(new MessageEvent(MessageEvent.CHOOSED_SITE, chooseTopic));
                ChooseDomainActivity.this.finish();
            } else {
                Toast.makeText(this, "Please select a your domain", Toast.LENGTH_SHORT).show();
            }

        });


        binding.searchSite.setOnTouchListener((view, motionEvent) -> {
            binding.tvSample.setVisibility(View.GONE);
            binding.searchSite.setFocusable(true);
            binding.searchSite.requestFocus();
            //change to full list in first touch
            if (!firstTouch) {
                filterChooseDomains.clear();
                chooseDomainAdapter.notifyDataSetChanged();
                firstTouch = true;
            }
            return false;
        });

        binding.searchSite.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().isEmpty()) {
                    showRecyclerView(new ArrayList<>());
                    chooseDomainAdapter.setFilter(new ArrayList<>());
                } else {
                    ArrayList<Domain> filterdNames = new ArrayList<>();
                    filterChooseDomains.clear();
                    filterChooseDomains.addAll(getFullDomain(editable.toString().trim()));
                    for (Domain s : filterChooseDomains) {
                        if (!findDomainExist(s.name)) {
                            filterdNames.add(s);
                        }
                    }
                    showRecyclerView(filterdNames);
                    chooseDomainAdapter.setFilter(filterdNames);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("NotifyDataSetChanged")
            public void onTextChanged(CharSequence query, int start, int before, int count) {

            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void showRecyclerView(List<Domain> fullDomains) {
        binding.rvChooseDomain.setBackground(this.getDrawable(fullDomains.size() > 0 ? R.drawable.bg_result_domain : R.drawable.bg_result_domain_transparent));
    }

    private ArrayList<Domain> getFullDomain(String domainName) {
        ArrayList<Domain> fullDomains = new ArrayList<>();
        for (int i = 0; i < domains.length; i++) {
            fullDomains.add(new Domain(i + 1, domainName + domains[i]));
        }
        return fullDomains;
    }

    private boolean findDomainExist(String fullDomain) {
        for (ChooseTopic item : storageTheme) {
            if (fullDomain.equals(item.website)) {
                return true;
            }
        }
        return false;
    }

    private void setupApdater() {
        chooseDomainAdapter = new ChooseDomainAdapter(ChooseDomainActivity.this, filterChooseDomains, new ChooseDomainAdapter.OnCheckItem() {
            @Override
            public void onCheckedChanged(Domain domain, boolean isChecked) {
                chooseTopic.website = domain.name;
            }
        });
        binding.rvChooseDomain.setAdapter(chooseDomainAdapter);
        binding.rvChooseDomain.setHasFixedSize(true);
    }

    private int getIndexChoosedTheme(List<ChooseTopic> datas, ChooseTopic chooseTopic) {
        int index = -1;
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).id == chooseTopic.id) {
                index = i;
                return index;
            }
        }
        return index;
    }

    private void initData() {
        //get from Preference
        storageTheme = PreferenceUtils.getChooseThemes();

        //get data from intent Preview pass to here
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        if (data == null || data.isEmpty()) {
            chooseTopic = null;
        } else {
            chooseTopic = new Gson().fromJson(data, ChooseTopic.class);
        }
    }

    private void initViews() {
        setupApdater();
    }


}