package com.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.board.Board;
import com.board.BoardActor;
import com.board.TileActor;
import com.models.HudController;
import com.models.PlayerController;
import com.models.actions.PlayerAction;
import com.models.components.Components;
import com.models.components.StatsComponent;
import com.models.entities.Monster;
import com.models.entities.Player;
import com.models.signals.ActionTaken;
import com.models.systems.ActionSystem;
import com.utils.Enums;

import java.util.Comparator;

/**
 * Created by emily on 18/05/15.
 */
public class GameScreen implements Screen{
    private Board theBoard;
    private BoardActor theBoardActor;
    private Stage theStage;
    private Stage theHudStage;
    private Engine theEngine;
    private HudController theHudController;


    @Override
    public void show() {

        PlayerController.init();

        theStage = new Stage(new ScreenViewport());
        theHudStage = new Stage(new ScreenViewport());

        theHudController = HudController.init(theHudStage);

        Gdx.input.setInputProcessor(new InputMultiplexer(theStage, theHudStage));
        theBoard = new Board(4);
        theBoardActor = new BoardActor(theBoard);
        theBoardActor.setPosition(100, 100);

        theStage.addActor(theBoardActor);
        theStage.setDebugAll(true);

        theEngine = new Engine();


        //make family (entities that have a certain set of components)
        theEngine.addSystem(new ActionSystem(new Comparator<Entity>() {
            @Override
            /**
             * sorts into descending order of dexterity
             */
            public int compare(Entity pEntity_1, Entity pEntity_2) {
                StatsComponent lStat_1 = Components.STATS.get(pEntity_1);
                StatsComponent lStat_2 = Components.STATS.get(pEntity_2);

                return lStat_2.get(Enums.Attributes.Dexterity).getValue() - lStat_1.get(Enums.Attributes.Dexterity).getValue();
            }
        }));


        Monster lMonster = new Monster();
        PlayerController.get().registerListener(lMonster);
        theEngine.addEntity(lMonster);

        lMonster = new Monster();
        PlayerController.get().registerListener(lMonster);
        theEngine.addEntity(lMonster);
        lMonster.add(new StatsComponent(5, 15, 5, 5, 5, 5, 5));

        TileActor lFakeButton = new TileActor();
        lFakeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
        theHudStage.addActor(lFakeButton);

        lFakeButton = new TileActor();
        lFakeButton.setPosition(100, 0);
        lFakeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
        theHudStage.addActor(lFakeButton);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        theHudController.setFPS("FPS: " + Gdx.graphics.getFramesPerSecond());
        theEngine.update(delta);

        theStage.act(delta);
        theStage.draw();

        theHudStage.act();
        theHudStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
