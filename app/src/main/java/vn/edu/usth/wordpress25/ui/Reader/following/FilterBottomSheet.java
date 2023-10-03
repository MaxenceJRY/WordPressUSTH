package vn.edu.usth.wordpress25.ui.Reader.following;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.databinding.BottomSheetFilterBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;


public class FilterBottomSheet extends BottomSheetDialogFragment {

    private BottomSheetFilterBinding binding;
    private FilterPagerAdapter filterPagerAdapter;

    @Override
    public void onResume() {
        super.onResume();
    }

    public FilterBottomSheet newInstance() {
        return new FilterBottomSheet();
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
                if (dialog != null){
                    FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                    if (bottomSheet != null){
                        BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
                        behavior.setPeekHeight(1100);
                        behavior.setFitToContents(true);
                    }
                }

            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetFilterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initControl();
        initData();
        initViews();


        return root;
    }


    private void initControl() {

    }

    private void initData() {

    }

    private void initViews() {
        setupTabFollowing();
    }

    private void setupTabFollowing() {
        filterPagerAdapter = new FilterPagerAdapter(getChildFragmentManager());
        binding.viewPageFilter.setAdapter(filterPagerAdapter);
        binding.tabFilter.setupWithViewPager(binding.viewPageFilter);
    }

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

    @SuppressLint("NotifyDataSetChanged")
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        if (event.getMsg().contentEquals(MessageEvent.DISMISS_FILTER_BOTTOM_SHEET)) {
            this.dismissAllowingStateLoss();
        }
    }
}
