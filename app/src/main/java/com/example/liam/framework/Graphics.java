package com.example.liam.framework;

import android.graphics.Paint;

/**
 * Created by Liam on 16/06/2015.
 */
public interface Graphics {

    //Formats accepted by Android
    public static enum ImageFormat{
        ARGB8888, ARGB4444, RGB565
    }

    //new Image variable
    public Image newImage(String fileName, ImageFormat format);

    //Clear the screen of currently rendered items
    public void clearScreen(int colour);

    //Draw line with custom values on the screen
    public void drawLine(int x, int y, int x2, int y2, int colour);

    //Draw a rectangle with custom values on the screen
    public void drawRect(int x, int y, int width, int height, int colour);

    //Draw a image variable on the screen with more custombility
    public void drawImage(Image image, int x, int y, int srcX, int srcY,
                          int srcWidth, int srcHeight);

    //Draw basic image on the screen
    public void drawImage(Image Image, int x, int y);

    //Draw text onto the screen
    void drawString(String text, int x, int y, Paint paint);

    //get width of screen that's being rendered
    public int getWidth();

    //get height of screen that's being rendered
    public int getHeight();

    //draw ARGB file format
    public void drawARGB(int i, int j, int k, int l);
}
