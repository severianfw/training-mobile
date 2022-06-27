package com.severianfw.training_mobile.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNbaTeamBinding binding = ItemNbaTeamBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(nbaTeamItems.get(position));
    }

    @Override
    public int getItemCount() {
        return nbaTeamItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemNbaTeamBinding binding;

        public ViewHolder(@NonNull ItemNbaTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(NbaTeamItem nbaTeamItem) {
            binding.tvName.setText(nbaTeamItem.getName());
            Glide.with(binding.getRoot())
                    .load(nbaTeamItem.getLogo())
                    .into(binding.ivLogo);
        }
    }
}
