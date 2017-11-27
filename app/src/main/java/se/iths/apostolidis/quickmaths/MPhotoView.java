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
import android.widget.ImageView;

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
    //private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face1);
    private Bitmap startSquare = BitmapFactory.decodeResource(getResources(), R.drawable.arrowright);
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

        //checkPointDrawables(canvas);

        drawPoint(canvas);
        //bror(canvas);

        //canvas.setMatrix(this.getAttacher().getImageMatrix());
        //gridCoordinates(canvas);
    }

    public void setAssetPosList (Point[] posList){
        posList[0] = (new Point(170, 1530));
        posList[1] = (new Point(310, 1530));
        posList[2] = (new Point(330, 1430));
        posList[3] = (new Point(360, 1340));
        posList[4] = (new Point(430, 1240));
        posList[5] = (new Point(500, 1170));
        posList[6] = (new Point(580, 1070));
        posList[7] = (new Point(650, 940));
        posList[8] = (new Point(680, 840));
        posList[9] = (new Point(700, 740));
        posList[10] = (new Point(710, 640));
        posList[11] = (new Point(800, 540));

        /**
         * Vägdelning Rutt A
         */
        posList[12] = (new Point(880, 450));
        posList[13] = (new Point(880, 350));
        posList[14] = (new Point(900, 250));
        posList[15] = (new Point(970, 170));
        posList[16] = (new Point(1100, 150));
        posList[17] = (new Point(1220, 170));
        posList[18] = (new Point(1320, 250));
        posList[19] = (new Point(1440, 330));
        posList[20] = (new Point(1520, 430));
        posList[21] = (new Point(1520, 530));
        posList[22] = (new Point(1500, 610));



        /**
         * Vägdelning Rutt B
         */


        /**
         * Sammankoppling
         */
        posList[23] = (new Point(1600, 710));
        posList[24] = (new Point(1585, 810));
        posList[25] = (new Point(1550, 1300));
        posList[26] = (new Point(1550, 1400));

        /**
         * Alt rutt A
         */
        posList[27] = (new Point(1460, 1500));

        /**
         * Alt Rutt B
         */

        /**
         * Sammankoppling
         */
        posList[28] = (new Point(1330, 1550));
        posList[29] = (new Point(1200, 1570));
        posList[30] = (new Point(1100, 1620));
        posList[31] = (new Point(1000, 1690));
        posList[32] = (new Point(860, 1670));
        posList[33] = (new Point(710, 1690));
        posList[34] = (new Point(600, 1730));
        posList[35] = (new Point(450, 1700));
        posList[36] = (new Point(320, 1620));

    }
    public void checkPointDrawables (Canvas canvas){
        Bitmap coordinate = BitmapFactory.decodeResource(getResources(), R.drawable.arrowright);
        canvas.save();
        canvas.setMatrix(this.getAttacher().getImageMatrix());
        Bitmap b = coordinate.createScaledBitmap(coordinate, 100, 100, false);
        canvas.drawBitmap(b, 450, 1700, null);
        canvas.restore();

        Bitmap coordinate2 = BitmapFactory.decodeResource(getResources(), R.drawable.arrowright);
        canvas.save();
        canvas.setMatrix(this.getAttacher().getImageMatrix());
        Bitmap b2 = coordinate.createScaledBitmap(coordinate2, 100, 100, false);
        canvas.drawBitmap(b2, 320, 1620, null);
        canvas.restore();
    }
    public void drawPoint (Canvas canvas){
//        paint.setStrokeWidth(30);
//        canvas.save();
//        canvas.drawLine(0,0, 200, 200, paint);
//        canvas.restore();

        Point[] assetPosList = new Point[37];
        setAssetPosList(assetPosList);

        for (int i = 0; i < assetPosList.length; i++) {

            canvas.save();
            canvas.setMatrix(this.getAttacher().getImageMatrix());
            //canvas.drawRect(new Rect(coordinatesX[i].x, (coordinatesX[i].x + 20), 50, 100), new Paint(Color.BLACK));
            canvas.drawCircle(assetPosList[i].x + 40, assetPosList[i].y + 60, 20,  new Paint(Color.BLACK));
            canvas.restore();

            int x = canvas.getWidth();
            Log.d("Wille", "Canvas Width: " + x);

            /**
             * Image loop
             */
//            Bitmap coordinate = BitmapFactory.decodeResource(getResources(), R.drawable.arrowright);
//            canvas.save();
//            canvas.setMatrix(this.getAttacher().getImageMatrix());
//            Bitmap b = coordinate.createScaledBitmap(coordinate, 100, 100, false);
//            canvas.drawBitmap(b, assetPosList[i].x, assetPosList[i].y, null);
//            canvas.restore();
//
//            Log.d("Wille", "Xpos: " + assetPosList[i].x);
//            Log.d("Wille", "Ypos: " + assetPosList[i].y);
        }

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
