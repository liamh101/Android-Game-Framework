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

        Assets.setMenu(g.newImage("menu.bmp", Graphics.ImageFormat.ARGB8888));
        Assets.setBackground(g.newImage("background.bmp", Graphics.ImageFormat.ARGB8888));

        Assets.setPlayer1(g.newImage("player.png", Graphics.ImageFormat.ARGB8888));
        Assets.setPlayer2(g.newImage("player2.png", Graphics.ImageFormat.ARGB8888));
        Assets.setPlayer3(g.newImage("player3.png", Graphics.ImageFormat.ARGB8888));
        Assets.setPlayerJ(g.newImage("playerj.png", Graphics.ImageFormat.ARGB8888));
        Assets.setPlayerD(g.newImage("playerd.png", Graphics.ImageFormat.ARGB8888));

        Assets.setEnemy1(g.newImage("enemy.png", Graphics.ImageFormat.ARGB8888));
        Assets.setEnemy2(g.newImage("enemy2.png", Graphics.ImageFormat.ARGB8888));
        Assets.setEnemy3(g.newImage("enemy3.png", Graphics.ImageFormat.ARGB8888));

        Assets.setTileDirt(g.newImage("Tile1.bmp", Graphics.ImageFormat.RGB565));
        Assets.setTileGrassDirt(g.newImage("Tile2.bmp", Graphics.ImageFormat.RGB565));
        Assets.setTileWater(g.newImage("Tile4.png", Graphics.ImageFormat.ARGB8888));
        Assets.setTileSpike(g.newImage("Spike.png", Graphics.ImageFormat.ARGB8888));

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
