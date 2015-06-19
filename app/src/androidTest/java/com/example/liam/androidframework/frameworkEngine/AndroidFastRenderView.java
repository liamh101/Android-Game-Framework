package com.example.liam.androidframework.frameworkEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Liam on 18/06/2015.
 *
 * Quickly render images to be displayed onto the screen
 */
public class AndroidFastRenderView extends SurfaceView implements Runnable  {

    private AndroidGame game;
    private Bitmap frameBuffer;
    private Thread renderThread;
    private SurfaceHolder holder;
    private volatile boolean running;

    /**Constructor to set up the and and thread.
     *
     * @param game Game that is going to be rendered
     * @param frameBuffer the frame view that is going to be used to push the render onto the screen
     */
    public AndroidFastRenderView(AndroidGame game, Bitmap frameBuffer){
        super(game);
        renderThread = null;
        running = true;
        this.game = game;
        this.frameBuffer = frameBuffer;
        this.holder = getHolder();

    }

    /**If the game is paused due to game being inactive, resume actitiy
     *
     */
    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    /**If game isn't paused then method updates the game (e.g. character moving) and rerenders the view.
     *
     */
    public void run(){
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        //If game isn't paused
        while(running){
            if(holder.getSurface().isValid())
                continue;

            //Deltatime used to keep consistent game movement without making the game unplayable
            float deltaTime = (System.nanoTime() - startTime) / 10000000.000f;
            startTime = System.nanoTime();

            if(deltaTime > 3.15)
                deltaTime = (float) 3.15;

            //Update game
            game.getCurrentScreen().update(deltaTime);
            //Rerender game
            game.getCurrentScreen().paint(deltaTime);

            //Push render to the screen;
            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds();
            canvas.drawBitmap(frameBuffer, null, dstRect, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    /**Stops activity of game used for when the player has to the phone for activities outside the game
     *
     */
    public void pause(){
        running = false;
        while(true){
            try {
                renderThread.join();
                break;
            }catch (InterruptedException e){
                new Error("RenderView Pause exception");
            }
        }
    }

}
