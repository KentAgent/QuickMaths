package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by mrx on 2017-11-30.
 */

public class GameViewLayout extends SurfaceView implements Runnable {

    Thread thread = null;
    Boolean canDraw = false;
    Paint redPaintFill, bluePaintFill, greenPaintFill;
    Paint redPaintStroke, bluePaintStroke, greenPaintStroke;
    Path square = new Path();
    Path d_square = new Path();

    Bitmap backGround;
    Bitmap player;
    Bitmap unscaledPlayer;

    float screenX, screenY;
    int playerX, playerY;
    int playerX_d, playerY_d;

    DisplayMetrics displayMetrics = this.getContext().getResources().getDisplayMetrics();
    Matrix m;
    Canvas canvas;
    SurfaceHolder surfaceHolder;

    float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
    float dpWidth = displayMetrics.widthPixels / displayMetrics.density;


    public GameViewLayout(Context context) {
        super(context);
        surfaceHolder = getHolder();



        //matrix
        m = new Matrix();
        screenX = 1;
        screenY = 1;

        //m.setScale(1, 1, toPxs(1000), toPxs(1600));
        m.setScale(1, 1, toPxs((int) dpWidth), toPxs((int) dpHeight));
        backGround = BitmapFactory.decodeResource(getResources(), R.mipmap.gamemap);

        unscaledPlayer = BitmapFactory.decodeResource(getResources(), R.drawable.face1);
        player = Bitmap.createScaledBitmap(unscaledPlayer, 200, 200, false);
        playerX = 650;
        playerY = 130;

    }

    @Override
    public void run() {

        importPaintBrushes();

        while (canDraw){

            if (!surfaceHolder.getSurface().isValid()){
                continue;
            }

            Log.d("Wille", "Px: " + String.valueOf(playerX));
            Log.d("Wille", "Dps: " + String.valueOf(toPxs(playerX)));
            canvas = surfaceHolder.lockCanvas();
            drawPlayer(10);
            canvas.drawBitmap(backGround, m, null );
            //canvas.drawBitmap(backGround, 0, 0, null);
            drawSquare(130, 130, 650, 650);

            canvas.drawBitmap(player, playerX - (player.getWidth()/2), playerY - (player.getHeight()/2), null);
            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    private void drawSquare(int x1, int y1, int x2, int y2) {
        square.moveTo(x1, y1);
        square.lineTo(x2, y1);

        square.moveTo(x2, y1);
        square.lineTo(x2, y2);

        square.moveTo(x2, y2);
        square.lineTo(x1, y2);

        square.moveTo(x1, y2);
        square.lineTo(x1, y1);

        this.canvas.drawPath(square, greenPaintStroke);
    }
    private void drawPlayer(int speed){

        if ((playerY == 130) && (playerX < 650)){
            playerX = playerX + speed;
        }
        if ((playerY < 650) && (playerX == 650)){
            playerY = playerY + speed;
        }
        if ((playerY == 650) && (playerX > 130)){
            playerX = playerX - speed;
        }
        if ((playerY > 130) && (playerX == 130)){
            playerY = playerY - speed;
        }
    }
    public void pause(){

        canDraw = false;
        while(true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }
    public void resume(){
        canDraw = true;
        thread = new Thread(this);
        thread.start();
    }
    private void importPaintBrushes (){
        redPaintFill = new Paint();
        redPaintFill.setColor(Color.RED);
        redPaintFill.setStyle(Paint.Style.FILL);

        bluePaintFill = new Paint();
        bluePaintFill.setColor(Color.BLUE);
        bluePaintFill.setStyle(Paint.Style.FILL);

        greenPaintFill = new Paint();
        greenPaintFill.setColor(Color.GREEN);
        greenPaintFill.setStyle(Paint.Style.FILL);

        redPaintStroke = new Paint();
        redPaintStroke.setColor(Color.RED);
        redPaintStroke.setStyle(Paint.Style.STROKE);
        redPaintStroke.setStrokeWidth(10);

        bluePaintStroke = new Paint();
        bluePaintStroke.setColor(Color.RED);
        bluePaintStroke.setStyle(Paint.Style.STROKE);
        bluePaintStroke.setStrokeWidth(10);

        greenPaintStroke = new Paint();
        greenPaintStroke.setColor(Color.GREEN);
        greenPaintStroke.setStyle(Paint.Style.STROKE);
        greenPaintStroke.setStrokeWidth(10);

    }
    private int toPxs (int dps){
        return (int) (dps * getResources().getDisplayMetrics().density);
    }

    public static int getScreenWidth() {     return Resources.getSystem().getDisplayMetrics().widthPixels; }
    public static int getScreenHeight() {     return Resources.getSystem().getDisplayMetrics().heightPixels; }

}
