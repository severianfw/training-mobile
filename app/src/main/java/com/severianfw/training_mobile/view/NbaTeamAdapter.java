package com.severianfw.training_mobile.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.severianfw.training_mobile.R;
import com.severianfw.training_mobile.data.remote.NbaTeamItem;
import com.severianfw.training_mobile.databinding.ItemNbaTeamBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NbaTeamAdapter extends RecyclerView.Adapter<NbaTeamAdapter.ViewHolder> {

    private final List<NbaTeamItem> nbaTeamItems;

    public NbaTeamAdapter(List<NbaTeamItem> nbaTeamItems) {
        this.nbaTeamItems = nbaTeamItems;
    }

    OnItemClickCallBack onItemClickCallback;
    Boolean isOfflineMode = false;

    public void setOnItemClickCallback(OnItemClickCallBack onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNbaTeamBinding binding = ItemNbaTeamBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(nbaTeamItems.get(position));
        holder.mBinding.btnSave.setOnClickListener(view -> onItemClickCallback.onInsertClick(nbaTeamItems.get(position)));
        holder.mBinding.btnDelete.setOnClickListener(view -> onItemClickCallback.onDeleteClick(nbaTeamItems.get(position)));
        holder.itemView.setOnClickListener(view -> onItemClickCallback.onItemClick(nbaTeamItems.get(position)));

        if (isOfflineMode) {
            holder.mBinding.btnSave.setVisibility(View.GONE);
            holder.mBinding.btnDelete.setVisibility(View.VISIBLE);
        } else {
            holder.mBinding.btnSave.setVisibility(View.VISIBLE);
            holder.mBinding.btnDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return nbaTeamItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemNbaTeamBinding mBinding;

        public ViewHolder(@NonNull ItemNbaTeamBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        void bind(NbaTeamItem nbaTeamItem) {
            mBinding.tvName.setText(nbaTeamItem.getName());
            Glide.with(mBinding.getRoot())
                    .load(nbaTeamItem.getLogo())
                    .into(mBinding.ivLogo);
        }
    }

    public interface OnItemClickCallBack {
        void onDeleteClick(NbaTeamItem nbaTeamItem);

        void onInsertClick(NbaTeamItem nbaTeamItem);

        void onItemClick(NbaTeamItem nbaTeamItem);
    }
}
