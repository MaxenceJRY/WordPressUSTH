package vn.edu.usth.wordpress25.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import vn.edu.usth.wordpress25.databinding.ItemTopicBinding;
import vn.edu.usth.wordpress25.model.Topic;


public class FilterTopicsAdapter extends RecyclerView.Adapter<FilterTopicsAdapter.ViewHolder> {
    private List<Topic> datas;
    private Context context;

    private OnClick onClickListener;

    public FilterTopicsAdapter(Context context, List<Topic> datas, OnClick onClickListener) {
        this.datas = datas;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTopicBinding itemBinding = ItemTopicBinding .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic data = datas.get(position);
        holder.itemBinding.tvTopic.setText(data.name);
        holder.itemView.setOnClickListener(view -> onClickListener.onClick(datas.get(holder.getLayoutPosition()), holder.getLayoutPosition()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemTopicBinding itemBinding;

        ViewHolder(ItemTopicBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

        }
    }

    public interface OnClick {
        void onClick(Topic data, int position);
    }
}
