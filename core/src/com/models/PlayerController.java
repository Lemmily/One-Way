package com.models;

import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.InputProcessor;
import com.board.Tile;
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
            PlayerAction lPlayerAction = new PlayerAction() {
                @Override
                public ActionTaken execute() {
                    GameController.get().getBoardActor().dropFirstTile();
                    return new ActionTaken(0);
                }

                @Override
                public ActionTaken execute(Monster pMonster) {
                    return new ActionTaken(0);
                }

                @Override
                public ActionTaken execute(Player pPlayer) {
                    return new ActionTaken(0);
                }
            };
            thePlayerControlsSignal.dispatch(lPlayerAction.execute());
            thePlayerMoved = false;
        } else {
            thePlayerControlsSignal.dispatch(theCurrentAction.execute());
            thePlayerMoved = true;
        }
    }

    public void actionPerformed(GameEntity pGameEntity) {
        if(theCurrentAction == null) {
//            thePlayerControlsSignal.dispatch(theCurrentAction.execute());


            //temp action to test destruction.
            PlayerAction lDestructTileAction = new PlayerAction() {
                @Override
                public ActionTaken execute() {
                    GameController.get().getBoardActor().dropFirstTile();
                    return new ActionTaken(0);
                }

                @Override
                public ActionTaken execute(Monster pMonster) {
                    Tile lTile = GameController.get().getBoardActor().board().findTileWithMonster(pMonster);
                    GameController.get().getBoardActor().dropTile(lTile.theActor);
                    return new ActionTaken(0);
                }

                @Override
                public ActionTaken execute(Player pPlayer) {
                    return new ActionTaken(0);
                }

            };
            thePlayerControlsSignal.dispatch(lDestructTileAction.execute((Monster)pGameEntity));
            thePlayerMoved = false;
        } else {
                if (Components.ENEMY.get(pGameEntity) != null)
                thePlayerControlsSignal.dispatch(theCurrentAction.execute((Monster) pGameEntity));
            if (Components.PLAYER.get(pGameEntity) != null)
                thePlayerControlsSignal.dispatch(theCurrentAction.execute((Player) pGameEntity));

            thePlayerMoved = true;
        }
//        else {
//            if (Components.ENEMY.get(pGameEntity) != null) {
//                //show enemy stats
//            }
//        }
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
