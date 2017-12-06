package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;

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
    String[] choosenCategories;

    Button buttonSelectAll;
    Button buttonSelectNone;

    ImageButton buttonGoBack;
    ImageButton buttonGoForward;



    int xTrue;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

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
        stringArrayCategories[3] = "Music";
        stringArrayCategories[4] = "History";
        stringArrayCategories[5] = "Geography";
        stringArrayCategories[6] = "E-sport";
        stringArrayCategories[7] = "Random";
        stringArrayCategories[8] = "Math";


        xTrue = 0;

        for (int i = 0; i < booleanArrayCategories.length; i++) {
            if (booleanArrayCategories[i]) {
                xTrue++;
            }
        }

        choosenCategories = new String[xTrue];

        count = 0;

        for(int i = 0; i < booleanArrayCategories.length; i++){
            if(booleanArrayCategories[i]){
                choosenCategories[count] = stringArrayCategories[i];
                count++;
            }

        }


        if (xTrue > 0) {
            Intent intent = new Intent(this, OfflineModeSetupActivity.class);
            intent.putExtra("xTrue", xTrue);
            intent.putExtra("choosenCategories", choosenCategories);
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
