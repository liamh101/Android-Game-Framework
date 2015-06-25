package com.example.liam.frameworkEngine;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.liam.framework.Graphics;
import com.example.liam.framework.Image;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Liam on 18/06/2015.
 *
 * Manage graphical elements for the game to be later rendered on the screen
 */
public class AndroidGraphics implements Graphics {

    private AssetManager assets;
    private Bitmap frameBuffer;
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;

    /** Create Asset Manager to manage all bitmap images assigned for the level and frameBuffer used to draw the master image.
     *
     * @param assets AssetManager to monitor all assets within the game
     * @param frameBuffer Bitmap image to hold all the images about to be drawn on screen
     */
    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer){
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();

    }

    /** Find image found on disk and assign it to memory
     *
     * @param fileName the name of image stored on disk
     * @param format format of the image on the disk and is kept once image is in memory
     * @return image that is now in memory
     */
    @Override
    public Image newImage(String fileName, ImageFormat format){
        Bitmap.Config config = null;
        if( format == ImageFormat.RGB565)
            config = Bitmap.Config.RGB_565; // least memory intensive holding only red,green and blue colour channels
        else if( format == ImageFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444; // holds Alpha channel along with red,green,blue colour channels (use for transparent objects)
        else
            config = Bitmap.Config.ARGB_8888; // Most memory intensive holding a higher quality Alpha channel infomation with red,green,blue colour channels

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inPreferredConfig = config;

        //Find file in memory
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

        //Give format information to the new Android image
        if(bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = ImageFormat.RGB565;
        else if(bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);

    }

    /**Remove all elements from the screen and render the screen a single colour.
     *
     * @param colour what colour to make the screen
     */
    @Override
    public void clearScreen(int colour){
        canvas.drawRGB((colour & 0xff0000),(colour & 0xff00),(colour & 0xff));
    }

    /**Render a single line on the screen with a single colour
     *
     * @param x start x position
     * @param y start y position
     * @param x2 stop x position
     * @param y2 stop y position
     * @param colour colour to render the line
     */
    @Override
    public void drawLine(int x, int y, int x2, int y2, int colour){
        paint.setColor(colour);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    /** Render a single rectangle on the screen with a single colour
     *
     * @param x star x position on the screen
     * @param y start y position on the screen
     * @param width width of the rectangle
     * @param height height of rectangle
     * @param colour render colour of rectangle
     */
    @Override
    public void drawRect(int x, int y, int width, int height, int colour){
        paint.setColor(colour);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, width, height, paint);
    }

    /**Draw the entire screen with a bitmap ARGB colour
     *
     * @param a alpha colour channel (0..255)
     * @param r amount of red colour channel (0..255)
     * @param g amount of green colour channel (0..255)
     * @param b amount of blue colour channel (0..255)
     */
    @Override
    public void drawARGB(int a, int r, int g,int b){
        paint.setStyle(Paint.Style.FILL);
        canvas.drawARGB(a, r, g, b);
    }

    /**render text onto the screen at a certain position
     *
     * @param text what to right to the screen
     * @param x start x position of the text
     * @param y start y position of the text
     * @param paint colour to render text
     */
    @Override
    public void drawString(String text, int x, int y, Paint paint){
        canvas.drawText(text, x, y, paint);
    }

    /** Render a bitmap image that is stored in memory onto the screen with the ability to scale it
     *
     * @param image image stored in memory to be rendered on screen
     * @param x start x position where image shall be rendered
     * @param y start y position where image shall be rendered
     * @param srcX the scaled x position where the image shall be rendered
     * @param srcY the scaled y position where the image shall be rendered
     * @param srcWidth scaled Width of the image
     * @param srcHeight scaled height of the image
     */
    @Override
    public void drawImage(Image image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight){
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;

        canvas.drawBitmap(((AndroidImage) image).getBitmap(), srcRect, dstRect,
                null);
    }

    /** Draw a bitmap image that is stored in memory at it's original size
     *
     * @param image image that will be rendered on screen
     * @param x x position where the image shall be rendered
     * @param y y position where the image shall be rendered
     */
    @Override
    public void drawImage(Image image, int x, int y){
        canvas.drawBitmap(((AndroidImage)image).getBitmap(), x, y, null);
    }

    /** Render a bitmap image stored in memory to be scaled and rendered on the screen
     *
     * @param image bitmap image to be rendered
     * @param x x position of original image
     * @param y y position of original image
     * @param width width of original image
     * @param height height of original image
     * @param srcX x postion of where to render too
     * @param srcY y postion of where to render too
     * @param srcWidth width to which to scale too
     * @param srcHeight height to which to scale too
     */
    public void drawScaledImage(Image image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.right = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;

        canvas.drawBitmap(((AndroidImage)image).getBitmap(), srcRect, dstRect, null);
    }

    /**get the width of canvas of the screen
     *
     * @return int of the width
     */
    @Override
    public int getWidth(){
        return frameBuffer.getWidth();
    }

    /**get the height of the canvas of the screen
     *
     * @return int of the height
     */
    @Override
    public int getHeight(){
        return frameBuffer.getHeight();
    }
}
