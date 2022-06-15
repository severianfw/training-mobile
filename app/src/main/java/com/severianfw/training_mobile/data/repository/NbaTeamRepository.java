package com.severianfw.training_mobile.data.repository;

import android.app.Application;

import com.severianfw.training_mobile.data.local.NbaTeamDao;
import com.severianfw.training_mobile.data.local.NbaTeamEntity;
import com.severianfw.training_mobile.data.local.NbaTeamRoomDatabase;
import com.severianfw.training_mobile.data.remote.NbaTeamItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;


public class NbaTeamRepository {

    private final NbaTeamDao nbaTeamDao;
    private final ExecutorService executorService;

    public NbaTeamRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        NbaTeamRoomDatabase db = NbaTeamRoomDatabase.getDatabase(application);
        nbaTeamDao = db.nbaTeamDao();
    }

    public LiveData<List<NbaTeamItem>> getOfflineNbaTeams() {
        return nbaTeamDao.getNbaTeams();
    }

    public void insert(final NbaTeamItem nbaTeam) {
        executorService.execute(() -> nbaTeamDao.insert(nbaTeam));
    }

    public void delete(final NbaTeamItem nbaTeam) {
        executorService.execute(() -> nbaTeamDao.delete(nbaTeam));
    }

    public void update(final NbaTeamItem nbaTeam) {
        executorService.execute(() -> nbaTeamDao.update(nbaTeam));
    }
}
