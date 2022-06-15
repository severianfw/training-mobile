package com.severianfw.training_mobile.viewmodel;

import android.app.Application;

import com.severianfw.training_mobile.data.remote.NbaTeamItem;
import com.severianfw.training_mobile.data.repository.NbaTeamRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class OfflineViewModel extends ViewModel {

    private final NbaTeamRepository mNbaTeamRepository;

    public OfflineViewModel(Application mApplication) {
        this.mNbaTeamRepository = new NbaTeamRepository(mApplication);
    }

    public LiveData<List<NbaTeamItem>> getOfflineNbaTeams() {
        return mNbaTeamRepository.getOfflineNbaTeams();
    }

    public void deleteNbaTeams(NbaTeamItem nbaTeamItem) {
        mNbaTeamRepository.delete(nbaTeamItem);
    }
}
