package com.example.liam.androidframework.exampleGame;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.liam.androidframework.framework.Game;
import com.example.liam.androidframework.framework.Graphics;
import com.example.liam.androidframework.framework.Image;
import com.example.liam.androidframework.framework.Input;
import com.example.liam.androidframework.framework.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Liam on 21/06/2015.
 */
public class GameScreen extends Screen {

    private Image currentSprite, character, character2, character3, characterJ, characterD,flyingEnemy,flyingEnemy2,flyingEnemy3;
    private Animation animP, animE;

    private Thread thread;
    private static Player player;
    private static FlyingEnemy en1;
    private static int score;
    private ArrayList<Tile> tilearray;
    private static Background bg1, bg2;
    private boolean debug;
    private GameState state;

    Paint paint, paint2;


    enum GameState {
        Running, Dead, Ready;
    }

    public GameScreen(Game game){
        super(game);

        bg1 = new Background(0,0);
        bg2 = new Background(2160,0);
        player = new Player();
        state = GameState.Ready;

        character = Assets.getPlayer1();
        character2 = Assets.getPlayer2();
        character3 = Assets.getPlayer3();

        animP = new Animation();
        animP.addFrame(character, 1250);
        animP.addFrame(character2, 50);
        animP.addFrame(character3, 50);
        animP.addFrame(character2, 50);

        currentSprite = animP.getImage();

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

    }

    private void loadMap(){
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;


        Scanner reader = new Scanner (ExampleGame.getMap());

        while (true) {
            String line = reader.nextLine();

            if( line == null) {
                reader.close();
                break;
            }

            if (!line.startsWith("#")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }
        height = lines.size();

        for(int j = 0; j < 12; j++){
            String line = (String) lines.get(j);
            for(int i = 0; i <width; i++){

                if(i < line.length()) {
                    char ch = line.charAt(i);
                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
                    tilearray.add(t);
                }
            }
        }

    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if(state == GameState.Ready)
            updateReady(touchEvents);



    }

    public void updateReady(List<Input.TouchEvent> touchEvents){
        if(touchEvents.size() > 0)
            state = GameState.Running;



    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.getBackground(), bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.getBackground(), bg2.getBgX(), bg2.getBgY());
        paintTiles(g);

        g.drawImage(currentSprite, player.getCenterX(), player.getCenterY());

        if(state == GameState.Ready)
            drawReadyUI();
    }

    private void paintTiles(Graphics g){
        for(int i = 0; i < tilearray.size(); i++){
            Tile t = (Tile) tilearray.get(i);
            if(t.getType() == 0)
                g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
        }
    }

    private void drawReadyUI(){
        Graphics g = game.getGraphics();

        g.drawARGB(155,0,0,0);
        g.drawString("Tap to Start", 400, 240, paint);
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

    public static Background getBg1() {
        return bg1;
    }

    public static void setBg1(Background bg1) {
        GameScreen.bg1 = bg1;
    }

    public static Background getBg2() {
        return bg2;
    }

    public static void setBg2(Background bg2) {
        GameScreen.bg2 = bg2;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        GameScreen.player = player;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameScreen.score = score;
    }

    public static FlyingEnemy getEn1() {
        return en1;
    }

    public static void setEn1(FlyingEnemy en1) {
        GameScreen.en1 = en1;
    }
}
