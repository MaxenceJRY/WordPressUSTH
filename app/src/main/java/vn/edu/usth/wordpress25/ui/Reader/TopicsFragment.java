package vn.edu.usth.wordpress25.ui.Reader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import vn.edu.usth.wordpress25.R;

public class TopicsFragment extends Fragment {

    public TopicsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topics, container, false);

        // Danh sách các mục
        String[] topics = {"add a Topic", "dailyprompt"};

        // Tạo ArrayAdapter để hiển thị danh sách trong ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, topics);

        // Tìm ListView trong layout
        ListView listView = rootView.findViewById(R.id.item1);

        // Đặt adapter cho ListView
        listView.setAdapter(adapter);

        // Xử lý sự kiện khi nút Discover more topics được nhấn
        rootView.findViewById(R.id.discoverButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý sự kiện tại đây
            }
        });

        return rootView;
    }
}

