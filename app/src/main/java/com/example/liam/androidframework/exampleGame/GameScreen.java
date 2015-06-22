package com.example.liam.androidframework.exampleGame;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.liam.androidframework.framework.Game;
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

        if(state == GameState.Ready){


        }


    }

    public void updateReady(List<Input.TouchEvent> touchEvents){


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
