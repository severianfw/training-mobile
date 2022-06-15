package com.severianfw.training_mobile.viewmodel;

import android.app.Application;
import android.util.Log;

import com.severianfw.training_mobile.data.remote.ApiConfig;
import com.severianfw.training_mobile.data.remote.ApiService;
import com.severianfw.training_mobile.data.remote.NbaTeamItem;
import com.severianfw.training_mobile.data.repository.NbaTeamRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final NbaTeamRepository mNbaTeamRepository;

    public MainViewModel(Application mApplication) {
        mNbaTeamRepository = new NbaTeamRepository(mApplication);
    }

    private final MutableLiveData<List<NbaTeamItem>> _nbaTeams = new MutableLiveData<>();

    public LiveData<List<NbaTeamItem>> getNbaTeams() {
        return _nbaTeams;
    }

    //  -> online feature
    private final ApiService mApiService = ApiConfig.getApiService();

    public void callApi() {
        mApiService.getNbaTeams().enqueue(new Callback<List<NbaTeamItem>>() {
            @Override
            public void onResponse(Call<List<NbaTeamItem>> call, Response<List<NbaTeamItem>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        _nbaTeams.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NbaTeamItem>> call, Throwable t) {
                Log.d("ERROR_MESSAGE", "Api Call Failed");
            }
        });
    }

    //  -> offline feature
    public void insertNbaTeams(NbaTeamItem nbaTeamItem) {
        mNbaTeamRepository.insert(nbaTeamItem);
    }

    public void updateNbaTeam(NbaTeamItem nbaTeamItem) {
        mNbaTeamRepository.update(nbaTeamItem);
    }
}
