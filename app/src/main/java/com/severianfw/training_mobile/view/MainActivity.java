package com.severianfw.training_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.severianfw.training_mobile.data.remote.NbaTeamItem;
import com.severianfw.training_mobile.databinding.ActivityMainBinding;
import com.severianfw.training_mobile.viewmodel.MainViewModel;
import com.severianfw.training_mobile.viewmodel.MainViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mViewModel = new ViewModelProvider(this, new MainViewModelFactory(getApplication())).get(MainViewModel.class);

        mViewModel.callApi();

        mBinding.rvNbaTeams.setLayoutManager(new LinearLayoutManager(this));
        mViewModel.getNbaTeams().observe(this, nbaTeamItems -> {
            if (nbaTeamItems != null) {
                setupRecyclerView(nbaTeamItems);
            }
        });
        mBinding.tvOfflineMode.setOnClickListener(view -> startActivity(new Intent(this, OfflineActivity.class)));
    }

    void setupRecyclerView(List<NbaTeamItem> nbaTeamItems) {
        NbaTeamAdapter nbaTeamAdapter = new NbaTeamAdapter(nbaTeamItems);
        nbaTeamAdapter.setOnItemClickCallback(new NbaTeamAdapter.OnItemClickCallBack() {
            @Override
            public void onDeleteClick(NbaTeamItem nbaTeamItem) {
                // no implementation needed
            }

            @Override
            public void onInsertClick(NbaTeamItem nbaTeamItem) {
                mViewModel.insertNbaTeams(nbaTeamItem);
            }

            @Override
            public void onItemClick(NbaTeamItem nbaTeamItem) {
                // no implementation needed
            }
        });
        mBinding.rvNbaTeams.setAdapter(nbaTeamAdapter);
    }
}