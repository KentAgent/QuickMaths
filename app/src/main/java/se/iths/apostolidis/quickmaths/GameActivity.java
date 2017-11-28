package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.Console;

public class
GameActivity extends AppCompatActivity {

    MPhotoView map;
    private static int gridSize = 37;
    private int maxX;
    private int maxY;
    // private View view = new View(this);
    //private Bitmap bitmap = Bitmap.createBitmap(maxX, maxY, Bitmap.Config.ARGB_8888);


    SurfaceView surfaceView;

    private Point[] assetCoordinates = new Point[gridSize];
    private Canvas canvas;
    private Drawable d;
    private SurfaceHolder surfaceHolder;
    private Context context;
    private Paint paint;
    private GameEngine engine;
    private ImageView drawView;
    private Bitmap bitmap;

    //private CoordinateGrid grid = new CoordinateGrid(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        map = findViewById(R.id.photo_view);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face1);
        //map.setImageResource(R.mipmap.gamemap);
        //map.setImageBitmap(setMap());


        assetCoordinates = new Point[gridSize];
        setAssetPosList(assetCoordinates);

        map.setImageBitmap(setMap(assetCoordinates));
    }

    public Bitmap setMap (Point[] assetCoordinates){


        Bitmap backgroundImage = BitmapFactory.decodeResource(getResources(), R.mipmap.gamemap);
        Bitmap mergedCoodinateAssets = backgroundImage;

        Bitmap previousImage;
        Bitmap scaledCoordinateAsset = backgroundImage;
        for (int i = 0; i < assetCoordinates.length; i++){

            if (i == 0) {
                previousImage = backgroundImage;
            } else {
                previousImage = mergedCoodinateAssets;
            }
            Bitmap coordinateAsset = BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_maths);
            scaledCoordinateAsset = Bitmap.createScaledBitmap(coordinateAsset, 50, 50, false);
            Log.d("Wille", "X value: " + assetCoordinates[i].x);

            mergedCoodinateAssets = createSingleImageFromMultipleImages(previousImage, scaledCoordinateAsset, 0, 0, assetCoordinates[i].x + 20, assetCoordinates[i].y + 20);
        }


        Log.d("Wille", "Bigmerge: " + mergedCoodinateAssets.toString());
        return mergedCoodinateAssets;

    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage, int firstImageX, int firstImageY, int secondImageX, int secondImageY){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, firstImageX, firstImageY, null);
        canvas.drawBitmap(secondImage, secondImageX, secondImageY, null);
        return result;
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

}
