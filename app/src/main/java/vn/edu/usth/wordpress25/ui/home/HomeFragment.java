package vn.edu.usth.wordpress25.ui.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.adapter.ChooseThemeAdapter;
import vn.edu.usth.wordpress25.databinding.FragmentHomeBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;
import vn.edu.usth.wordpress25.eventbus.PreferenceUtils;
import vn.edu.usth.wordpress25.model.ChooseTopic;
import vn.edu.usth.wordpress25.ui.mysite.AddSiteActivity;
import vn.edu.usth.wordpress25.ui.mysite.PreviewActivity;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    private List<ChooseTopic> themes = new ArrayList<>();
    private ChooseThemeAdapter chooseThemeAdapter;


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initControl();
        initData();
        initViews();


        return root;

    }

    private void goToAddSite() {
        Intent intent = new Intent(getContext(), AddSiteActivity.class);
        startActivity(intent);
    }

    @SuppressLint({"ClickableViewAccessibility", "NotifyDataSetChanged"})
    private void initControl() {
        Dialog dialog = new Dialog(requireActivity());


        binding.button2.setOnClickListener(view -> {

            dialog.setContentView(R.layout.dialog_optional);
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            TextView okay_text = dialog.findViewById(R.id.tv_create);
            TextView cancel_text = dialog.findViewById(R.id.tv_add_self);

            okay_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToAddSite();
                    dialog.dismiss();
                }
            });

            cancel_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        });

        binding.btnCreateNew.setOnClickListener(view -> {

            dialog.setContentView(R.layout.dialog_optional);
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);

            TextView okay_text = dialog.findViewById(R.id.tv_create);
            TextView cancel_text = dialog.findViewById(R.id.tv_add_self);

            okay_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToAddSite();
                    dialog.dismiss();
                }
            });

            cancel_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        });
    }

    private void initData() {
        //short list to show
        if (themes != null) {
            themes.addAll(PreferenceUtils.getChooseThemes());
            setupView();
        }
    }

    private void setupView() {
        if (themes.size() > 0) {
            binding.frameChoosedTheme.setVisibility(View.VISIBLE);
            binding.lnEmptyData.setVisibility(View.GONE);
        } else {
            binding.frameChoosedTheme.setVisibility(View.GONE);
            binding.lnEmptyData.setVisibility(View.VISIBLE);
        }
    }

    private void initViews() {
        setupApdater();
    }

    private void setupApdater() {
        chooseThemeAdapter = new ChooseThemeAdapter(requireActivity(), themes, true, new ChooseThemeAdapter.OnClick() {
            @Override
            public void onClick(ChooseTopic data, int position) {
                Intent intent = new Intent(requireActivity(), PreviewActivity.class);
                intent.putExtra("showBtnDelete", true);
                intent.putExtra("data", new Gson().toJson(data));
                startActivity(intent);
            }

            @Override
            public void onDeleteItem(ChooseTopic data, int position) {
                deleteTheme(data);
            }
        });
        binding.rvTheme.setAdapter(chooseThemeAdapter);
    }

    private void deleteTheme(ChooseTopic data) {
        int index = themes.indexOf(data);
        themes.remove(data);
        chooseThemeAdapter.notifyItemRemoved(index);
        PreferenceUtils.saveChooseThemes(themes);
        setupView();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        if (event.getMsg().contentEquals(MessageEvent.CHOOSED_SITE) || event.getMsg().contentEquals(MessageEvent.DELETED_THEME)) {
            if (themes != null) {
                themes.clear();
                initData();
                chooseThemeAdapter.notifyDataSetChanged();
            }

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}