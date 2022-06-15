package com.severianfw.training_mobile.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/severianfw/training-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
