package com.example.liam.androidframework.frameworkEngine;

import android.media.SoundPool;

import com.example.liam.androidframework.framework.Sound;

/**
 * Created by Liam on 18/06/2015.
 *
 * Manage sound within the game
 */
public class AndroidSound implements Sound {

    private int soundID;
    private SoundPool soundPool;

    /** Assign a sound file a ID and a soundpool to store it in memory so it can be played in the application
     *
     * @param soundPool the manager that will store the sound file for the application
     * @param soundID the id number which will be given to the sound file to be easily accessed by the soundPool
     */
    public AndroidSound(SoundPool soundPool, int soundID){
        this.soundPool = soundPool;
        this.soundID = soundID;
    }


    /** Play an sound file stored within the soundpool at a certain volume
     *
     * @param volume a float value to play the sound in the application
     */
    @Override
    public void play(float volume) {
        soundPool.play(soundID, volume, volume,0, 0, 1);
    }

    /** Remove the sound file from the soundpool and free the memory making it unusable to the app until you reload the sound file back into the SoundPool .
     *
     */
    @Override
    public void dispose() {
        soundPool.unload(soundID);
    }

    //GETTER AND SETTER METHODS

    /**Returns the ID assigned to sound click when created
     *
     * @return int SoundID
     */
    public int getSoundID() {
        return soundID;
    }

    /**Set the ID of the sound clip, do not recommend doing this.
     *
     * @param soundID int of what you want the ID to be
     */
    public void setSoundID(int soundID) {
        this.soundID = soundID;
    }

    /** Returns the collection of sound clips that the current sound clip is stored
     *
     * @return SoundPool of sound clips
     */
    public SoundPool getSoundPool() {
        return soundPool;
    }

    /** Change where the current sound clip is stored.
     *
     * @param soundPool which SoundPool to put the sound clip into
     */
    public void setSoundPool(SoundPool soundPool) {
        this.soundPool = soundPool;
    }
}
