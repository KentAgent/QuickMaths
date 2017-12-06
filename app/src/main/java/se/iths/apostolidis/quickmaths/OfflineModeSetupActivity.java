package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;


import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;

public class OfflineModeSetupActivity extends AppCompatActivity {



   private EditText[] editTextsPN = new EditText[4];
   private EditText etP1Name;
   private EditText etP2Name;
   private EditText etP3Name;
   private EditText etP4Name;
   private TextView[] textViewsPN = new TextView[4];
   private TextView tvP1Name;
   private TextView tvP2Name;
   private TextView tvP3Name;
    private TextView tvP4Name;

    private int nrOfPlayers;
    private ScrollableNumberPicker snp;
    private int temp = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode_setup);




        //Initierar varablerna med xml-referenser.
        etP1Name = findViewById(R.id.editTextNamePlayer1);
        etP2Name = findViewById(R.id.editTextNamePlayer2);
        etP3Name = findViewById(R.id.editTextNamePlayer3);
        etP4Name = findViewById(R.id.editTextNamePlayer4);

        tvP1Name = findViewById(R.id.textViewPlayer1Name);
        tvP2Name = findViewById(R.id.textViewPlayer2Name);
        tvP3Name = findViewById(R.id.textViewPlayer3Name);
        tvP4Name = findViewById(R.id.textViewPlayer4Name);

        snp = findViewById(R.id.number_picker_horizontal);


        //Skapar arrayer för players textViews och Edittext
        textViewsPN[0] = tvP1Name;
        textViewsPN[1] = tvP2Name;
        textViewsPN[2] = tvP3Name;
        textViewsPN[3] = tvP4Name;


        editTextsPN[0] = etP1Name;
        editTextsPN[1] = etP2Name;
        editTextsPN[2] = etP3Name;
        editTextsPN[3] = etP4Name;

        //Gör alla spelare utan första osynliga
        makeAllInvisable();

        //Lyssnar efter förändringar på antalet spelare
        snp.setListener(new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {

                if(temp < value) {
                    addPlayers(value);

                } else if(temp > value){
                    removePlayers(value, temp);
                }
                temp = value;
            }
        });






        //Intent intent = new Intent();
        //Bundle extras = getIntent().getExtras();

        //numberOfPlayers = extras.getInt("numberOfPlayers");

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
        for (int i = 1; i < editTextsPN.length; i++) {
            editTextsPN[i].setVisibility(View.INVISIBLE);
            textViewsPN[i].setVisibility(View.INVISIBLE);
        }
    }

    public void addPlayers(int nrOfPlayers){
        if(nrOfPlayers != 0) {
            for (int i = 0; i < snp.getValue(); i++) {
                editTextsPN[i].setVisibility(View.VISIBLE);
                textViewsPN[i].setVisibility(View.VISIBLE);

            }
        }
        nrOfPlayers = snp.getValue();
        Log.d("Yeah", "onClickChangePlayers: snp.getValue() ");

    }

    public void removePlayers(int nrOfPlayers, int temp){
        if(nrOfPlayers != 0){
            for (int i = temp ; i > nrOfPlayers  ; i--) {
                editTextsPN[i-1].setVisibility(View.INVISIBLE);
                textViewsPN[i-1].setVisibility(View.INVISIBLE);
            }
        }
    }



}




