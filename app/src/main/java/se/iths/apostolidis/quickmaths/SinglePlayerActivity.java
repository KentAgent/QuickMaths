package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

/**
 * Created by Apostolidis on 2017-11-14.
 */

public class SinglePlayerActivity extends AppCompatActivity{

    private Spinner spinnerNumberOfPlayers;
    private CheckBox cHumor;
    private CheckBox cSport;
    private CheckBox cFilm;
    private CheckBox cMusik;
    private CheckBox cHistoria;
    private CheckBox cGeografi;
    private CheckBox cRandom;
    private CheckBox cEsport;
    private CheckBox cMatte;
    private Button continueButton;
    private int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        spinnerNumberOfPlayers = findViewById(R.id.spinnerNumberOfPlayers);

        /**
         * Creates an Integer-array, and then fills the spinner with the the array with help of the adapter
         *
         */

        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        spinnerNumberOfPlayers.setAdapter(adapter);

        /**
         * Grabs value from of number of players from spinner and casts it to an int
         */
        numberOfPlayers = (int) spinnerNumberOfPlayers.getSelectedItem();


        cHumor = findViewById(R.id.checkBoxHumor);
        cSport = findViewById(R.id.checkBoxSport);
        cFilm = findViewById(R.id.checkBoxFilm);
        cMusik = findViewById(R.id.checkBoxMusik);
        cHistoria = findViewById(R.id.checkBoxHistoria);
        cGeografi = findViewById(R.id.checkBoxGeografi);
        cEsport = findViewById(R.id.checkBoxEsport);
        cRandom = findViewById(R.id.checkBoxRandom);
        cMatte = findViewById(R.id.checkBoxMatte);
        continueButton = findViewById(R.id.buttonOfflineModeContinue);

        //database = DBHelper.getInstance(this);

    }

    /**
     * Creates an intent when the continue-button is being clicked. Then sends a boolean value for every checkbox.
     * Also sends the value the spinner, which is number of players.
     */

    public void onClickOfflineModeContinue(){
        Intent intent = new Intent(this, OfflineModeSetupActivity.class);
        intent.putExtra("numberOfPlayers", numberOfPlayers);
        intent.putExtra("Humor", cHumor.isEnabled());
        intent.putExtra("Sport", cSport.isEnabled());
        intent.putExtra("Film", cFilm.isEnabled());
        intent.putExtra("Musik", cMusik.isEnabled());
        intent.putExtra("Historia", cHistoria.isEnabled());
        intent.putExtra("Geografi", cGeografi.isEnabled());
        intent.putExtra("Random", cGeografi.isEnabled());
        intent.putExtra("Matte", cMatte.isEnabled());
        startActivity(intent);
    }


}
