package com.example.liam.framework;

/**
 * Created by Liam on 16/06/2015.
 */
public interface Audio {

    //Assign a audio file on disk as a music variable
    public Music createMusic(String file);

    //Assign a audio file on disk as a sound variable
    public Sound createSound(String file);

}
