package com.lemmily.game.models.actions;


import com.lemmily.game.models.entities.Monster;
import com.lemmily.game.models.entities.Player;
import com.lemmily.game.models.signals.ActionTaken;

/**
 * Created by emily on 19/08/15.
 */
public class SpellAction extends PlayerAction {


    @Override
    public ActionTaken execute() {
        return null;
    }

    @Override
    public ActionTaken execute(Monster pMonster) {
        return null;
    }

    @Override
    public ActionTaken execute(Player pPlayer) {
        return null;
    }


    @Override
    public boolean checkCondition() {
        //if player has enough "learned spell" points to use the action.
        //and if the player has enough mana points to use said spell.
        //return true

        //else
        return false;
    }
}
