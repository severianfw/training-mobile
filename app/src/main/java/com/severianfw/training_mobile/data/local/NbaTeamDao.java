package com.severianfw.training_mobile.data.local;

import com.severianfw.training_mobile.data.remote.NbaTeamItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NbaTeamDao {

    // Masukin data ke database
    @Insert
    void insert(NbaTeamItem nbaTeamItem);

    // Baca/Read data dari database
    @Query("SELECT * from NbaTeamItem")
    LiveData<List<NbaTeamItem>> getNbaTeams();
}
