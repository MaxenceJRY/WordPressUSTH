package vn.edu.usth.wordpress25.ui.Reader;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ReaderPagerAdapter extends FragmentPagerAdapter {

    public ReaderPagerAdapter(FragmentManager fm) {
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
            case 2:
                return new Likes();
            case 3:
                return new Saved();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "FOLLOWING";
            case 1:
                return "DISCOVER";
            case 2:
                return "LIKES";
            case 3:
                return "SAVED";
            default:
                return null;
        }
    }
}