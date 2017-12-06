package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;

public class OfflineModeSetupActivity extends AppCompatActivity {

    int numberOfPlayers = 0;


    EditText[] editTextsPN = new EditText[4];
    EditText etP1Name;
    EditText etP2Name;
    EditText etP3Name;
    EditText etP4Name;


    TextView[] textViewsPN = new TextView[4];
    TextView tvP1Name;
    TextView tvP2Name;
    TextView tvP3Name;
    TextView tvP4Name;
    int setUpPlayers = 0;

    String[] playerNames;

    ScrollableNumberPicker snp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode_setup);

        //TODO skapa flera intents, en som sparar vid bak och en som sparar vid framåt knapparna



        etP1Name = findViewById(R.id.editTextNamePlayer1);
        etP2Name = findViewById(R.id.editTextNamePlayer2);
        etP3Name = findViewById(R.id.editTextNamePlayer3);
        etP4Name = findViewById(R.id.editTextNamePlayer4);


        tvP1Name = findViewById(R.id.textViewPlayer1Name);
        tvP2Name = findViewById(R.id.textViewPlayer2Name);
        tvP3Name = findViewById(R.id.textViewPlayer3Name);
        tvP4Name = findViewById(R.id.textViewPlayer4Name);


        textViewsPN[0] = tvP1Name;
        textViewsPN[1] = tvP2Name;
        textViewsPN[2] = tvP3Name;
        textViewsPN[3] = tvP4Name;


        editTextsPN[0] = etP1Name;
        editTextsPN[1] = etP2Name;
        editTextsPN[2] = etP3Name;
        editTextsPN[3] = etP4Name;


        snp = findViewById(R.id.number_picker_horizontal);
        int nrOfPlayers = 1;

        ScrollableNumberPickerListener nrPlayersListener = new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {
                setUpPlayers = snp.getValue();

            }
        };
        snp.setListener(nrPlayersListener);
        makeAllInvisable();
        Intent intent = new Intent();
        //Bundle extras = getIntent().getExtras();

        //numberOfPlayers = extras.getInt("numberOfPlayers");
        if(numberOfPlayers != 0) {
            for (int i = 0; i < nrOfPlayers; i++) {
                editTextsPN[i].setVisibility(View.VISIBLE);
                textViewsPN[i].setVisibility(View.VISIBLE);

            }
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

    public void makeAllInvisable(){
            for (EditText n: editTextsPN){
                n.setVisibility(View.INVISIBLE);
            }
            for (TextView n: textViewsPN){
                n.setVisibility(View.INVISIBLE);
            }
    }

    public void makePlayersVisable(View view){
        int players = snp.getValue();
        for (int i = 0; i < setUpPlayers; i++) {
            editTextsPN[i].setVisibility(View.VISIBLE);
            textViewsPN[i].setVisibility(View.VISIBLE);

        }
    }
    public void onClickForward(){

        playerNames = new String[setUpPlayers];

        for(int i = 0; i < setUpPlayers; i++){
            String temp = editTextsPN[i].toString();
            temp = playerNames[i];
        }

        ScrollableNumberPickerListener nrPlayersListener = new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {
                setUpPlayers = snp.getValue();

            }
        };

        Intent intent = new Intent(this, SinglePlayerActivity.class);
        intent.putExtra("numberOfPlayers", setUpPlayers);
        intent.putExtra("playerNames", playerNames);
        startActivity(intent);
    }

}




