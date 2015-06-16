package com.example.liam.androidframework;

/**
 * Created by Liam on 17/06/2015.
 */
public interface Music {

    //Play music file from last place is was playing
    public void play();

    //Stop music file
    public void stop();

    //Continue playing music file from last place it was stopped
    public void pause();

    //Will the music loop once it's ended?
    public void setLooping(boolean looping);

    //What's the volume of the music?
    public void setVolume(float volume);

    //Is the music playing?
    public boolean isPlaying();

    //Has the music stopped?
    public boolean isStopped();

    //Is the music going to loop?
    public boolean isLooping();

    //Remove music file
    public void dispose();

    void seekBegin();


}
