package com.severianfw.training_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.severianfw.training_mobile.data.remote.NbaTeamItem;
import com.severianfw.training_mobile.databinding.ActivityOfflineBinding;
import com.severianfw.training_mobile.viewmodel.OfflineViewModel;
import com.severianfw.training_mobile.viewmodel.OfflineViewModelFactory;

import java.util.List;

public class OfflineActivity extends AppCompatActivity {

    private ActivityOfflineBinding mBinding;
    private OfflineViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOfflineBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mViewModel = new ViewModelProvider(this, new OfflineViewModelFactory(getApplication())).get(OfflineViewModel.class);

        mBinding.rvNbaTeams.setLayoutManager(new LinearLayoutManager(this));
        mViewModel.getOfflineNbaTeams().observe(this, nbaTeamItems -> {
            if (nbaTeamItems != null) {
                addRecyclerViewItem(nbaTeamItems);
            }
        });
    }

    void addRecyclerViewItem(List<NbaTeamItem> nbaTeamItems) {
        NbaTeamAdapter nbaTeamAdapter = new NbaTeamAdapter(nbaTeamItems);
        nbaTeamAdapter.isOfflineMode = true;
        nbaTeamAdapter.setOnItemClickCallback(new NbaTeamAdapter.OnItemClickCallBack() {
            @Override
            public void onDeleteClick(NbaTeamItem nbaTeamItem) {
                mViewModel.deleteNbaTeams(nbaTeamItem);
            }

            @Override
            public void onInsertClick(NbaTeamItem nbaTeamItem) {
                // no implementation needed
            }

            @Override
            public void onItemClick(NbaTeamItem nbaTeamItem) {
                BottomSheetUpdateNbaTeams bottomSheetUpdateNbaTeams = new BottomSheetUpdateNbaTeams();
                Bundle bundle = new Bundle();
                bundle.putInt("NBA_TEAM", nbaTeamItem.getId());
                bottomSheetUpdateNbaTeams.setArguments(bundle);
                bottomSheetUpdateNbaTeams.show(getSupportFragmentManager(), "UPDATE_NBA_TEAM_BOTTOM_SHEET");
            }
        });
        mBinding.rvNbaTeams.setAdapter(nbaTeamAdapter);
    }
}