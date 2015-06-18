package com.example.liam.androidframework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.liam.androidframework.framework.Graphics;
import com.example.liam.androidframework.framework.Image;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Liam on 18/06/2015.
 */
public class AndroidGraphics implements Graphics {

    private AssetManager assets;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer){
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();

    }

    @Override
    public Image newImage(String fileName, ImageFormat format){
        Bitmap.Config config = null;
        if( format == ImageFormat.RGB565)
            config = Bitmap.Config.RGB_565;
        else if( format == ImageFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try{
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in,null, options);

            if(bitmap == null){
                throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
            }

        } catch (IOException e){
           throw new RuntimeException("Coudn't find bitmap '" + fileName + "' on disk");
        } finally {
            if( in != null){
                try{
                    in.close();
                }catch(IOException e){
                    throw new RuntimeException("Can't close input stream");
                }
            }
        }

        if(bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = ImageFormat.RGB565;
        else if(bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);

    }
}
