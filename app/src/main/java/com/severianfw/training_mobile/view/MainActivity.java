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

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private MainViewModel mMainViewModel;

    private NbaTeamAdapter mNbaTeamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mMainViewModel = new ViewModelProvider(this,
                new MainViewModelFactory(getApplication())
        ).get(MainViewModel.class);

        mMainViewModel.callApi();

        mBinding.rvNbaTeams.setLayoutManager(new LinearLayoutManager(this));
        mMainViewModel.getNbaTeamItems().observe(this, nbaTeamItems -> {
            mNbaTeamAdapter = new NbaTeamAdapter(nbaTeamItems);
            mNbaTeamAdapter.setOnItemClickCallback(new NbaTeamAdapter.OnItemClickCallback() {
                @Override
                public void onInsertClick(NbaTeamItem nbaTeamItem) {
                    mMainViewModel.insertNbaItem(nbaTeamItem);
                }
            });
            mBinding.rvNbaTeams.setAdapter(mNbaTeamAdapter);
        });

        mBinding.btnOfflineMode.setOnClickListener(view -> startActivity(new Intent(this, OfflineActivity.class)));

    }
}