package com.example.liam.androidframework.exampleGame;

import com.example.liam.androidframework.framework.Game;
import com.example.liam.androidframework.framework.Graphics;
import com.example.liam.androidframework.framework.Screen;

/**
 * Created by Liam on 21/06/2015.
 */
public class SplashLoadingScreen extends Screen {


    public SplashLoadingScreen(Game game){
        super(game);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.setLoading(g.newImage("loading.jpg", Graphics.ImageFormat.RGB565));

        game.setScreen();

    }

    @Override
    public void paint(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}
