package vn.edu.usth.wordpress25.ui.Reader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.databinding.FragmentReaderBinding;
import vn.edu.usth.wordpress25.ui.notifications.NotificationsPagerAdapter;

public class ReaderFragment extends Fragment {

    private FragmentReaderBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reader, container, false);

        ViewPager ReaderviewPager = view.findViewById(R.id.ReaderviewPager);
        PagerAdapter pagerAdapter = new ReaderPagerAdapter(getChildFragmentManager());
        ReaderviewPager.setAdapter(pagerAdapter);


        TabLayout tabLayout = view.findViewById(R.id.ReadertabLayout);
        tabLayout.setupWithViewPager(ReaderviewPager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}