package com.models;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.board.StatisticWindowActor;
import com.board.Tile;
import com.board.TileActor;
import com.models.actions.PlayerAction;
import com.models.entities.Monster;
import com.models.entities.Player;
import com.models.signals.ActionTaken;
import com.ui.Tooltip;

/**
 * Created by emily on 20/05/15.
 */
public class HudController implements EntityListener {

    private static HudController INSTANCE;
    private StatisticWindowActor theStatWin;
    private Tooltip theTooltip;
    private Tooltip theFPS;

    public static HudController init(Stage pStage) {
        INSTANCE = new HudController(pStage);
        return INSTANCE;
    }

    public static HudController get() {
        return INSTANCE;
    }


    private final Stage theStage;

    public HudController(Stage pStage) {
        theStage = pStage;

        theTooltip = new Tooltip(pStage.getViewport().getScreenWidth()/2, 60);
        theStage.addActor(theTooltip);

        theFPS= new Tooltip(0, pStage.getViewport().getScreenHeight() - 30);
        theStage.addActor(theFPS);

        theStatWin = new StatisticWindowActor("Statistics");
        theStage.addActor(theStatWin);
        theStatWin.setLabel("hahahahahahahah \n ahahahah ");
        theStatWin.setPosition(Gdx.graphics.getWidth() - theStatWin.getWidth(), Gdx.graphics.getHeight() - theStatWin.getHeight());



        TileActor lFakeButton = new TileActor();
        lFakeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Switched to action 1");
                PlayerController.get().setCurrentAction(new PlayerAction() {
                    @Override
                    public ActionTaken execute() {
                        System.out.println("action 1 performed on nothing");
                        return new ActionTaken(5);
                    }

                    @Override
                    public ActionTaken execute(Monster pMonster) {
                        System.out.println("action 1 performed on monster");
                        return new ActionTaken(5);
                    }

                    @Override
                    public ActionTaken execute(Player pPlayer) {
                        System.out.println("action 1 performed on player");
                        return new ActionTaken(5);
                    }
                });
            }
        });
        theStage.addActor(lFakeButton);

        lFakeButton = new TileActor();
        lFakeButton.setPosition(100, 0);
        lFakeButton.addListener(new ClickListener(){
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
        lFakeButton.setPosition(250, 0);
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
