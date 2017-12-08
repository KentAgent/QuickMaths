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

    CheckBox cEsport;
    CheckBox cMusik;
    CheckBox cSport;
    CheckBox cRandom;
    CheckBox cFilm;
    CheckBox cVetenskap;
    CheckBox cHumor;
    //CheckBox cEsport;
    //CheckBox cMath;

    boolean[] booleanArrayCategories;
    String[] stringArrayCategories;
    ArrayList<String> chosenCategories;

    private ArrayList<String> playerNames;

    Button buttonSelectAll;
    Button buttonSelectNone;

    ImageButton buttonGoBack;
    ImageButton buttonGoForward;
    private Bundle bundle;

    int setUpPlayers;
    int xTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        bundle = getIntent().getExtras();

        setUpPlayers = bundle.getInt("setUpPlayers");



        playerNames = bundle.getStringArrayList("playerNames");


        booleanArrayCategories = new boolean[9];
        stringArrayCategories = new String[9];


        buttonSelectAll = findViewById(R.id.buttonSelectAll);
        buttonSelectNone = findViewById(R.id.buttonSelectNone);
        buttonGoForward = findViewById(R.id.imageButtonForwardButton);
        buttonGoBack= findViewById(R.id.imageButtonGoBack);

        cEsport = findViewById(R.id.checkboxEsport);
        cMusik = findViewById(R.id.checkboxMusic);
        cSport = findViewById(R.id.checkboxSport);
        cRandom = findViewById(R.id.checkboxRandom);
        cFilm = findViewById(R.id.checkboxFilm);
        cVetenskap = findViewById(R.id.checkboxGeography);
        cHumor = findViewById(R.id.checkboxHumor);
        //cEsport = findViewById(R.id.checkboxFilm);
        //cMath = findViewById(R.id.checkboxHumor);
    }
    /**
     * Checks all category checkboxes
     *
     */
    public void onClickSelectAll(View view) {

        cEsport.setChecked(true);
        cMusik.setChecked(true);
        cSport.setChecked(true);
        cRandom.setChecked(true);
        cFilm.setChecked(true);
        cVetenskap.setChecked(true);
        cHumor.setChecked(true);
        //cRandom.setChecked(true);
        //cMath.setChecked(true);
    }
    /**
     *  Unchecks all category boxes
     *
     */
    public void onClickSelectNone(View view) {

        cEsport.setChecked(false);
        cMusik.setChecked(false);
        cSport.setChecked(false);
        cRandom.setChecked(false);
        cFilm.setChecked(false);
        cVetenskap.setChecked(false);
        cHumor.setChecked(false);
        //cRandom.setChecked(false);
        //cMath.setChecked(false);
    }


    /**
     * Sends number of categories, chosen categories,  and player names to GameActivity
     */
    public void onClickForward(View view) {

        booleanArrayCategories[0] = cEsport.isChecked();
        booleanArrayCategories[1] = cMusik.isChecked();
        booleanArrayCategories[2] = cSport.isChecked();
        booleanArrayCategories[3] = cRandom.isChecked();
        booleanArrayCategories[4] = cFilm.isChecked();
        booleanArrayCategories[5] = cVetenskap.isChecked();
        booleanArrayCategories[6] = cHumor.isChecked();
        //booleanArrayCategories[7] = cRandom.isChecked();
        //booleanArrayCategories[8] = cMath.isChecked();

        stringArrayCategories[0] = "E-sport";
        stringArrayCategories[1] = "Musik";
        stringArrayCategories[2] = "Sport";
        stringArrayCategories[3] = "Random";
        stringArrayCategories[4] = "Film";
        stringArrayCategories[5] = "Vetenskap";
        stringArrayCategories[6] = "Humor";
        //stringArrayCategories[7] = "Random";
        //stringArrayCategories[8] = "Math";


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
