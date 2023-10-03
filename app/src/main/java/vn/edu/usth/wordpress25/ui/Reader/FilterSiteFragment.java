package vn.edu.usth.wordpress25.ui.Reader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import androidx.fragment.app.Fragment;
import vn.edu.usth.wordpress25.databinding.FragmentFilterSiteBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;

public class FilterSiteFragment extends Fragment {
    private FragmentFilterSiteBinding binding;

    public FilterSiteFragment() {

    }

    public static FilterSiteFragment newInstance() {
        return new FilterSiteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterSiteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initControl();
        initData();
        initViews();

        return root;
    }

    private void initControl() {
       binding.btnFollowASite.setOnClickListener(view -> EventBus.getDefault().post(new MessageEvent(MessageEvent.DISMISS_FILTER_BOTTOM_SHEET)));
    }


    private void initData() {

    }

    private void initViews() {
    }



}