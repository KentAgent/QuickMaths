package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class SinglePlayerActivity extends AppCompatActivity {

    CheckBox cHumor;
    CheckBox cSport;
    CheckBox cFilm;
    CheckBox cMusic;
    CheckBox cHistory;
    CheckBox cGeography;
    CheckBox cRandom;
    CheckBox cEsport;
    CheckBox cMath;
    Button buttonSport;

    boolean[] booleanArrayCategories;
    String[] stringArrayCategories;
    ArrayList<String> chosenCategories;

    private String[] playerNames;

    Button buttonSelectAll;
    Button buttonSelectNone;

    ImageButton buttonGoBack;
    ImageButton buttonGoForward;
    private Bundle bundle;

    int setUpPlayers;
    int xTrue;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        bundle = getIntent().getExtras();

        setUpPlayers = bundle.getInt("setUpPlayers");

        //playerNames = new ArrayList<String>(setUpPlayers);

        playerNames = bundle.getStringArray("playerNames");


        booleanArrayCategories = new boolean[9];
        stringArrayCategories = new String[9];


        buttonSelectAll = findViewById(R.id.buttonSelectAll);
        buttonSelectNone = findViewById(R.id.buttonSelectNone);
        buttonGoForward = findViewById(R.id.imageButtonForwardButton);
        buttonGoBack= findViewById(R.id.imageButtonGoBack);

        cHumor = findViewById(R.id.checkboxHumor);
        cSport = findViewById(R.id.checkboxSport);
        cFilm = findViewById(R.id.checkboxFilm);
        cMusic = findViewById(R.id.checkboxMusic);
        cHistory = findViewById(R.id.checkboxHistory);
        cGeography = findViewById(R.id.checkboxGeography);
        cRandom = findViewById(R.id.checkboxRandom);
        cEsport = findViewById(R.id.checkboxEsport);
        cMath = findViewById(R.id.checkboxMath);

        //buttonSport.setBackgroundResource(R.drawable.circle_button_green);


    }

/*

                            _
                           < >
                         < _ >
                      __<_ - _>___
                        (.)(.)
                         {--}
                   //----\ /----\\
                        // }}    \\
                       === ===    \\
                                   \\
                                 \\\\\\
                                    D
                                      A
                                        M
                                         M

*/

    /**
     * Checks all category checkboxes
     *
     */
    public void onClickSelectAll(View view) {

        cHumor.setChecked(true);
        cSport.setChecked(true);
        cFilm.setChecked(true);
        cMusic.setChecked(true);
        cHistory.setChecked(true);
        cGeography.setChecked(true);
        cEsport.setChecked(true);
        cRandom.setChecked(true);
        cMath.setChecked(true);


    }
    /**
     *  Unchecks all category boxes
     *
     */
    public void onClickSelectNone(View view) {

        cHumor.setChecked(false);
        cSport.setChecked(false);
        cFilm.setChecked(false);
        cMusic.setChecked(false);
        cHistory.setChecked(false);
        cGeography.setChecked(false);
        cEsport.setChecked(false);
        cRandom.setChecked(false);
        cMath.setChecked(false);
    }


    /**
     * When clicking on proceed button:
     * value of number of categories choosen, and string[] array with categories choosen,
     * and value of number of players from scrollable number picker
     * is sent with intent to OfflineModeSetupActivity
     */
    public void onClickForward(View view) {

        /*
        snp.setListener(new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {
                numberOfPlayers = value;
            }
        });
*/
        booleanArrayCategories[0] = cHumor.isChecked();
        booleanArrayCategories[1] = cSport.isChecked();
        booleanArrayCategories[2] = cFilm.isChecked();
        booleanArrayCategories[3] = cMusic.isChecked();
        booleanArrayCategories[4] = cHistory.isChecked();
        booleanArrayCategories[5] = cGeography.isChecked();
        booleanArrayCategories[6] = cEsport.isChecked();
        booleanArrayCategories[7] = cRandom.isChecked();
        booleanArrayCategories[8] = cMath.isChecked();



        stringArrayCategories[0] = "Humor";
        stringArrayCategories[1] = "Sport";
        stringArrayCategories[2] = "Film";
        stringArrayCategories[3] = "Musik";
        stringArrayCategories[4] = "History";
        stringArrayCategories[5] = "Geography";
        stringArrayCategories[6] = "E-Sport";
        stringArrayCategories[7] = "Random";
        stringArrayCategories[8] = "Math";


        xTrue = 0;

        for (int i = 0; i < booleanArrayCategories.length; i++) {
            if (booleanArrayCategories[i]) {
                xTrue++;
            }
        }

        chosenCategories = new ArrayList<String>(xTrue);


        for(int i = 0; i < booleanArrayCategories.length; i++){
            if(booleanArrayCategories[i]){
                chosenCategories.add(stringArrayCategories[i]);
            }

        }


        if (xTrue > 0) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("playerNames", playerNames);
            intent.putExtra("setUpPlayers", setUpPlayers);
            Log.d("SING", "onClickForward: " + setUpPlayers);
            intent.putExtra("xTrue", xTrue);
            intent.putExtra("chosenCategories", chosenCategories);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Choose at least one category", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickGoBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
