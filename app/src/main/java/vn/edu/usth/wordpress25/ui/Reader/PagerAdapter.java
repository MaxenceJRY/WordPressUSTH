package vn.edu.usth.wordpress25.ui.Reader;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new View1Fragment();
            case 1:
                return new View2Fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2; // Số lượng tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Posts";
            case 1:
                return "Sites";
            default:
                return "";
        }
    }
}
