package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by mrx on 2017-11-15.
 */

public class GameEngine extends SurfaceView implements Runnable {
    private Context context;

    private int maxX;
    private int maxY;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;
    private Drawable d;

    public GameEngine(Context context, int maxX, int maxY) {
        super(context);
        this.context = context;
        this.maxX = maxX;
        this.maxY = maxY;
        surfaceHolder = getHolder();

    }

    @Override
    public void run() {
        draw();

    }
    public void onDraw(Canvas canvas){

    }

    public void draw() {
    }
}
