package se.iths.apostolidis.quickmaths;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by mrx on 2017-11-24.
 */

public class MPhotoView extends PhotoView {
    private Bitmap backgroundImage;
    private LayerDrawable coordinateAssets;
    int gridSize = 35;
    private Point[] coordinatesY = new Point[gridSize];
    private Point[] coordinatesX = new Point[gridSize];
    private int maxX;
    private int maxY;
    //private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face1);
    private Bitmap startSquare = BitmapFactory.decodeResource(getResources(), R.drawable.arrowright);
    Paint paint = new Paint(Color.BLACK);


    public MPhotoView(Context context) {
        super(context);
    }


    public MPhotoView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public MPhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("Wille", "Canvas width: " + String.valueOf(canvas.getWidth()));
        Log.d("Wille", "Canvas height: " + String.valueOf(canvas.getHeight()));

        canvas.save();
        super.onDraw(canvas);
        canvas.restore();

    }


    public void gridCoordinates(Canvas canvas){
        int incrementY;
        int incrementX;

        int X = canvas.getWidth() + 700;
        int Y = canvas.getHeight();
        int spaceX = X / gridSize;
        int spaceY = Y / gridSize;
        int index = 0;
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(40);


        incrementY = 0;
        for (int j = 0; j < gridSize; j++) {
//            canvas.save();
//            //canvas.drawRect(new Rect(coordinatesX[i].x, (coordinatesX[i].x + 20), 50, 100), new Paint(Color.BLACK));
//            canvas.drawCircle(50,  incrementY, 10, new Paint(Color.BLACK));
//            canvas.restore();
//            incrementY += spaceY;
            incrementX = 0;
            for (int i = 0; i < coordinatesX.length; i++) {
                canvas.save();
                //canvas.drawRect(new Rect(coordinatesX[i].x, (coordinatesX[i].x + 20), 50, 100), new Paint(Color.BLACK));
                canvas.drawCircle(spaceX + incrementX, incrementY, 10, new Paint(Color.BLACK));
                canvas.restore();
                incrementX += spaceX;
            }
        }
    }
}
