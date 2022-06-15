package com.severianfw.training_mobile.data.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NbaTeamEntity {
    @PrimaryKey
    private int id;
    private String name;
    private String logo;

    public NbaTeamEntity(int id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


}
