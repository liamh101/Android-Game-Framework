package com.example.liam.frameworkEngine;

import android.graphics.Bitmap;

import com.example.liam.framework.Graphics;
import com.example.liam.framework.Image;

/**
 * Created by Liam on 18/06/2015.
 *
 * Image management system so it's easier to manage graphical elements in the game
 */
public class AndroidImage implements Image {

    private Bitmap bitmap;
    private Graphics.ImageFormat format;

    /**Image used to be rendered on screen holding all infomation about the image
     *
     * @param bitmap the image that can be later rendered on the screen
     * @param format the file format that
     */
    public AndroidImage(Bitmap bitmap, Graphics.ImageFormat format){
        this.bitmap = bitmap;
        this.format = format;
    }

    /**Return width of the bitmap image being rendered.
     *
     * @return int of the width of the bitmap image
     */
    @Override
    public int getWidth(){
        return bitmap.getWidth();
    }

    /**returns the height of the bitmap image being rendered.
     *
     * @return int of the height of the bitmap image
     */
    @Override
    public int getHeight(){
        return bitmap.getHeight();
    }

    /** returns the format of the bitmap image being rendered
     *
     * e.g. RGB565, ARGB4444, ARGB_8888
     *
     * @return ImageFormat type used by the Graphics framework
     */
    @Override
    public Graphics.ImageFormat getFormat(){
        return format;
    }

    /**Destorys image in memory.
     *
     */
    public void dispose(){
        bitmap.recycle();
    }

    /**Returns bitmap image stored in memeory
     *
     * @return bitmap image type rendered in game
     */
    public Bitmap getBitmap(){
        return  bitmap;
    }

    /**set the image that will be used in the game
     *
     * @param bitmap bitmap image type
     */
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }



}
