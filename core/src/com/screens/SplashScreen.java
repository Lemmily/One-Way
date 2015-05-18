package com.screens;

import com.LibGdxUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.utils.Assets;

public class SplashScreen implements Screen {
    private Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
    private Image splashImage = new Image(texture);
    private Stage stage = new Stage();

    public boolean animationDone = false;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        if(Assets.update()){ // check if all files are loaded
            if(animationDone){ // when the animation is finished, go to MainMenu()
                Assets.setMenuSkin(); // uses files to create menuSkin
                LibGdxUtils.game.setScreen(new GameScreen());
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        stage.addActor(splashImage);

        splashImage.addAction(Actions.sequence(Actions.alpha(0)
                ,Actions.fadeIn(0.75f),Actions.delay(1.5f),Actions.run(new Runnable() {
            @Override
            public void run() {
                animationDone = true;
            }
        })));

        //Assets.manager.clear(); 
        //not necessary, only when splash called more then once
        Assets.queueLoading();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        texture.dispose();
        stage.dispose();
    }
}