package se.iths.apostolidis.quickmaths;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private int score;
    private ArrayList<String> listOfPlayersScore = new ArrayList<>();
    private ArrayList<Integer> scoreList = new ArrayList<>();
    private Bundle bundle;
    private TextView scoreboard;
    private String scoreboardText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreboard = findViewById(R.id.textViewScoreboardResult);

        bundle = getIntent().getExtras();
        listOfPlayersScore = bundle.getStringArrayList("listOfPlayers");
        Log.d("Grekolas", "listOfPlayers: " + listOfPlayersScore.get(0));
        scoreList = bundle.getIntegerArrayList("scoreList");
        Log.d("Grekolas", "scoreList: " + scoreList.get(0));

        scoreboard.setText(updateScoreboard());

    }

    private String updateScoreboard() {

        for (int i = 0; i < listOfPlayersScore.size(); i++) {
            scoreboardText += listOfPlayersScore.get(i) + " " + scoreList.get(i);
            if (i != (listOfPlayersScore.size() - 1))
                scoreboardText += "\n";
        }
        return scoreboardText;
    }

}
