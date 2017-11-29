package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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

    boolean booleanArrayCategories[];

    Button buttonSelectAll;
    Button buttonSelectNone;
    Button buttonGoBack;
    Button buttonProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        booleanArrayCategories = new boolean[9];
        buttonSelectAll = findViewById(R.id.buttonSelectAll);
        buttonSelectNone = findViewById(R.id.buttonSelectNone);
        buttonGoBack = findViewById(R.id.buttonGoBack);
        buttonProceed = findViewById(R.id.buttonRandom);
    }

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
     * When clicking on continue button:
     * creates intent and puts an array with boolean values of checkbox checked
     * and value of number of players from scrollable number picker
     * goes to OfflineModeSetupActivity where players chooses names and avatars
     */
    public void onClickProceed(View view) {

        booleanArrayCategories[0] = cHumor.isChecked();
        booleanArrayCategories[1] = cSport.isChecked();
        booleanArrayCategories[2] = cFilm.isChecked();
        booleanArrayCategories[3] = cMusic.isChecked();
        booleanArrayCategories[4] = cHistory.isChecked();
        booleanArrayCategories[5] = cGeography.isChecked();
        booleanArrayCategories[6] = cEsport.isChecked();
        booleanArrayCategories[7] = cRandom.isChecked();
        booleanArrayCategories[8] = cMath.isChecked();
/**
 * checks how many categories that are checked
 *
 */
        int count = 0;

        for (int i = 0; i < booleanArrayCategories.length; i++) {
            if (booleanArrayCategories[i]) {
                count++;
            }
        }

        if (count != 0) {
            Intent intent = new Intent(this, OfflineModeSetupActivity.class);
            intent.putExtra("booleanArrayCategories", booleanArrayCategories);
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
