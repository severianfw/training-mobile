package com.severianfw.training_mobile.utils;

import com.severianfw.training_mobile.data.local.NbaTeamEntity;
import com.severianfw.training_mobile.data.remote.NbaTeamItem;

import java.util.ArrayList;
import java.util.List;

public class NbaTeamMapper {
    public NbaTeamEntity mapItemToEntity(NbaTeamItem nbaTeamItem) {
        return new NbaTeamEntity(nbaTeamItem.getId(), nbaTeamItem.getName(), nbaTeamItem.getLogo());
    }

    public List<NbaTeamItem> mapEntitiesToItems(List<NbaTeamEntity> nbaTeamEntities) {
        List<NbaTeamItem> nbaTeamItems = new ArrayList<>();
        for (NbaTeamEntity item: nbaTeamEntities) {
            nbaTeamItems.add(new NbaTeamItem(item.getId(), item.getName(), item.getLogo()));
        }
        return nbaTeamItems;
    }
}
