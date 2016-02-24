package com.lemmily.game.models;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lemmily.game.board.InventoryWindowActor;
import com.lemmily.game.board.StatisticWindowActor;
import com.lemmily.game.board.Tile;
import com.lemmily.game.board.TileActor;
import com.lemmily.game.models.actions.ActionCondition;
import com.lemmily.game.models.actions.PlayerAction;
import com.lemmily.game.models.entities.Monster;
import com.lemmily.game.models.entities.Player;
import com.lemmily.game.models.signals.ActionTaken;
import com.lemmily.game.ui.Tooltip;

/**
 * Created by emily on 20/05/15.
 */
public class HudController implements EntityListener {

    private static HudController INSTANCE;
    private final Stage theStage;
	private InventoryWindowActor theInvWin;
	private StatisticWindowActor theStatWin;
    private Tooltip theTooltip;
    private Tooltip theFPS;

    public HudController(Stage pStage) {
        theStage = pStage;

        theTooltip = new Tooltip(pStage.getViewport().getScreenWidth() / 2, 0);
        theStage.addActor(theTooltip);

        theFPS = new Tooltip(0, pStage.getViewport().getScreenHeight() - 30);
        theStage.addActor(theFPS);

        theStatWin = new StatisticWindowActor("Statistics");
        theStage.addActor(theStatWin);
        theStatWin.setLabel("hahahahahahahah \n ahahahah ");
        theStatWin.setPosition(Gdx.graphics.getWidth() - theStatWin.getWidth(), Gdx.graphics.getHeight() - theStatWin.getHeight());

        theInvWin = new InventoryWindowActor("Inventory");
        theStage.addActor(theInvWin);
		theInvWin.setPosition(Gdx.graphics.getWidth() - theStatWin.getWidth(), 0);


        TileActor lFakeButton = new TileActor();
        lFakeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Switched to action Denied");
                PlayerController.get().setCurrentAction(new PlayerAction() {
                    // is this weird gross code?
                    {
//                        theName = "Conditional Action";
                        setName("Conditional Denied Action");
                        //instance initializer
                        theActionCondition = new ActionCondition() {
                            @Override
                            public boolean hasMetCondition() {
                                // some test condition.
                                System.out.println("Dummy Condition has NOT been met! (always NOT met)");
                                return false;
                            }
                        };
                    }

                    @Override
                    public ActionTaken execute() {
                        if (checkCondition()) {
                            System.out.println("action 1 performed on nothing");
                            return new ActionTaken(5);
                        } else {
                            return null;
                        }
                    }

                    @Override
                    public ActionTaken execute(Monster pMonster) {
                        if (checkCondition()) {
                            System.out.println("action 1 performed on monster");
                            return new ActionTaken(5);
                        } else {
                            return null;
                        }
                    }

                    @Override
                    public ActionTaken execute(Player pPlayer) {
                        if (checkCondition()) {
                            System.out.println("action 1 performed on player");
                            return new ActionTaken(5);
                        } else {
                            return null;
                        }
                    }
                });
            }
        });
        theStage.addActor(lFakeButton);

        lFakeButton = new TileActor();
        lFakeButton.setPosition(80, 0);
        lFakeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Switched to action 1");
                PlayerController.get().setCurrentAction(new PlayerAction() {
                    // is this weird gross code?
                    {
//                        theName = "Conditional Action";
                        setName("Conditional Action");
                        //instance initializer
                        theActionCondition = new ActionCondition() {
                            @Override
                            public boolean hasMetCondition() {
                                // some test condition.
                                System.out.println("Dummy Condition has been met! (always met)");
                                return true;
                            }
                        };
                    }
                    @Override
                    public ActionTaken execute() {
                        if (checkCondition()) {
                            System.out.println("action 1 performed on nothing");
                            return new ActionTaken(5);
                        } else {
                            return null;
                        }
                    }

                    @Override
                    public ActionTaken execute(Monster pMonster) {
                        checkCondition();
                        System.out.println("action 1 performed on monster");
                        return new ActionTaken(5);
                    }

                    @Override
                    public ActionTaken execute(Player pPlayer) {
                        checkCondition();
                        System.out.println("action 1 performed on player");
                        return new ActionTaken(5);
                    }
                });
            }
        });
        theStage.addActor(lFakeButton);


        lFakeButton = new TileActor();
        lFakeButton.setPosition(160, 0);
        lFakeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Switched to action 2");
                PlayerController.get().setCurrentAction(new PlayerAction() {
                    @Override
                    public ActionTaken execute() {
                        System.out.println("action 2 performed on nothing");
                        return new ActionTaken(10);
                    }

                    @Override
                    public ActionTaken execute(Monster pMonster) {
                        System.out.println("action 2 performed on monster");
                        return new ActionTaken(10);
                    }

                    @Override
                    public ActionTaken execute(Player pPlayer) {
                        System.out.println("action 2 performed on player");
                        return new ActionTaken(10);
                    }

                });
            }
        });
        theStage.addActor(lFakeButton);

        lFakeButton = new TileActor();
        lFakeButton.setPosition(240, 0);
        lFakeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Switched to action 2");
                PlayerController.get().setCurrentAction(new PlayerAction() {
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


                });
            }
        });
        theStage.addActor(lFakeButton);
    }

    public static HudController init(Stage pStage) {
        INSTANCE = new HudController(pStage);
        return INSTANCE;
    }

    public static HudController get() {
        return INSTANCE;
    }

    public void setTooltip(String pText) {
        theTooltip.setText(pText);
    }

    /**
     * used for fps display.
     *
     * @param pText set top left label to this text
     */
    public void setFPS(String pText) {
        theFPS.setText(pText);
    }

    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    public StatisticWindowActor statWin() {
        return theStatWin;
    }
}
