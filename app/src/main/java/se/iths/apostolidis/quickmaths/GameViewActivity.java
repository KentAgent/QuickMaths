package se.iths.apostolidis.quickmaths;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameViewActivity extends AppCompatActivity {


    GameViewLayout gameViewLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameViewLayout = new GameViewLayout(this);
        setContentView(gameViewLayout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameViewLayout.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameViewLayout.resume();
    }
}
