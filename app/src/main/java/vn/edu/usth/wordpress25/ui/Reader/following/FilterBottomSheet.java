package vn.edu.usth.wordpress25.ui.Reader.following;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.databinding.BottomSheetFilterBinding;


public class FilterBottomSheet extends BottomSheetDialogFragment {

    private BottomSheetFilterBinding binding;
    private Context context;
    private FilterPagerAdapter filterPagerAdapter;

    @Override
    public void onResume() {
        super.onResume();
    }

    public FilterBottomSheet newInstance(Context context) {
        this.context = context;
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
                if (dialog != null) {
                    FrameLayout bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                    if (bottomSheet != null) {
                        BottomSheetBehavior<FrameLayout> behavior = BottomSheetBehavior.from(bottomSheet);
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
}
