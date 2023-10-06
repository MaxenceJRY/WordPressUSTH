package vn.edu.usth.wordpress25.ui.Reader.following;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import vn.edu.usth.wordpress25.adapter.FilterTopicsAdapter;
import vn.edu.usth.wordpress25.databinding.FragmentFilterTopicsBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;
import vn.edu.usth.wordpress25.model.Topic;

public class FilterTopicsFragment extends Fragment {
    private FragmentFilterTopicsBinding binding;

    private List<Topic> topics = new ArrayList<>(
            Arrays.asList(
                    new Topic(
                            1,
                            "art"
                    ),
                    new Topic(
                            2,
                            "food"
                    ),
                    new Topic(
                            3,
                            "photography"
                    ),
                    new Topic(
                            4,
                            "technology"
                    )
            ));
    private FilterTopicsAdapter filterTopicsAdapter;

    public FilterTopicsFragment() {

    }

    public static FilterTopicsFragment newInstance() {
        return new FilterTopicsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilterTopicsBinding.inflate(inflater, container, false);
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
        setupApdater();
    }


    private void setupApdater() {
        filterTopicsAdapter = new FilterTopicsAdapter(requireActivity(), topics, (data, position) -> {
            EventBus.getDefault().post(new MessageEvent(MessageEvent.DISMISS_FILTER_BOTTOM_SHEET));
            EventBus.getDefault().post(new MessageEvent(MessageEvent.START_FILTER, data, position));
        });
        binding.rvTheme.setAdapter(filterTopicsAdapter);
    }

}