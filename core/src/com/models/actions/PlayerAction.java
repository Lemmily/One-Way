package com.models.actions;

import com.models.entities.Monster;
import com.models.entities.Player;
import com.models.signals.ActionTaken;

/**
 * Created by emily on 20/05/15.
 */
public interface PlayerAction {

    public ActionTaken execute();

    /**
     * for performing action on a monster (eg. attack)
     * @param pMonster
     */
    public ActionTaken execute(Monster pMonster);

    /**
     * for performing action on the player (eg. potion drink)
     * @param pPlayer
     */
    public ActionTaken execute(Player pPlayer);


//    /**
//     *
//     * @return object with ap cost.
//     */
//    public ActionTaken getCost();


}
