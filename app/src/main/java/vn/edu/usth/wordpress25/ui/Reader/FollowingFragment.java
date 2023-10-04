package vn.edu.usth.wordpress25.ui.Reader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import vn.edu.usth.wordpress25.R;
import vn.edu.usth.wordpress25.adapter.FollowingTopicAdapter;
import vn.edu.usth.wordpress25.databinding.FragmentFollowingBinding;
import vn.edu.usth.wordpress25.eventbus.MessageEvent;
import vn.edu.usth.wordpress25.model.ChooseTopic;
import vn.edu.usth.wordpress25.ui.Reader.discover.DetailPostActivity;
import vn.edu.usth.wordpress25.ui.Reader.following.FilterBottomSheet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentFollowingBinding binding;

    private FilterBottomSheet filterBottomSheet;

    private List<ChooseTopic> followingTopics = new ArrayList<>();
    private FollowingTopicAdapter followingTopicAdapter;

    public FollowingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment following.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowingFragment newInstance(String param1, String param2) {
        FollowingFragment fragment = new FollowingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        filterBottomSheet = new FilterBottomSheet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFollowingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initControl();
        initData();
        initViews();


        return root;
    }

    private void initControl() {
        binding.lnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterBottomSheet();
            }
        });

        //end filter
        binding.imgEndFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent(MessageEvent.END_FILTER));
            }
        });
    }

    private void showFilterBottomSheet() {
        if (!filterBottomSheet.isAdded())
            filterBottomSheet.show(getChildFragmentManager(), "");
    }

    private void initData() {

    }

    private void initViews() {
        showDefaultData(true, "", -1);
        setupApdater();
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    private void showDefaultData(boolean isDefault, String topic, int index) {
        followingTopics.clear();
        if (isDefault){
            binding.tvDefaultFilter.setVisibility(View.VISIBLE);
            binding.lnFilterTopic.setVisibility(View.GONE);
            followingTopics.addAll(getArts());
            followingTopics.addAll(getFoods());
            followingTopics.addAll(getPhotographies());
            followingTopics.addAll(getTechnologies());
        } else {
            binding.tvFilterTopic.setText(topic);
            binding.tvDefaultFilter.setVisibility(View.GONE);
            binding.lnFilterTopic.setVisibility(View.VISIBLE);
            getDataFilter(index);
        }
        if(followingTopicAdapter != null){
            followingTopicAdapter.notifyDataSetChanged();
        }
        binding.rvTopic.smoothScrollToPosition(0);
    }

    private void setupApdater() {
        followingTopicAdapter = new FollowingTopicAdapter(requireActivity(), followingTopics, (data, position) -> {
            Intent intent = new Intent(requireActivity(), DetailPostActivity.class);
            intent.putExtra("data", new Gson().toJson(data));
            startActivity(intent);
        });
        binding.rvTopic.setAdapter(followingTopicAdapter);
    }

    private void getDataFilter(int index) {
        switch (index) {
            case 0:
                followingTopics.addAll(getArts());
                break;
            case 1:
                followingTopics.addAll(getFoods());
                break;
            case 2:
                followingTopics.addAll(getPhotographies());
                break;
            case 3:
                followingTopics.addAll(getTechnologies());
                break;
        }
    }

    private ArrayList<ChooseTopic> getArts() {
        return new ArrayList<>(Arrays.asList(
                new ChooseTopic(
                        1,
                        R.drawable.img_art_1,
                        "JaZzArt en València",
                        "paintinginvalencia.com",
                        "El beso que nunca es delito – The Kiss That’s Never a Crime",
                        getString(R.string.content_art_1)
                ),
                new ChooseTopic(
                        2,
                        R.drawable.img_art_2,
                        "Mastery",
                        "vjknutson.org",
                        "Spotlight Art – Dandelion Clocks – Painting of the Day",
                        getString(R.string.content_art_2)
                ),
                new ChooseTopic(
                        3,
                        R.drawable.img_art_3,
                        "K Anil Roy Speaks",
                        "kanilroyspeaks.in",
                        "Lighting the lamp",
                        getString(R.string.content_art_3)
                )
        )
        );
    }

    private ArrayList<ChooseTopic> getFoods() {
        return new ArrayList<>(Arrays.asList(
                new ChooseTopic(
                        1,
                        R.drawable.img_food_1,
                        "ELLIE",
                        "newcreationsministries.wordpress.com",
                        "Are You Hungrier After Drinking Sodas?",
                        getString(R.string.content_food_1)
                ),
                new ChooseTopic(
                        2,
                        R.drawable.img_food_2,
                        "Gail Dorna",
                        "snapshotsincursive.com",
                        "Cereal Milk Smoothie",
                        getString(R.string.content_food_2)
                ),
                new ChooseTopic(
                        3,
                        R.drawable.img_food_3,
                        "Kuliner Enak",
                        "arellachef.wordpress.com",
                        "Chicken Meatball Veggie Soup – Recipe",
                        getString(R.string.content_food_3)
                )
        )
        );
    }

    private ArrayList<ChooseTopic> getPhotographies() {
        return new ArrayList<>(Arrays.asList(
                new ChooseTopic(
                        1,
                        R.drawable.img_photography_1,
                        "Rebecca Goes Rendezvous",
                        "rebeccagoesrendezvous.com",
                        "Colonia del Sacramento, Uruguay",
                        getString(R.string.content_photography_1)
                ),
                new ChooseTopic(
                        2,
                        R.drawable.img_photography_2,
                        "ThatTravelLadyInHerShoes",
                        "thecadyluckleedy.com",
                        "Pafos Port and City, near Limassol, Cyprus",
                        getString(R.string.content_photography_2)
                ),
                new ChooseTopic(
                        3,
                        R.drawable.img_photography_3,
                        "Empty Nest Homesteading",
                        "emptynesthomesteading.com",
                        "Somewhere in Seattle",
                        getString(R.string.content_photography_3)
                )
        )
        );
    }

    private ArrayList<ChooseTopic> getTechnologies() {
        return new ArrayList<>(Arrays.asList(
                new ChooseTopic(
                        1,
                        R.drawable.img_technology_1,
                        "Debbie Gravett",
                        "debbiegrav.wordpress.com",
                        "Close It or Drop It",
                        getString(R.string.content_technology_1)
                ),
                new ChooseTopic(
                        2,
                        R.drawable.img_technology_2,
                        "Sora News 24",
                        "soranews24.com",
                        "Real-life pilotable anime-style robots now on sale in Japan",
                        getString(R.string.content_technology_2)
                )
        )
        );
    }

    @SuppressLint("NotifyDataSetChanged")
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        if (event.getMsg().contentEquals(MessageEvent.START_FILTER)) {
            showDefaultData(false, event.getTopic().name, event.getIndexFilter());
        }
        if (event.getMsg().contentEquals(MessageEvent.END_FILTER)) {
            showDefaultData(true, "", -1);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}