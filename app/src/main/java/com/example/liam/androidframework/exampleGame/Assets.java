package com.example.liam.androidframework.exampleGame;

import com.example.liam.androidframework.framework.Image;
import com.example.liam.androidframework.framework.Music;

/**
 * Created by Liam on 21/06/2015.
 */
public class Assets {
    private static Image background, enemy1, enemy2, enemy3, player1, player2, player3, playerJ, playerD, tileDirt, tileGrassDirt, tileWater, tileSpike, loading, menu;
    private static Music music;


    public static void loadTitle(ExampleGame game){

        music = game.getAudio().createMusic("title.mp3");
        music.setVolume(0.85f);
        music.setLooping(false);
        music.play();
    }

    public static void stopMusic(){
        music.stop();
    }

    public static Image getMenu() {
        return menu;
    }

    public static void setMenu(Image menu) {
        Assets.menu = menu;
    }

    public static Image getLoading() {
        return loading;
    }

    public static void setLoading(Image loading) {
        Assets.loading = loading;
    }

    public static Image getBackground() {
        return background;
    }

    public static void setBackground(Image background) {
        Assets.background = background;
    }

    public static Image getEnemy1() {
        return enemy1;
    }

    public static void setEnemy1(Image enemy1) {
        Assets.enemy1 = enemy1;
    }

    public static Image getEnemy2() {
        return enemy2;
    }

    public static void setEnemy2(Image enemy2) {
        Assets.enemy2 = enemy2;
    }

    public static Image getEnemy3() {
        return enemy3;
    }

    public static void setEnemy3(Image enemy3) {
        Assets.enemy3 = enemy3;
    }

    public static Image getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Image player1) {
        Assets.player1 = player1;
    }

    public static Image getPlayer2() {
        return player2;
    }

    public static void setPlayer2(Image player2) {
        Assets.player2 = player2;
    }

    public static Image getPlayer3() {
        return player3;
    }

    public static void setPlayer3(Image player3) {
        Assets.player3 = player3;
    }

    public static Image getPlayerJ() {
        return playerJ;
    }

    public static void setPlayerJ(Image playerJ) {
        Assets.playerJ = playerJ;
    }

    public static Image getPlayerD() {
        return playerD;
    }

    public static void setPlayerD(Image playerD) {
        Assets.playerD = playerD;
    }


    public static Image getTileDirt() {
        return tileDirt;
    }

    public static void setTileDirt(Image tileDirt) {
        Assets.tileDirt = tileDirt;
    }

    public static Image getTileGrassDirt() {
        return tileGrassDirt;
    }

    public static void setTileGrassDirt(Image tileGrassDirt) {
        Assets.tileGrassDirt = tileGrassDirt;
    }

    public static Image getTileWater() {
        return tileWater;
    }

    public static void setTileWater(Image tileWater) {
        Assets.tileWater = tileWater;
    }

    public static Image getTileSpike() {
        return tileSpike;
    }

    public static void setTileSpike(Image tileSpike) {
        Assets.tileSpike = tileSpike;
    }

    public static Music getMusic() {
        return music;
    }

    public static void setMusic(Music music) {
        Assets.music = music;
    }
}
