package se.iths.apostolidis.quickmaths;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;

public class GameActivity extends AppCompatActivity {

   PhotoView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        map = findViewById(R.id.photo_view);
        map.setImageResource(R.mipmap.gamemap);

        

    }
}
