package vn.edu.usth.wordpress25.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.wordpress25.databinding.ItemChooseDomainBinding;
import vn.edu.usth.wordpress25.model.Domain;


public class ChooseDomainAdapter extends RecyclerView.Adapter<ChooseDomainAdapter.ViewHolder> {
    private List<Domain> datas;
    private Context context;
    private OnCheckItem onCheckItem;

    private boolean onBind;

    public ChooseDomainAdapter(Context context, List<Domain> datas, OnCheckItem onCheckItem) {
        this.datas = datas;
        this.context = context;
        this.onCheckItem = onCheckItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChooseDomainBinding itemBinding = ItemChooseDomainBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemBinding);
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Domain data = datas.get(position);
        holder.itemBinding.ckbDomain.setText(data.name.trim());
        holder.itemBinding.ckbDomain.setChecked(data.isCheck);
        onBind = true;
        holder.itemBinding.ckbDomain.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Invoke the callback
            if (onCheckItem != null) {
                setCheckOnlyItem();
                data.isCheck = isChecked;
                onCheckItem.onCheckedChanged(data, isChecked);
                if(!onBind) {
                    notifyDataSetChanged();
                }
            }
        });
        onBind = false;
    }

    private void setCheckOnlyItem(){
        int index = checkedExist();
        if (index != -1){ //exist => uncheck
            datas.get(index).isCheck = false;
        }
    }

    private int checkedExist(){
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isCheck) return i;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilter(List<Domain> filterdNames) {
        this.datas = filterdNames;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemChooseDomainBinding itemBinding;

        ViewHolder(ItemChooseDomainBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            this.setIsRecyclable(false);
        }
    }

    public interface OnCheckItem {
        void onCheckedChanged(Domain domain, boolean isChecked);
    }
}
