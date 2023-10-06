package vn.edu.usth.wordpress25.ui.mysite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.wordpress25.databinding.ActivityPreviewBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;
import vn.edu.usth.wordpress25.eventbus.PreferenceUtils;
import vn.edu.usth.wordpress25.model.ChooseTopic;

public class PreviewActivity extends AppCompatActivity {

    private ActivityPreviewBinding binding;
    private List<ChooseTopic> storageTheme = new ArrayList<>();

    private ChooseTopic chooseTopic;
    private boolean showBtnDelete = false;

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
        binding = ActivityPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initControl();
        initData();
        initViews();


    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initControl() {
        binding.imgBack.setOnClickListener(view -> onBackPressed());
        binding.btnChoose.setOnClickListener(view -> {
            storageTheme = PreferenceUtils.getChooseThemes();
            if (showBtnDelete){
                storageTheme.remove(getIndexChoosedTheme(storageTheme, chooseTopic));
                PreferenceUtils.saveChooseThemes(storageTheme);
                PreviewActivity.this.finish();
                EventBus.getDefault().post(new MessageEvent(MessageEvent.DELETED_THEME));
            } else {
                Intent intent = new Intent(PreviewActivity.this, ChooseDomainActivity.class);
                intent.putExtra("data", new Gson().toJson(chooseTopic));
                startActivity(intent);
            }
        });
    }

    private int getIndexChoosedTheme(List<ChooseTopic> datas, ChooseTopic chooseTopic){
        int index = -1;
        for (int i = 0; i < datas.size(); i ++){
            if (datas.get(i).id == chooseTopic.id) {
                index = i;
                return index;
            }
        }
        return index;
    }

    private void initData() {
        Intent intent = getIntent();
        showBtnDelete = intent.getBooleanExtra("showBtnDelete", false);
        String data = intent.getStringExtra("data");
        if (data == null || data.isEmpty()) {
            chooseTopic = null;
        } else {
            chooseTopic = new Gson().fromJson(data, ChooseTopic.class);
        }
    }

    private void initViews() {
        if (chooseTopic != null) {
            binding.imgTheme.setImageResource(chooseTopic.srcImage);
        }
        binding.tvChoose.setText(showBtnDelete ? "Delete" : "Choose");
    }


    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        if (event.getMsg().contentEquals(MessageEvent.CHOOSED_SITE)) {
            finish();
        }
    }

}