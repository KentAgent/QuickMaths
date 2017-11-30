package se.iths.apostolidis.quickmaths;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OfflineModeSetupActivity extends AppCompatActivity {

    int numberOfPlayers = 0;
    int emptyPlayers = 3;
    int hide = 7;

    EditText[] editTextsPN = new EditText[8];
    EditText etP1Name;
    EditText etP2Name;
    EditText etP3Name;
    EditText etP4Name;
    EditText etP5Name;
    EditText etP6Name;
    EditText etP7Name;
    EditText etP8Name;

    TextView[] textViewsPN = new TextView[8];
    TextView tvP1Name;
    TextView tvP2Name;
    TextView tvP3Name;
    TextView tvP4Name;
    TextView tvP5Name;
    TextView tvP6Name;
    TextView tvP7Name;
    TextView tvP8Name;

    String[] choosenCategories;
    int xTrue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode_setup);





        etP1Name = findViewById(R.id.editTextNamePlayer1);
        etP2Name = findViewById(R.id.editTextNamePlayer2);
        etP3Name = findViewById(R.id.editTextNamePlayer3);
        etP4Name = findViewById(R.id.editTextNamePlayer4);
        etP5Name = findViewById(R.id.editTextNamePlayer5);
        etP6Name = findViewById(R.id.editTextNamePlayer6);
        etP7Name = findViewById(R.id.editTextNamePlayer7);
        etP8Name = findViewById(R.id.editTextNamePlayer8);

        tvP1Name = findViewById(R.id.textViewPlayer1Name);
        tvP2Name = findViewById(R.id.textViewPlayer2Name);
        tvP3Name = findViewById(R.id.textViewPlayer3Name);
        tvP4Name = findViewById(R.id.textViewPlayer4Name);
        tvP5Name = findViewById(R.id.textViewPlayer5Name);
        tvP6Name = findViewById(R.id.textViewPlayer6Name);
        tvP7Name = findViewById(R.id.textViewPlayer7Name);
        tvP8Name = findViewById(R.id.textViewPlayer8Name);

        textViewsPN[0] = tvP1Name;
        textViewsPN[1] = tvP2Name;
        textViewsPN[2] = tvP3Name;
        textViewsPN[3] = tvP4Name;
        textViewsPN[4] = tvP5Name;
        textViewsPN[5] = tvP6Name;
        textViewsPN[6] = tvP7Name;
        textViewsPN[7] = tvP8Name;

        editTextsPN[0] = etP1Name;
        editTextsPN[1] = etP2Name;
        editTextsPN[2] = etP3Name;
        editTextsPN[3] = etP4Name;
        editTextsPN[4] = etP5Name;
        editTextsPN[5] = etP6Name;
        editTextsPN[6] = etP7Name;
        editTextsPN[7] = etP8Name;


        for (int i = 0; i < emptyPlayers; i++) {
            editTextsPN[hide].setVisibility(View.INVISIBLE);
            textViewsPN[hide].setVisibility(View.INVISIBLE);
            hide--;
        }





        /* tar fram info från bundle från SinglePlayerActivity
        Bundle extras = getIntent().getExtras();

        xTrue = extras.getInt("xTrue");

        //här instansieras stringarray som sätts till storleken av antalet valda kategorier;
        choosenCategories = new String[xTrue];

        choosenCategories = extras.getStringArray("choosenCategories");


         gömmer antalet textviews och editexts baserat på antalet spelare

        emptyPlayers = 8 - numberOfPlayers;

        for (int i = 0; i < emptyPlayers; i++) {
            hide = numberOfPlayers + i;
            editTextsPN[hide].setVisibility(View.INVISIBLE);
            textViewsPN[hide].setVisibility(View.INVISIBLE);

        }
        */

              /*
        Skriver ut valda kategorier i textviews
         */
              /*
        if(choosenCategories != null){
                int index = 0;
                for (String n: choosenCategories){
                textViews.get(index).setText(n);
                index++;
                }

                }

                */
    }
}




