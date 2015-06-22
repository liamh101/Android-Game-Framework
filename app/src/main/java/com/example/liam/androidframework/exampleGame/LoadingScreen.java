package com.example.liam.androidframework.exampleGame;

import com.example.liam.androidframework.framework.Game;
import com.example.liam.androidframework.framework.Graphics;
import com.example.liam.androidframework.framework.Screen;

/**
 * Created by Liam on 21/06/2015.
 */
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game){
        super(game);
    }
    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

        Assets.setMenu(g.newImage("menu.jpg", Graphics.ImageFormat.RGB565));
        Assets.setBackground(g.newImage("background.png", Graphics.ImageFormat.RGB565));

        Assets.setPlayer1(g.newImage("player1.png", Graphics.ImageFormat.ARGB4444));
        Assets.setPlayer2(g.newImage("player2.png", Graphics.ImageFormat.ARGB4444));
        Assets.setPlayer3(g.newImage("player3.png", Graphics.ImageFormat.ARGB4444));
        Assets.setPlayerJ(g.newImage("playerJ.png", Graphics.ImageFormat.ARGB4444));
        Assets.setPlayerD(g.newImage("playerD.png", Graphics.ImageFormat.ARGB4444));

        Assets.setEnemy1(g.newImage("enemy.png", Graphics.ImageFormat.ARGB4444));
        Assets.setEnemy2(g.newImage("enemy2.png", Graphics.ImageFormat.ARGB4444));
        Assets.setEnemy3(g.newImage("enemy3.png", Graphics.ImageFormat.ARGB4444));

        Assets.setTileDirt(g.newImage("tile1.png", Graphics.ImageFormat.RGB565));
        Assets.setTileGrassDirt(g.newImage("tile2.png", Graphics.ImageFormat.RGB565));
        Assets.setTileWater(g.newImage("tile4.png", Graphics.ImageFormat.RGB565));
        Assets.setTileSpike(g.newImage("spike.png", Graphics.ImageFormat.ARGB4444));

        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void paint(float deltaTime) {

        Graphics g = game.getGraphics();
        g.drawImage(Assets.getLoading(),0,0);
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
