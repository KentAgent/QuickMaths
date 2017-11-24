package se.iths.apostolidis.quickmaths;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;

import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by mrx on 2017-11-24.
 */

public class MPhotoView extends PhotoView {
    int gridSize = 35;
    private Point[] coordinatesY = new Point[gridSize];
    private Point[] coordinatesX = new Point[gridSize];
    private int maxX;
    private int maxY;
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.eyes);
    Paint paint = new Paint(Color.BLACK);


    public MPhotoView(Context context) {
        super(context);
    }

    public MPhotoView (Context context, int maxX, int maxY){
        super(context);
        //this.coordinatesX = coordinatesX;
        //this.coordinatesY = coordinatesY;
        this.maxX = maxX;
        this.maxY = maxY;

    }
    public void setCoordinatesY (Point[] posY){
        coordinatesY = posY;
    }
    public void setCoordinatesX (Point[] posX){
        coordinatesX = posX;
    }

    public MPhotoView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public MPhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    public MPhotoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("Wille", String.valueOf(canvas.getWidth()));
        Log.d("Wille", String.valueOf(canvas.getHeight()));

        canvas.save();
        super.onDraw(canvas);
        canvas.restore();

        drawPoint(canvas);

        //gridCoordinates(canvas);
        canvas.setMatrix(this.getAttacher().getImageMatrix());
        bror(canvas);

    }

    public void drawPoint (Canvas canvas){

        canvas.save();
        canvas.setMatrix(this.getAttacher().getImageMatrix());
        Bitmap b = bitmap.createScaledBitmap(bitmap, 70, 70, false);
        canvas.drawBitmap(b, 1200, 400, null);
        canvas.restore();
    }

    public void bror(Canvas canvas){
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
            canvas.save();
            canvas.drawText(String.valueOf(j), 0, 50 + incrementY, paint);
            canvas.restore();
            incrementY += spaceY;

        }
            incrementX = 0;
            for (int i = 0; i < gridSize; i++) {
                canvas.save();
                canvas.drawText(String.valueOf(i), incrementX, 50, paint);
                canvas.restore();
                incrementX += spaceX;
                index += 1;

            }



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
            canvas.save();
            //canvas.drawRect(new Rect(coordinatesX[i].x, (coordinatesX[i].x + 20), 50, 100), new Paint(Color.BLACK));
            canvas.drawCircle(0, incrementY, 10, new Paint(Color.BLACK));
            canvas.restore();
            incrementY += spaceY;
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
