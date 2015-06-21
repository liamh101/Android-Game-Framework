package com.example.liam.androidframework.frameworkEngine;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

import com.example.liam.androidframework.framework.Audio;
import com.example.liam.androidframework.framework.FileIO;
import com.example.liam.androidframework.framework.Game;
import com.example.liam.androidframework.framework.Graphics;
import com.example.liam.androidframework.framework.Input;
import com.example.liam.androidframework.framework.Screen;

/**
 * Created by Liam on 17/06/2015.
 *
 * The main game element that takes each individual part of the framework and compines them together to work with each other
 */
public abstract class AndroidGame extends Activity implements Game {
    private AndroidFastRenderView renderView;
    private Graphics graphics;
    private Audio audio;
    private Input input;
    private FileIO fileIO;
    private Screen screen;
    private PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //If the phone is in portrait view true else false
        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        //If isPortrait is true then use first value, else use second value.
        int frameBufferHeight = isPortrait ? 480 : 800;
        int frameBufferWidth = isPortrait ? 800 : 480;

        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();

        //Instigate variables here
        renderView = new AndroidFastRenderView(this,frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this,renderView, scaleX,scaleY);
        screen = getInitScreen();
        setContentView(renderView);




        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Test Game");

    }

    @Override
    public void onResume(){
        //Resume game and activity
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }

    @Override
    public void onPause(){
        //Pause game and all activity
        super.onPause();
        wakeLock.release();
        renderView.pause();
        screen.pause();

        if(isFinishing())
            screen.dispose();
    }

    //GET METHODS FOR INPUT FILEIO AND GRAPHICS
    @Override
    public Input getInput(){
        return input;
    }

    @Override
    public FileIO getFileIO(){
        return fileIO;
    }

    @Override
    public Graphics getGraphics(){
        return graphics;
    }

    @Override
    public Audio getAudio(){
        return audio;
    }


    //Setter for screen type
    public void setScreen(Screen screen){
        //If null vaule is passed
        if(screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        //Remove old screen
        this.screen.pause();
        this.screen.dispose();
        //Add new screen to view
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    //Getter for screen type
    public Screen getCurrentScreen(){
        return screen;
    }
}
