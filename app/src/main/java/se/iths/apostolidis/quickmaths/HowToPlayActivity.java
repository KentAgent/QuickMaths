package se.iths.apostolidis.quickmaths;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class HowToPlayActivity extends AppCompatActivity {

    private TextView singleplayer;
    private TextView multiplayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        singleplayer = findViewById(R.id.textViewHowToSingle);
        multiplayer = findViewById(R.id.textViewHowToMulti);

        singleplayer.setText("\nRoll the die and get moved the amount of dots\n\n" +
                "Answer the question and if you get it wrong you get moved back to your original position " +
                "\n\n After each succesfull answer you get gold equal to your current placement on the map" +
                "\n\nWin the game by reaching the finishline\n");

        multiplayer.setText("\nCreate your avatar" + "\n\nCreate or join a lobby" +
                "\n\nRoll the die and get moved the amount of dots\n\n" +
                "Answer the question and if you get it wrong you get moved back to your original position " +
                "\n\n After each succesfull answer you get gold equal to your current placement on the map" +
                "\n\n Win the game by reaching the finishline and your personal score gets updated\n");
    }

    public void onClickSingle (View view) {
        if (multiplayer.getVisibility() == View.VISIBLE) {
            multiplayer.setVisibility(View.INVISIBLE);
            singleplayer.setVisibility(View.VISIBLE);
        }
    }

    public void onClickMulti (View view) {
        if (singleplayer.getVisibility() == View.VISIBLE){
            singleplayer.setVisibility(View.INVISIBLE);
            multiplayer.setVisibility(View.VISIBLE);
        }
    }

    public void onClickBack(View view) {finish();}
}
