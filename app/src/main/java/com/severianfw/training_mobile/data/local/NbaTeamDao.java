package com.severianfw.training_mobile.data.local;

import com.severianfw.training_mobile.data.remote.NbaTeamItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public
interface NbaTeamDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(NbaTeamItem nbaTeam);

    @Delete
    void delete(NbaTeamItem nbaTeam);

    @Update
    void update(NbaTeamItem nbaTeam);

    @Query("SELECT * from NbaTeamItem")
    LiveData<List<NbaTeamItem>> getNbaTeams();
}
