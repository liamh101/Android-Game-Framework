package com.example.liam.frameworkEngine;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.example.liam.framework.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Liam on 18/06/2015.
 *
 * Manages Applications File system for grabbing elements to store into memory
 */
public class AndroidFileIO implements FileIO {

    private Context context;
    private AssetManager assets;
    private String externalStoragePath;

    /**Create a file directory system to search for assets in the applications file system
     *
     * @param context current directory in the application (e.g. where images are stored
     */
    public AndroidFileIO(Context context){
        this.context = context;
        assets = context.getAssets();
        externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /** Get a file to be read by the application
     *
     * @param file the name of the file within the directory you're looking for
     * @return contents of file to be interpreted
     * @throws IOException if file can't be found throw IOException
     */
    @Override
    public InputStream readFile(String file) throws IOException {
        return new FileInputStream(externalStoragePath + file);
    }

    /** prepare to write infomation to a file within the applications directory
     *
     * @param file name of file to write too
     * @return file that will be written to
     * @throws IOException throws exeption if file cannot be found within directory
     */
    @Override
    public OutputStream writeFile(String file) throws IOException {
        return new FileOutputStream(externalStoragePath + file);
    }

    /** read an asset within the file manage
     *
     * @param file file that is currently stored in memory
     * @return asset that is in the asset manager
     * @throws IOException if asset can't be found
     */
    @Override
    public InputStream readAsset(String file) throws IOException {
        return assets.open(file);
    }

    /** Get the shared preferences found within the application
     *
     * @return the shared preferences found within the PreferenceManager
     */
    @Override
    public SharedPreferences getSharePref() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
