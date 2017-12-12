package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPickerListener;

import java.util.ArrayList;

public class OfflineModeSetupActivity extends AppCompatActivity {

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

    ImageView[] imageViewAvatarPlayers = new ImageView[4];
    ImageView avatar1;
    ImageView avatar2;
    ImageView avatar3;
    ImageView avatar4;


    ArrayList<String> playerNames = new ArrayList<>();

    private ScrollableNumberPicker snp;
    private int tempo = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode_setup);

        //TODO skapa flera intents, en som sparar vid bak och en som sparar vid framåt knapparna


        //Initerierar variabler med xml-referenser.
        etP1Name = findViewById(R.id.editTextNamePlayer1);
        etP2Name = findViewById(R.id.editTextNamePlayer2);
        etP3Name = findViewById(R.id.editTextNamePlayer3);
        etP4Name = findViewById(R.id.editTextNamePlayer4);


        tvP1Name = findViewById(R.id.textViewPlayer1Name);
        tvP2Name = findViewById(R.id.textViewPlayer2Name);
        tvP3Name = findViewById(R.id.textViewPlayer3Name);
        tvP4Name = findViewById(R.id.textViewPlayer4Name);

        avatar1 = findViewById(R.id.imageViewP1OfflineAvatar);
        avatar2 = findViewById(R.id.imageViewP2OfflineAvatar);
        avatar3 = findViewById(R.id.imageViewP3OfflineAvatar);
        avatar4 = findViewById(R.id.imageViewP4OfflineAvatar);

        snp = findViewById(R.id.number_picker_horizontal);


        //Set default value names for players
        etP1Name.setText("Player1");
        etP2Name.setText("Player2");
        etP3Name.setText("Player3");
        etP4Name.setText("Player4");

        //Skapar arrayer för Player-widgets
        textViewsPN[0] = tvP1Name;
        textViewsPN[1] = tvP2Name;
        textViewsPN[2] = tvP3Name;
        textViewsPN[3] = tvP4Name;


        editTextsPN[0] = etP1Name;
        editTextsPN[1] = etP2Name;
        editTextsPN[2] = etP3Name;
        editTextsPN[3] = etP4Name;

        imageViewAvatarPlayers[0] = avatar1;
        imageViewAvatarPlayers[1] = avatar2;
        imageViewAvatarPlayers[2] = avatar3;
        imageViewAvatarPlayers[3] = avatar4;



        //Gör alla spelare utan första osynliga
        makeAllInvisable();

        //Lyssnar efter förändringar på antalet spelare

        snp.setListener(new ScrollableNumberPickerListener() {
            @Override
            public void onNumberPicked(int value) {

                if (tempo < value) {
                    addPlayers(value);

                } else if (tempo > value) {
                    removePlayers(value, tempo);
                }
                tempo = value;
            }
        });


    }

    public void onClickGoBack(View view){
        finish();
    }





    public void onClickForward(View view){
        setUpPlayers = snp.getValue();

        addNamesOrDefaultNames();





        Intent intent = new Intent(this, SinglePlayerActivity.class);
        intent.putExtra("setUpPlayers", setUpPlayers);
        intent.putExtra("playerNames", playerNames);
        startActivity(intent);
    }

    public void makeAllInvisable(){
        for (int i = 1; i < editTextsPN.length; i++) {
            editTextsPN[i].setVisibility(View.INVISIBLE);
            textViewsPN[i].setVisibility(View.INVISIBLE);
            imageViewAvatarPlayers[i].setVisibility(View.INVISIBLE);
        }
    }

    public void addPlayers(int nrOfPlayers){
        if(nrOfPlayers != 0) {
            for (int i = 0; i < snp.getValue(); i++) {
                editTextsPN[i].setVisibility(View.VISIBLE);
                textViewsPN[i].setVisibility(View.VISIBLE);
                imageViewAvatarPlayers[i].setVisibility(View.VISIBLE);
            }
        }

    }

    public void removePlayers(int nrOfPlayers, int temp){
        if(nrOfPlayers != 0){
            for (int i = temp ; i > nrOfPlayers  ; i--) {
                editTextsPN[i-1].setVisibility(View.INVISIBLE);
                textViewsPN[i-1].setVisibility(View.INVISIBLE);
                imageViewAvatarPlayers[i-1].setVisibility(View.INVISIBLE);
            }
        }
    }

    public void addNamesOrDefaultNames(){
        for(int i = 0; i < setUpPlayers; i++){
            playerNames.add(editTextsPN[i].getText().toString());
            if (playerNames.get(i).isEmpty()){
                playerNames.set(i, editTextsPN[i].getHint().toString());
            }
        }

    }

}








