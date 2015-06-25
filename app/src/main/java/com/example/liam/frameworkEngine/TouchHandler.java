package com.example.liam.frameworkEngine;

import android.view.View;

import com.example.liam.framework.Input;

import java.util.List;

/**
 * Created by Liam on 19/06/2015.
 *
 * Interface to set up basic methods to be used in any Touch management system
 */
public interface TouchHandler extends View.OnTouchListener {

    //Returns if the screen is being touched
    public boolean isTouchDown(int pointer);

    //Get X position of where the phone is being touched
    public int getTouchX(int pointer);

    //Get Y position of where the phone is being touched
    public int getTouchY(int pointer);

    //Returns a list of TouchEvents
    public List<Input.TouchEvent> getTouchEvents();
}
