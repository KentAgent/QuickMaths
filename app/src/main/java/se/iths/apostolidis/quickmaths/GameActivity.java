package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;

public class GameActivity extends AppCompatActivity {

    PhotoView map;
    private Context context;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //map = findViewById(R.mipmap.gamemap);


    }
}
