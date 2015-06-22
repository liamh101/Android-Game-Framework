package com.example.liam.androidframework.framework;

import android.content.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * All modthods needed within for a FileIO class within an Android App.
 *
 * Created by Liam on 16/06/2015.
 */
public interface FileIO {

    //Read items from a file on disk
    public InputStream readFile(String file) throws IOException;

    //Write items to file on disk
    public OutputStream writeFile(String file) throws IOException;

    //Read assets (e.g. text) from within a file
    public InputStream readAsset(String file) throws IOException;

    //Access Users sharedPreference data on phone
    public SharedPreferences getSharePref();

}
