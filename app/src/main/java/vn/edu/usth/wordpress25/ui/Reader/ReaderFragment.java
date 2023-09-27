package vn.edu.usth.wordpress25.ui.Reader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
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
        setHasOptionsMenu(true);

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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_reader, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_options) {
            Navigation.findNavController(requireView()).navigate(R.id.manageFragment);

            return true;
        }

        if (id == R.id.action_search) {
            Navigation.findNavController(requireView()).navigate(R.id.searchFragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}