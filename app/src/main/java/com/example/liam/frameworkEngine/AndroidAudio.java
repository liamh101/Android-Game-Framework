package com.example.liam.frameworkEngine;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.liam.framework.Audio;
import com.example.liam.framework.Music;
import com.example.liam.framework.Sound;

import java.io.IOException;

/**
 * Created by Liam on 18/06/2015.
 *
 * Creates audio variables based on either being game sounds or music for a video game.
 */
public class AndroidAudio implements Audio {
    private AssetManager assets;
    private SoundPool soundPool;

    /** Creates a audio manager that sets the master volume control based on the phone
     *  and SoundPool that is able to play 20 simultaneous sounds
     *
     * @param activity the applications activity
     */
    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    /**Finds a file and places it memory.
     *
     * @param file file to place into memory
     * @return return the AndroidMusic type
     */
    @Override
    public Music createMusic(String file) {
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(file);
            return new AndroidMusic(assetDescriptor);
        } catch (IOException e){
            throw new RuntimeException("Couldn't load music '" + file + "'");
        }


    }

    /**Loads file within application and adds it in memory within the soundPool for easy access by the application
     *
     * @param file what the sound file you want to use is called
     * @return returns the new sound file with a id and it's associated soundpool;
     */
    @Override
    public Sound createSound(String file) {
        try{
            AssetFileDescriptor assetDescriptor = assets.openFd(file);
            int soundID = soundPool.load(assetDescriptor, 0);
            return new AndroidSound(soundPool, soundID);
        }catch (IOException e){
            throw new RuntimeException("Couldn't load sound '" + file + "'");
        }
    }
}
