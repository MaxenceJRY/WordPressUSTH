package vn.edu.usth.wordpress25.ui.Reader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import vn.edu.usth.wordpress25.R;

public class Following_example_3 extends Fragment {

    private View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.fragment_following_example_3, container, false);
        return rootview;
    }


}