package com.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
    public static AssetManager manager = new AssetManager();
    public static Skin menuSkin;

    // In here we'll put everything that needs to be loaded in this format:
    // manager.load("file location in assets", fileType.class);
    //
    // libGDX AssetManager currently supports: Pixmap, Texture, BitmapFont,
    //     TextureAtlas, TiledAtlas, TiledMapRenderer, Music and Sound.
    public static void queueLoading() {
        manager.load("skins/uiskin.atlas", TextureAtlas.class);
        manager.load("images/defaultBtn.png", Texture.class);
    }

    //In here we'll create our skin, so we only have to create it once.
    public static void setMenuSkin() {
//        if (menuSkin == null)
//            menuSkin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
//                    manager.get("skins/menuSkin.pack", TextureAtlas.class));
        menuSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));

    }
    // This function gets called every render() and the AssetManager pauses the loading each frame
    // so we can still run menus and loading screens smoothly
    public static boolean update() {
        return manager.update();
    }

    public static Texture get(String s) {
        //TODO: some sort of catch for unloaded resources?
        return manager.get("images/" + s, Texture.class);
    }
}