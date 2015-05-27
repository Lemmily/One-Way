package com.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.board.Board;
import com.board.BoardActor;
import com.models.GameController;
import com.models.HudController;
import com.models.PlayerController;
import com.models.components.Components;
import com.models.components.StatsComponent;
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

        theStage = new Stage(new ScreenViewport());
        theHudStage = new Stage(new ScreenViewport());

        PlayerController.init();
        theEngine = new Engine();
        GameController.init(theEngine);
        theBoardActor = GameController.get().getBoard();
        theBoardActor.setPosition(100, 100);
        theHudController = HudController.init(theHudStage);

        Gdx.input.setInputProcessor(new InputMultiplexer(theStage, theHudStage, PlayerController.get()));

        theStage.addActor(theBoardActor);
        theStage.setDebugAll(true);

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
