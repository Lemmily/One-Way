package com.models.actions;

import com.models.entities.Monster;
import com.models.entities.Player;
import com.models.signals.ActionTaken;

/**
 * Created by emily on 20/05/15.
 */
public abstract class PlayerAction {

    protected ActionCondition theActionCondition = null;

    public abstract ActionTaken execute();

    /**
     * for performing action on a monster (eg. attack)
     * @param pMonster
     */
    public abstract ActionTaken execute(Monster pMonster);

    /**
     * for performing action on the player (eg. potion drink)
     * @param pPlayer
     */
    public abstract ActionTaken execute(Player pPlayer);

    /**
     * @return true if has a condition, false otherwise
     */
    public boolean hasCondition() {
        return theActionCondition != null;
    }

    public boolean checkCondition() {
        return theActionCondition.hasMetCondtion();
    }
//    /**
//     *
//     * @return object with ap cost.
//     */
//    public ActionTaken getCost();
}
