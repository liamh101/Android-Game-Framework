package com.example.liam.androidframework.exampleGame;

import android.util.Log;


import com.example.liam.androidframework.framework.Screen;
import com.example.liam.androidframework.frameworkEngine.AndroidGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Liam on 21/06/2015.
 */
public class ExampleGame extends AndroidGame {

    private static String map;
    private boolean firstLoad;

    public ExampleGame(){
        firstLoad = true;
    }

    @Override
    public Screen getInitScreen() {

        if(firstLoad){
            Assets.loadTitle(this);
            firstLoad = false;
        }

        InputStream is = getResources().openRawResource(R.raw.test1);
        map = convertStreamToString(is);

        return new SplashLoadingScreen(this);
    }

    private static String convertStreamToString(InputStream is){

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        try{
            while ((line = reader.readLine()) != null){
                sb.append((line + "\n"));
            }
        } catch (IOException e){
            Log.w("LOG", e.getMessage());
        } finally {
            try{
                is.close();
            } catch (IOException e){
                Log.w("LOG", e.getMessage());
            }
        }
        return sb.toString();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public static String getMap() {
        return map;
    }
}
