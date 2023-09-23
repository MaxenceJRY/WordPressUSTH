package vn.edu.usth.wordpress25.ui.notifications;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NotificationsPagerAdapter extends FragmentPagerAdapter {

    public NotificationsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Retournez le fragment correspondant à la position
        switch (position) {
            case 0:
                return new NotifAllFragment();
            case 1:
                return new NotifUnreadFragment();
            case 2:
                return new NotifCommentsFragment();
            case 3:
                return new NotifFollowsFragment();
            case 4:
                return new NotifLikesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Retournez le nombre total d'onglets
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Retournez le titre de l'onglet en fonction de la position
        switch (position) {
            case 0:
                return "ALL";
            case 1:
                return "UNREAD";
            case 2:
                return "COMMENTS";
            case 3:
                return "FOLLOWS";
            case 4:
                return "LIKES";
            default:
                return null;
        }
    }
}