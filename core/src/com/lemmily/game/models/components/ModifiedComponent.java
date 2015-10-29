package com.lemmily.game.models.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.lemmily.game.models.entities.StatMod;

/**
 * Created by emily on 06/08/15.
 */
public class ModifiedComponent implements Component {

    private final Array<StatMod> theModifiedStats;

    public ModifiedComponent() {
        theModifiedStats = new Array<StatMod>();
    }


    public void addModifier(StatMod pStatMod) {
        theModifiedStats.add(pStatMod);
    }
}
