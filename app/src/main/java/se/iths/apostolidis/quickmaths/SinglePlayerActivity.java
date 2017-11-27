package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;

import se.iths.apostolidis.quickmaths.service.database.DBHelper;

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
    int numberOfPlayers = 0;
    boolean[] booleanArrayCategories = new boolean[9];
    int count = 0;
    DBHelper database;

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

        database = DBHelper.getInstance(this);
    }

    /**
     * shows an error message if no categories are checked
     * @param message
     */
    public void ErrorMessageBoxNoCategories(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    /**
     * When clicking on continue button:
     * creates intent and puts an array with boolean values of checkbox checked
     * and value of number of players from scrollable number picker
     * goes to OfflineModeSetupActivity where players chooses names and avatars
     */
    public void onClickContinueButton(View view) {

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
/**
 * checks how many categories that are checked
 *
 */

        for (int i = 0; i < booleanArrayCategories.length; i++) {
            if (booleanArrayCategories[i]) {
                count++;
            }
        }

        if (count != 0) {

            Intent intent = new Intent(this, OfflineModeSetupActivity.class);
            intent.putExtra("booleanArrayCategories", booleanArrayCategories);
            intent.putExtra("numberOfPlayers", numberOfPlayers);
            startActivity(intent);
        } else if (count == 0) {
            ErrorMessageBoxNoCategories("Choose at least one category");
        }
    }

    /**
     * Checks all category checkboxes
     *
     */
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

    /**
     *  Unchecks all category boxes
     *
     */
    public void onClickDeSelectAllCategoriesButton(View view) {

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
}