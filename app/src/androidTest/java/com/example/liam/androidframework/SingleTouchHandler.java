package com.example.liam.androidframework;

import android.view.MotionEvent;
import android.view.View;

import com.example.liam.androidframework.framework.Input;
import com.example.liam.androidframework.framework.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 19/06/2015.
 */
public class SingleTouchHandler implements TouchHandler {

    private boolean isTouched;
    private int touchX;
    private int touchY;
    private Pool<Input.TouchEvent> touchEventPool;
    private List<Input.TouchEvent> touchEvents;
    private List<Input.TouchEvent> touchEventsBuffer;
    private float scaleX;
    private float scaleY;

    /** Attach a view and scale to the touch manager
     *
     * @param view what view to watch for touchevents
     * @param scaleX x sale of the gameview compared to the screen
     * @param scaleY y scale of the gameview compaed to the screen
     */
    public SingleTouchHandler(View view, float scaleX, float scaleY){
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

    /**check to see whether the view is being touched in some way
     *
     * @param pointer where to look
     * @return if pointer is false then return whether the screen is being touched or not, else instantly return false.
     */
    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            if(pointer == 0)
                return isTouched;
            else
                return false;
        }

    }

    /** return the X point of the screen where it's being touched
     *
     * @param pointer
     *
     * @return int of the x position
     */
    @Override
    public int getTouchX(int pointer) {
        synchronized (this){
            return touchX;
        }
    }

    /**return the Y point of the screen where it's being touched
     *
     * @param pointer
     * @return int of the y position
     */
    @Override
    public int getTouchY(int pointer) {
        synchronized (this){
            return touchY;
        }
    }

    /**returns a list of touch events to be acted. Removes the events from main pool as a result
     *
     * @return List of TouchEvent
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

    /**Check what type of touch is happening on the view and adds it to the touch events pool
     *
     * @param v View to watch the MotionEvents
     * @param event the current motionEvent that's currently happening
     * @return Once the event has been monitored return (this is just a check)
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this){
            Input.TouchEvent touchEvent = touchEventPool.newObject();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = Input.TouchEvent.TOUCH_DRAGGED;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = Input.TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;
            }

            touchEvent.x = touchX = (int)(event.getX() * scaleX);
            touchEvent.y = touchY = (int)(event.getY() * scaleY);
            touchEventsBuffer.add(touchEvent);

            return true;
        }
    }


}
