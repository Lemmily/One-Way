package com.models;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ui.Tooltip;

/**
 * Created by emily on 20/05/15.
 */
public class HudController implements EntityListener {

    private static HudController INSTANCE;
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
    }


    public void setTooltip(String pText) {
        theTooltip.setText(pText);
    }

    public void setFPS(String pText) {
        theFPS.setText(pText);
    }

    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {

    }
}
