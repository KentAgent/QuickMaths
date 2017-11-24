package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;

/**
 * Created by Apostolidis on 2017-11-14.
 */

public class SinglePlayerActivity extends AppCompatActivity{

    CheckBox cHumor;
    CheckBox cSport;
    CheckBox cFilm;
    CheckBox cMusic;
    CheckBox cHistory;
    CheckBox cGeography;
    CheckBox cRandom;
    CheckBox cEsport;
    CheckBox cMath;
    Button continueButton;
    ScrollableNumberPicker snp;
    int numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        cHumor = findViewById(R.id.checkBoxHumor);
        cSport = findViewById(R.id.checkBoxSport);
        cFilm = findViewById(R.id.checkBoxFilm);
        cMusic = findViewById(R.id.checkBoxMusic);
        cHistory = findViewById(R.id.checkBoxHistory);
        cGeography = findViewById(R.id.checkBoxGeography);
        cEsport = findViewById(R.id.checkBoxEsport);
        cRandom = findViewById(R.id.checkBoxRandom);
        cMath = findViewById(R.id.checkBoxMath);

        continueButton = findViewById(R.id.buttonOfflineModeContinue);
        snp = findViewById(R.id.scrollableNumberPickerNumberOfPlayers);

    }
    /**
     * When clicking on continue button:
     * creates intent and puts an array with boolean values of checkbox checked
     * and value of number of players from scrollable number picker
     * goes to OfflineModeSetupActivity where players chooses names and avatars
     */
    public void onClickContinueButton(View view){

        boolean[] booleanArrayCategories = new boolean[9];
        booleanArrayCategories[0] = cHumor.isChecked();
        booleanArrayCategories[1] = cSport.isChecked();
        booleanArrayCategories[2] = cFilm.isChecked();
        booleanArrayCategories[3] = cMusic.isChecked();
        booleanArrayCategories[4] = cHistory.isChecked();
        booleanArrayCategories[5] = cGeography.isChecked();
        booleanArrayCategories[6] = cEsport.isChecked();
        booleanArrayCategories[7] = cRandom.isChecked();
        booleanArrayCategories[8] = cMath.isChecked();

        numberOfPlayers = snp.getValue();

        Intent intent = new Intent(this, OfflineModeSetupActivity.class);
        intent.putExtra("booleanArrayCategories", booleanArrayCategories);
        intent.putExtra("numberOfPlayers", numberOfPlayers);
        startActivity(intent);
    }

    public void onClickSelectAllCategoriesButton(View view) {

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

    //database = DBHelper.getInstance(this)
}
