package com.models.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.models.entities.StatMod;

/**
 * Created by emily on 06/08/15.
 */
public class ModifiedComponent extends Component {

    private final Array<StatMod> theModifiedStats;

    public ModifiedComponent() {
        theModifiedStats = new Array<StatMod>();
    }


    public void addModifier(StatMod pStatMod) {
        theModifiedStats.add(pStatMod);
    }
}
