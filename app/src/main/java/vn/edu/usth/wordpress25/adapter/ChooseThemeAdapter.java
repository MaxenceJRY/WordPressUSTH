package vn.edu.usth.wordpress25.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.wordpress25.databinding.ItemChooseThemeBinding;
import vn.edu.usth.wordpress25.model.ChooseTopic;


public class ChooseThemeAdapter extends RecyclerView.Adapter<ChooseThemeAdapter.ViewHolder> {
    private List<ChooseTopic> datas;
    private Context context;

    private boolean showBtnDelete = false;
    private OnClick onClickListener;

    public ChooseThemeAdapter(Context context, List<ChooseTopic> datas, boolean showBtnDelete, OnClick onClickListener) {
        this.datas = datas;
        this.context = context;
        this.showBtnDelete = showBtnDelete;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChooseThemeBinding itemBinding = ItemChooseThemeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChooseTopic data = datas.get(position);
        holder.itemBinding.imgTopic.setImageResource(data.srcImage);
        holder.itemBinding.imgTopic.setClipToOutline(true);
        holder.itemBinding.tvTopic.setText(data.name);
        if (data.website != null && !data.website.isEmpty()) {
            holder.itemBinding.tvWeb.setVisibility(View.VISIBLE);
            holder.itemBinding.tvWeb.setText(data.website);
        } else {
            holder.itemBinding.tvWeb.setVisibility(View.GONE);
        }
        holder.itemBinding.imgDelete.setVisibility(showBtnDelete ? View.VISIBLE : View.GONE);
        holder.itemBinding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) onClickListener.onDeleteItem(datas.get(holder.getLayoutPosition()), holder.getLayoutPosition());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) onClickListener.onClick(datas.get(holder.getLayoutPosition()), holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemChooseThemeBinding itemBinding;

        ViewHolder(ItemChooseThemeBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

        }
    }

    public interface OnClick {
        void onClick(ChooseTopic data, int position);

        void onDeleteItem(ChooseTopic data, int position);
    }
}
