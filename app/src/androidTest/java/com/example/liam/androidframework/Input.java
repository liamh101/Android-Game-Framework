package com.example.liam.androidframework;

import java.util.List;

/**
 * Created by Liam on 16/06/2015.
 */
public interface Input {

    public static class TouchEvent {

        //Different states of the touchscreen
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_HOLD = 3;

        //Type of touch
        public int type;

        //position
        public int x, y;

        //multiple points of at which the screen is being touched
        public int pointer;
    }

    //Is the screen currently being touched?
    public boolean isTouchDown(int pointer);

    //Get X position at which the screen is being touched
    public int getTouchX();

    //Get Y position at which the screen is being touched
    public int getTouchY();

    //List of recent touch events
    public List<TouchEvent> getTouchEvents();

}
