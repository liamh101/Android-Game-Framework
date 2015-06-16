package com.example.liam.androidframework;

/**
 * Created by Liam on 16/06/2015.
 */
public interface Image {

    //returns Width of image
    public int getWidth();
    //returns Height of image
    public int getHeight();
    //returns file format (of accepted format type)
    public Graphics.ImageFormat getFormat();
    //distroys image variable
    public void dispose();
}
