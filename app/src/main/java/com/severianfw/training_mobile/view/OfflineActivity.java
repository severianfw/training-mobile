package com.severianfw.training_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.severianfw.training_mobile.databinding.ActivityOfflineBinding;
import com.severianfw.training_mobile.viewmodel.OfflineViewModel;

public class OfflineActivity extends AppCompatActivity {

    private ActivityOfflineBinding mBinding;

    private OfflineViewModel mOfflineViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOfflineBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mOfflineViewModel = new OfflineViewModel(getApplication());

        mBinding.rvNbaTeams.setLayoutManager(new LinearLayoutManager(this));
        mOfflineViewModel.getOfflineData().observe(this, nbaTeamItems -> {
            mBinding.rvNbaTeams.setAdapter(new NbaTeamAdapter(nbaTeamItems));
        });
    }
}