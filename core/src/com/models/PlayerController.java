package com.models;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.InputProcessor;
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
public class PlayerController implements InputProcessor {
    private static PlayerController INSTANCE;

    public static PlayerController get() {
        return INSTANCE;
    }
    public static void init() {
        INSTANCE = new PlayerController();
    }

    /**
     * objects that need to listen to player actions must register to this.
     */
    private final Signal<ActionTaken> thePlayerControlsSignal;
    private boolean thePlayerMoved;

    private PlayerAction theCurrentAction;

    /**
     * Singleton(??) To control the interactions made by the player
     */
    private PlayerController() {
        thePlayerControlsSignal = new Signal<>();

        theCurrentAction = null;
    }

    public void actionPerformed() {
        if(theCurrentAction == null) {
//            thePlayerControlsSignal.dispatch(theCurrentAction.execute());
            HudController.get().statWin().setLabel("");
        } else {
            ActionTaken lActionTaken = theCurrentAction.execute();
            if (lActionTaken != null) {
                thePlayerControlsSignal.dispatch(lActionTaken);
                thePlayerMoved = lActionTaken.thePlayerMoved;
            }
        }
    }

    public void actionPerformed(GameEntity pGameEntity) {
        if(theCurrentAction == null) {
            //update stats window.
            HudController.get().statWin().setLabel(pGameEntity.getStats());
        } else {
            ActionTaken lActionTaken = null;
            if (Components.ENEMY.get(pGameEntity) != null) {
                lActionTaken = theCurrentAction.execute((Monster) pGameEntity);
                thePlayerControlsSignal.dispatch(lActionTaken);
            } else if (Components.PLAYER.get(pGameEntity) != null) {
                lActionTaken = theCurrentAction.execute((Player) pGameEntity);
                thePlayerControlsSignal.dispatch(lActionTaken);
            }
            thePlayerMoved = lActionTaken != null && lActionTaken.thePlayerMoved;
        }
//        else {
//            if (Components.ENEMY.get(pGameEntity) != null) {
//                //show enemy stats
//            }
//        }
    }

    /**
     * register a listener to the player's actions.
     *
     * @param pListener
     */
    public void registerListener(ActionListener pListener) {
        thePlayerControlsSignal.add(pListener);
    }

    /**
     * deregister a listener from the player's actions.
     *
     * @param pListener
     */
    public void deregisterListener(ActionListener pListener) {
        thePlayerControlsSignal.remove(pListener);
    }

    /**
     * @return boolean for whether the player has moved.
     */
    public boolean playerMoved() {
        return thePlayerMoved;
    }

    /**
     * @param pPlayerMoved se tthe player moved to this.
     */
    public void setPlayerMoved(boolean pPlayerMoved) {
        thePlayerMoved = pPlayerMoved;
    }

    /**
     * @param pAction set the current action to this.
     */
    public void setCurrentAction(PlayerAction pAction) {
        if(pAction == null) {
            theCurrentAction = null;
            HudController.get().setTooltip("Action selection cleared");

        } else {
            HudController.get().setTooltip(pAction.getName() + " was selected");
            theCurrentAction = pAction;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchdown");
        setCurrentAction(null);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
