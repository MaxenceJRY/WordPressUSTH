package vn.edu.usth.wordpress25.ui.Reader.following;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import vn.edu.usth.wordpress25.ui.Reader.Discover;
import vn.edu.usth.wordpress25.ui.Reader.FollowingFragment;

public class FilterPagerAdapter extends FragmentPagerAdapter {

    public FilterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FollowingFragment();
            case 1:
                return new Discover();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SITES (0)";
            case 1:
                return "TOPICS (4)";
            default:
                return "";
        }
    }
}