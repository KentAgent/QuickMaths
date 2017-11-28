package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import se.iths.apostolidis.quickmaths.service.network.GameCallback;
import se.iths.apostolidis.quickmaths.service.network.RemoteDataManager;

import java.io.Console;

public class GameActivity extends AppCompatActivity {
    PhotoView map;
    private RemoteDataManager remoteDataManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    private Point point = new Point();
    private static int gridSize = 35;
    private Point[] coordinatesX = new Point[gridSize];
    private Point[] coordinatesY = new Point[gridSize];
    private int maxX;
    private int maxY;
    // private View view = new View(this);
    //private Bitmap bitmap = Bitmap.createBitmap(maxX, maxY, Bitmap.Config.ARGB_8888);


    SurfaceView surfaceView;


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
        map.setScaleType(PhotoView.ScaleType.FIT_XY);
        map.setImageResource(R.mipmap.gamemap);
    }


    public void getCoordinateGrid (){
        Display display = getWindowManager().getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        Log.d("Wille", displaySize.toString());

        maxY = displaySize.y;
        maxX = displaySize.x;

        Log.e("Wille", displaySize.toString());


        final float spaceX = (float) (maxX * 0.05);
        final float spaceY = (float) (maxY * 0.05);

        double increment = 0;
        for (int i = 0; i < gridSize; i++) {
            Point point = new Point();
            point.set(0, (int) (increment += spaceY));
            coordinatesY[i] = point;
            Log.d("Wille", "Y: " + coordinatesY[i].toString());
        }
        increment = 0;
        for (int i = 0; i < gridSize; i++){
            Point point = new Point();
            point.set((int) (increment += spaceX), 0);
            coordinatesX[i] = point;
            Log.d("Wille", "X: " + coordinatesX[i].toString());
        }
        //map.setCoordinatesX(coordinatesX);
        //map.setCoordinatesY(coordinatesY);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        Log.d("hund", user.getUid());

        remoteDataManager = RemoteDataManager.getInstance();

        remoteDataManager.getGames(new GameCallback() {

            @Override
            public void didReceiveGames(String body) {
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    JSONArray games = jsonObject.getJSONArray("games");
                    for(int x = 0, xLen = games.length(); x < xLen; x++) {
                        JSONArray players = games.getJSONObject(x).getJSONArray("players");
                        if(games.getJSONObject(x).getBoolean("isActive")){
                            for(int y = 0, yLen = players.length(); y < yLen; y++) {
                                if(players.getJSONObject(y).getString("uid").equals(user.getUid())){
                                    if(players.getJSONObject(y).getBoolean("turnToPlay")){
                                       // GameEngine.rollDice();
                                        //GameEngine.answerQuestion();
                                        //GameEngine.endturn(x, y, user);



                                    }
                                }
                            }

                        }
                    }
                } catch (JSONException e) {
                    Log.e("hund", e.getMessage());
                    e.printStackTrace();
                }

            }

        });

    }

}
