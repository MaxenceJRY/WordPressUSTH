package vn.edu.usth.wordpress25.ui.Reader;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import vn.edu.usth.wordpress25.databinding.FragmentDiscoverBinding;
import vn.edu.usth.wordpress25.ui.Reader.discover.DiscoverTopicActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment{

    private FragmentDiscoverBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Discover.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initControl();
        initData();
        initViews();


        return root;
    }

    private void initControl() {
            binding.button.setOnClickListener(view -> gotoTopic());
            binding.button2.setOnClickListener(view -> gotoTopic());
            binding.button3.setOnClickListener(view -> gotoTopic());
            binding.button4.setOnClickListener(view -> gotoTopic());
    }

    private void initData() {

    }

    private void initViews() {

    }


    private void gotoTopic() {
        Intent intent = new Intent(requireActivity(), DiscoverTopicActivity.class);
        requireActivity().startActivity(intent);
    }
}