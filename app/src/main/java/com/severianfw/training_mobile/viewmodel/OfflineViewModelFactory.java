package com.severianfw.training_mobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class OfflineViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;

    public OfflineViewModelFactory(Application mApplication) {
        this.mApplication = mApplication;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OfflineViewModel(mApplication);
    }
}
