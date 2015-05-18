package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.board.Board;
import com.board.BoardActor;

/**
 * Created by emily on 18/05/15.
 */
public class GameScreen implements Screen{
    private Board theBoard;
    private BoardActor theBoardActor;
    private Stage theStage;

    @Override
    public void show() {
        theStage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(theStage);
        theBoard = new Board(4);
        theBoardActor = new BoardActor(theBoard);
        theBoardActor.setPosition(100, 100);

        theStage.addActor(theBoardActor);
        theStage.setDebugAll(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        theStage.act(delta);
        theStage.draw();
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
