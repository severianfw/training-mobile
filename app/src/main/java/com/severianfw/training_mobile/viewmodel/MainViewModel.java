package com.severianfw.training_mobile.viewmodel;

import com.severianfw.training_mobile.data.remote.ApiConfig;
import com.severianfw.training_mobile.data.remote.ApiService;
import com.severianfw.training_mobile.data.remote.NbaTeamItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<NbaTeamItem>> _nbaTeams = new MutableLiveData<>();
    public LiveData<List<NbaTeamItem>> getNbaTeamItems() {
        return _nbaTeams;
    }

//    sifat datanya gabisa diubah

    private final ApiService mApiService = ApiConfig.getApiService();

    public void callApi() {
        mApiService.getNbaTeams().enqueue(new Callback<List<NbaTeamItem>>() {
            @Override
            public void onResponse(Call<List<NbaTeamItem>> call, Response<List<NbaTeamItem>> response) {
                if (response.isSuccessful()) {
                    _nbaTeams.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<NbaTeamItem>> call, Throwable t) {

            }
        });
    }

}
