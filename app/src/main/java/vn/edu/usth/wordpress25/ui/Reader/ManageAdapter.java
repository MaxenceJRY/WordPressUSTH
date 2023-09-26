package vn.edu.usth.wordpress25.ui.Reader;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ManageAdapter extends FragmentPagerAdapter {

    public ManageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SitesFragment();
            case 1:
                return new TopicsFragment();
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
                return "Followed Topics";
            case 1:
                return "Followed Sites";
            default:
                return "";
        }
    }
}
