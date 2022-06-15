package com.severianfw.training_mobile.data.remote;

import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NbaTeamItem {

    @SerializedName("name")
    private String name;

    @SerializedName("logo")
    private String logo;

    @PrimaryKey
    @SerializedName("id")
    private final int id;

    public NbaTeamItem(int id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

