package com.models;

import com.badlogic.ashley.signals.Signal;
import com.models.actions.PlayerAction;
import com.models.components.Components;
import com.models.entities.GameEntity;
import com.models.entities.Monster;
import com.models.entities.Player;
import com.models.listeners.ActionListener;
import com.models.signals.ActionTaken;

/**
 * Created by emily on 19/05/15.
 */
public class PlayerController {
    private static PlayerController INSTANCE;

    public static PlayerController get() {
        return INSTANCE;
    }
    public static void init() {
        INSTANCE = new PlayerController();
    }


    private final Signal<ActionTaken> thePlayerControlsSignal;
    private boolean thePlayerMoved;

    private PlayerAction theCurrentAction;

    /**
     * Singleton(??) To control the interactions made by the player
     */
    private PlayerController() {
        thePlayerControlsSignal = new Signal<>();

        theCurrentAction = new PlayerAction() {
            @Override
            public ActionTaken execute() {
                System.out.println("action performed on nothing");
                return new ActionTaken(5);
            }

            @Override
            public ActionTaken execute(Monster pMonster) {
                System.out.println("action performed on monster");
                return new ActionTaken(5);
            }

            @Override
            public ActionTaken execute(Player pPlayer) {
                System.out.println("action performed on player");
                return new ActionTaken(5);
            }
        };
    }

    public void actionPerformed() {
        thePlayerControlsSignal.dispatch(theCurrentAction.execute());
        thePlayerMoved = true;
    }
//    public void actionPerformed(Player pPlayer) {
//        thePlayerControlsSignal.dispatch(theCurrentAction.execute(pPlayer));
//        thePlayerMoved = true;
//    }
    public void actionPerformed(GameEntity pGameEntity) {
        if (Components.ENEMY.get(pGameEntity) != null)
            thePlayerControlsSignal.dispatch(theCurrentAction.execute((Monster)pGameEntity));
        if (Components.PLAYER.get(pGameEntity) != null)
            thePlayerControlsSignal.dispatch(theCurrentAction.execute((Player)pGameEntity));

        thePlayerMoved = true;
    }

    public void registerListener(ActionListener pListener) {
        thePlayerControlsSignal.add(pListener);
    }

    public boolean playerMoved() {
        return thePlayerMoved;
    }

    public void setPlayerMoved(boolean pPlayerMoved) {
        thePlayerMoved = pPlayerMoved;
    }


    public void setCurrentAction(PlayerAction pAction) {
        theCurrentAction = pAction;
    }
}
