package com.severianfw.training_mobile.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.severianfw.training_mobile.data.remote.NbaTeamItem;
import com.severianfw.training_mobile.databinding.BottomSheetUpdateNbaTeamsBinding;
import com.severianfw.training_mobile.viewmodel.MainViewModel;
import com.severianfw.training_mobile.viewmodel.MainViewModelFactory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class BottomSheetUpdateNbaTeams extends BottomSheetDialogFragment {

    private BottomSheetUpdateNbaTeamsBinding mBinding;
    private MainViewModel mMainViewModel;

    private int nbaTeamId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = BottomSheetUpdateNbaTeamsBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMainViewModel = new ViewModelProvider(this, new MainViewModelFactory(requireActivity().getApplication())).get(MainViewModel.class);
        mMainViewModel.callApi();

        if (getArguments() != null) {
            nbaTeamId = getArguments().getInt("NBA_TEAM");
        }

        mBinding.rvNbaTeams.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mMainViewModel.getNbaTeams().observe(this, nbaTeamItems -> {
            if (nbaTeamItems != null) {
                addRecyclerViewItem(nbaTeamItems);
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    void addRecyclerViewItem(List<NbaTeamItem> nbaTeamItems) {
        NbaTeamAdapter nbaTeamAdapter = new NbaTeamAdapter(nbaTeamItems);
        nbaTeamAdapter.setOnItemClickCallback(new NbaTeamAdapter.OnItemClickCallBack() {
            @Override
            public void onDeleteClick(NbaTeamItem nbaTeamItem) {
                // no implementation needed
            }

            @Override
            public void onInsertClick(NbaTeamItem nbaTeamItem) {
                // no implementation needed
            }

            @Override
            public void onItemClick(NbaTeamItem nbaTeamItem) {
                NbaTeamItem newNbaTeamItem = new NbaTeamItem(
                        nbaTeamId,
                        nbaTeamItem.getName(),
                        nbaTeamItem.getLogo()
                );
                mMainViewModel.updateNbaTeam(newNbaTeamItem);
                dismiss();
            }
        });
        mBinding.rvNbaTeams.setAdapter(nbaTeamAdapter);
    }
}
