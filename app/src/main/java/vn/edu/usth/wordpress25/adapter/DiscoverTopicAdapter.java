package vn.edu.usth.wordpress25.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

=======
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
>>>>>>> origin/master
import vn.edu.usth.wordpress25.databinding.ItemDiscoverTopicBinding;
import vn.edu.usth.wordpress25.model.ChooseTopic;


public class DiscoverTopicAdapter extends RecyclerView.Adapter<DiscoverTopicAdapter.ViewHolder> {
    private List<ChooseTopic> datas;
    private Context context;

    private OnClick onClickListener;

    public DiscoverTopicAdapter(Context context, List<ChooseTopic> datas, OnClick onClickListener) {
        this.datas = datas;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDiscoverTopicBinding itemBinding = ItemDiscoverTopicBinding .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChooseTopic data = datas.get(position);
        holder.itemBinding.imgTopic.setImageResource(data.srcImage);
        holder.itemBinding.tvTopic.setText(data.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(datas.get(holder.getLayoutPosition()), holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemDiscoverTopicBinding itemBinding;

        ViewHolder(ItemDiscoverTopicBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

        }
    }

    public interface OnClick {
        void onClick(ChooseTopic data, int position);
    }
}
