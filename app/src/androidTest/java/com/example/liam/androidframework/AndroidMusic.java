package com.example.liam.androidframework;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.example.liam.androidframework.framework.Music;

import java.io.IOException;

/**
 * Created by Liam on 18/06/2015.
 *
 * Manage music within the game
 */
public class AndroidMusic implements Music, MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener{
    private MediaPlayer mediaPlayer;
    private boolean isPrepared;

    /**Creates a MediaPlayer variable for higher quality audio stream and more control over the games music.
     *
     * @param assetDescriptor audio file you wish to use as music
     */
    public AndroidMusic(AssetFileDescriptor assetDescriptor){
        isPrepared = false;
        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(), assetDescriptor.getStartOffset(), assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared = true;
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
        }catch (Exception e){
            throw new RuntimeException("Couldn't load music");
        }
    }

    /**Play the music file
     *
     */
    @Override
    public void play() {
        if(this.mediaPlayer.isPlaying())
            return;

        try {
            synchronized (this){
                if (!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**Stop music file
     *
     */
    @Override
    public void stop() {
        if(this.mediaPlayer.isPlaying() == true){
            this.mediaPlayer.stop();

            synchronized (this){
                isPrepared = false;
            }
        }
    }

    /**Pause music file
     *
     */
    @Override
    public void pause() {
        if(this.mediaPlayer.isPlaying() == true)
            mediaPlayer.pause();
    }

    /**Set whether the music loops once the music file reaches the end
     *
     * @param looping boolean variable, true loops music, false doesn't
     */
    @Override
    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);
    }

    /**Set the volume of the music playing
     *
     * @param volume float value to set the volume of the music
     */
    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    /**is the music currently playing
     *
     * @return boolean variable, true music is playing, false music is not playing
     */
    @Override
    public boolean isPlaying() {
        return this.mediaPlayer.isPlaying();
    }

    /**Has the music stop playing
     *
     * @return boolean variable, true music has stopped, false music hasn't stopped yet
     */
    @Override
    public boolean isStopped() {
        return !isPrepared;
    }


    /*Is the music going to loop once to the music reaches the end
     *
     * @return boolean variable, true it is going to loop, false it's not
     */
    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    /**Dispose the music track from memory
     *
     */
    @Override
    public void dispose() {
        if(this.mediaPlayer.isPlaying()){
            this.mediaPlayer.stop();
        }
        this.mediaPlayer.release();
    }

    /**Start music from the beginning
     *
     */
    @Override
    public void seekBegin() {
        mediaPlayer.seekTo(0);
    }

    /**Once music has finished playing and reached the end of the music track the music will stop
     *
     * @param player which player to stop
     */
    public void onCompletion(MediaPlayer player){
        synchronized (this){
            isPrepared = false;
        }
    }

    /**When the music is ready to be played
     *
     * @param mp the MediaPlayer that is going to be used to play the music
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        synchronized (this){
            isPrepared = true;
        }
    }

    //UNUSED METHODS
    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

    }
}
