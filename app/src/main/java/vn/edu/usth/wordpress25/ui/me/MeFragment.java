package vn.edu.usth.wordpress25.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {
    private FragmentMeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_me, container, false);
        LinearLayout myprofilLinearLayout = view.findViewById(R.id.my_profile);
        LinearLayout logOutLayout = view.findViewById(R.id.click_log_out);

        myprofilLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.myprofile);
            }
        });

        LinearLayout secondLinearLayout = view.findViewById(R.id.AccountSettings);
        secondLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.accountsettings);
            }
        });
        LinearLayout thirdLinearLayout = view.findViewById(R.id.click_app_Set);
        thirdLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.app_Settings);
            }
        });
        LinearLayout helplinearlayout = view.findViewById(R.id.click_help);
        helplinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.help);
            }
        });
        LinearLayout clickShare = view.findViewById(R.id.click_share);
        clickShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareText = "Hey! Here is a link to download the Jetpack app. I'm really enjoying it and thought you might too.\n" +
                        "https://jetpack.com/app?campaign=app_share_link";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(shareIntent, "Share with :"));
            }
        });
        logOutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }

    private void showDialog() {
        DialogFragment dialogFragment = new LogoutDialogFragment();
        dialogFragment.show(getChildFragmentManager(), "logout_dialog");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}