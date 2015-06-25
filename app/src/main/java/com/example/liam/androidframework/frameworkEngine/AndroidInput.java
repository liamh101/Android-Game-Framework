package com.example.liam.androidframework.frameworkEngine;

import android.content.Context;
import android.os.Build;
import android.view.View;

import com.example.liam.androidframework.framework.Input;

import java.util.List;

/**
 * Created by Liam on 19/06/2015.
 *
 * Manages Input controller based on OS version
 */
public class AndroidInput implements Input {

    private TouchHandler touchHandler;

    /**constructor that instigates a Single touch handler or Multi touch handler based on OS version
     *
     * @param context current context of app
     * @param view viewport used for the app
     * @param scaleX X scale of screen compared to game
     * @param scaleY Y scale of screen compared to game
     */
    public AndroidInput(Context context, View view, float scaleX, float scaleY){
        if(Integer.parseInt(Build.VERSION.SDK) < 5)
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        else
            touchHandler = new MultiTouchHandler(view,scaleX,scaleY);
    }


    /** Is screen currently being touched
     *
     * @param pointer pointer finger that could currently be touching the screen
     * @return boolean of whether screen is being touched
     */
    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    /** Get the current x position on the screen
     *
     * @param pointer finger that could currently be touching the screen
     * @return int of X position on screen
     */
    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    /** Get the current y position on the screen
     *
     * @param pointer finger that could currently be touching the screen
     * @return int of y position on screen
     */
    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    /**Get a list of touch events that have happened on the screen
     *
     * @return list of touch events
     */
    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}
