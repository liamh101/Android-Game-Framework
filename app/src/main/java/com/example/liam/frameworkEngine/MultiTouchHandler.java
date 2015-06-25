package com.example.liam.frameworkEngine;

import android.view.MotionEvent;
import android.view.View;

import com.example.liam.framework.Input;
import com.example.liam.framework.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 19/06/2015.
 *
 * Manages muli-touch input within the game
 */
public class MultiTouchHandler implements TouchHandler {
    private static final int MAX_TOUCHPOINTS = 10;

    private boolean[] isTouched;
    private int[] touchX;
    private int[] touchY;
    private int[] id;
    private Pool<Input.TouchEvent> touchEventPool;
    private List<Input.TouchEvent> touchEvents;
    private List<Input.TouchEvent> touchEventsBuffer;
    private float scaleX;
    private float scaleY;

    /** Creates a manager to monitor the viewport for multitouch input
     *
     * @param view the viewport the monitor
     * @param scaleX the x scale at which the game view is compared to the view
     * @param scaleY the y scale at which thre game view is compared to the view
     */
    public MultiTouchHandler(View view, float scaleX, float scaleY ){
        touchX = new int[MAX_TOUCHPOINTS];
        touchY = new int[MAX_TOUCHPOINTS];
        id = new int[MAX_TOUCHPOINTS];
        touchEvents = new ArrayList<Input.TouchEvent>();
        touchEventsBuffer = new ArrayList<Input.TouchEvent>();

        Pool.PoolObjectFactory<Input.TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };

        touchEventPool = new Pool<Input.TouchEvent>(factory, 100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /** Is the screen currently being touched by at least one finger.
     *
     * @param pointer How many fingers are currently touching the screen
     * @return if user is touching the screen between the bounds, return the number of fingers on screen.
     * else return false.
     */
    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index < 0 || index >= MAX_TOUCHPOINTS)
                return false;
            else
                return isTouched[index];
        }
    }

    /**get the x position where the player is currently touching the screen
     *
     * @param pointer how many fingers are touching the screen
     * @return if user is touching the screen between the bounds, return the number of fingers on screen.
     * else return false.
     */
    @Override
    public int getTouchX(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index < 0 || index >= MAX_TOUCHPOINTS)
                return 0;
            else
                return touchX[index];

        }
    }

    /**get the Y position where the player is currently touching the screen
     *
     * @param pointer how many fingers are touching the screen
     * @return if user is touching the screen between the bounds, return the number of fingers on screen.
     * else return false.
     */
    @Override
    public int getTouchY(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index < 0 || index >= MAX_TOUCHPOINTS)
                return 0;
            else
                return touchY[index];
        }
    }

    /** Return a list of all current touchevents after last check. This is so you can react to them
     *
     * @return List of touchevents
     */
    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this){
            int len = touchEvents.size();

            for(int i = 0; i < len; i++)
                touchEventPool.free(touchEvents.get(i));

            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();

            return touchEvents;
        }
    }

    /**What to do when the view is being touched, finds what kind of touch it is and assigns it within
     * the buffer.
     *
     * @param v which view to monitor
     * @param event the current that is happening
     * @return just a check to see if this method is working
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this){
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >>  MotionEvent.ACTION_POINTER_ID_SHIFT;
            int pointerCount = event.getPointerCount();
            Input.TouchEvent touchEvent;

            for(int i = 0; i < MAX_TOUCHPOINTS; i++){
                if( i >= pointerCount){
                    isTouched[i] = false;
                    id[i] = -1;
                    continue;
                }
                int powerId = event.getPointerId(i);

                if(event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex){
                    // if it's an up/down/cancel/out event, mask the id to see if we should process it for this touch point
                    continue;
                }

                switch (action){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                        touchEvent.pointer = powerId;
                        touchEvent.x = touchX[i] = (int) (event.getX() * scaleX);
                        touchEvent.y  = touchY[i] = (int) (event.getY() * scaleY);
                        isTouched[i] = true;
                        id[i] = powerId;
                        touchEventsBuffer.add(touchEvent);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_UP;
                        touchEvent.pointer = powerId;
                        touchEvent.x = touchX[i] = (int) (event.getX() * scaleX);
                        touchEvent.y = touchY[i] = (int) (event.getY() * scaleY);
                        isTouched[i] = false;
                        id[i] = -1;
                        touchEventsBuffer.add(touchEvent);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        touchEvent = touchEventPool.newObject();
                        touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                        touchEvent.pointer = powerId;
                        touchEvent.x = touchX[i] = (int) (event.getX() * scaleX);
                        touchEvent.y = touchY[i] = (int) (event.getY() * scaleY);
                        isTouched[i] = true;
                        id[i] = powerId;
                        touchEventsBuffer.add(touchEvent);
                        break;
                }
            }
            return true;
        }
    }

    /** Returns the id of the finger
     *
     * @param pointerId how many fingers are touching the screen
     * @return returns a new id number if there's space else return false
     */
    private int getIndex(int pointerId){
        for(int i = 0; i < MAX_TOUCHPOINTS; i++){
            if (id[i] == pointerId)
                return i;
        }

        return -1;
    }
}
