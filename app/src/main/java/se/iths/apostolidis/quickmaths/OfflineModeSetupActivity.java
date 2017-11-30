package se.iths.apostolidis.quickmaths;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OfflineModeSetupActivity extends AppCompatActivity {

    int numberOfPlayers = 0;
    int emptyPlayers = 0;
    int hide = 0;

    EditText[] editTextsPN = new EditText[8];
    EditText etP1Name;
    EditText etP2Name;
    EditText etP3Name;
    EditText etP4Name;
    EditText etP5Name;
    EditText etP6Name;
    EditText etP7Name;
    EditText etP8Name;

    TextView[] textViewsPN = new EditText[8];
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


/*
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode_setup);
/*

        TODO// Skapa edittexts  och textviews i layout. constraint layout duger bra

        såhär: player1name: __________
               player2name: __________

               namnen på edittexts och textviews finns deklarerade i toppen.
               du ser dem även få ett värde från resources nedanför
               kör hårt så gör jag scorllable pick number så länge

               TODO

        etP1Name = findViewById(R.id.editTextPlayer1Name);
        etP2Name = findViewById(R.id.editTextPlayer2Name);
        etP3Name = findViewById(R.id.editTextPlayer3Name);
        etP4Name = findViewById(R.id.editTextPlayer4Name);
        etP5Name = findViewById(R.id.editTextPlayer5Name);
        etP6Name = findViewById(R.id.editTextPlayer6Name);
        etP7Name = findViewById(R.id.editTextPlayer7Name);
        etP8Name = findViewById(R.id.editTextPlayer8Name);

        tvP1Name = findViewById(R.id.textViewPlayer1Name);
        tvP2Name = findViewById(R.id.textViewPlayer2Name);
        tvP3Name = findViewById(R.id.textViewPlayer3Name);
        tvP4Name = findViewById(R.id.textViewPlayer4Name);
        tvP5Name = findViewById(R.id.textViewPlayer5Name);
        tvP6Name = findViewById(R.id.textViewPlayer6Name);
        tvP7Name = findViewById(R.id.textViewPlayer7Name);
        tvP8Name = findViewById(R.id.textViewPlayer8Name);
/*
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

/*
        textView1 = findViewById(R.id.textViewTest1);
        textView2 = findViewById(R.id.textViewTest2);
        textView3 = findViewById(R.id.textViewTest3);
        textView4 = findViewById(R.id.textViewTest4);
        textView5 = findViewById(R.id.textViewTest5);
        textView6 = findViewById(R.id.textViewTest6);
        textView7 = findViewById(R.id.textViewTest7);
        textView8 = findViewById(R.id.textViewTest8);
        textView9 = findViewById(R.id.textViewTest9);
*/

        Bundle extras = getIntent().getExtras();

        xTrue = extras.getInt("xTrue");

        //här instansieras stringarray som sätts till storleken av antalet valda kategorier;
        choosenCategories = new String[xTrue];

        choosenCategories = extras.getStringArray("choosenCategories");


        // gömmer antalet textviews och editexts baserat på antalet spelare

        emptyPlayers = 8 - numberOfPlayers;

        for (int i = 0; i < emptyPlayers; i++) {
            hide = numberOfPlayers + i;
            editTextsPN[hide].setVisibility(View.INVISIBLE);
            textViewsPN[hide].setVisibility(View.INVISIBLE);
        }
    }
}

            /*
            switch (xTrue) {
                case 1:
                    textView1.setText(choosenCategories[0]);
                    break;
                case 2:
                    textView1.setText(choosenCategories[0]);
                    textView2.setText(choosenCategories[1]);
                    break;
                case 3:
                    textView1.setText(choosenCategories[0]);
                    textView2.setText(choosenCategories[1]);
                    textView3.setText(choosenCategories[2]);
                    break;
                case 4:
                    textView1.setText(choosenCategories[0]);
                    textView2.setText(choosenCategories[1]);
                    textView3.setText(choosenCategories[2]);
                    textView4.setText(choosenCategories[3]);
                    break;
                case 5:
                    textView1.setText(choosenCategories[0]);
                    textView2.setText(choosenCategories[1]);
                    textView3.setText(choosenCategories[2]);
                    textView4.setText(choosenCategories[3]);
                    textView5.setText(choosenCategories[4]);
                    break;
                case 6:
                    textView1.setText(choosenCategories[0]);
                    textView2.setText(choosenCategories[1]);
                    textView3.setText(choosenCategories[2]);
                    textView4.setText(choosenCategories[3]);
                    textView5.setText(choosenCategories[4]);
                    textView6.setText(choosenCategories[5]);
                    break;
                case 7:
                    textView1.setText(choosenCategories[0]);
                    textView2.setText(choosenCategories[1]);
                    textView3.setText(choosenCategories[2]);
                    textView4.setText(choosenCategories[3]);
                    textView5.setText(choosenCategories[4]);
                    textView6.setText(choosenCategories[5]);
                    textView7.setText(choosenCategories[6]);
                    break;
                case 8:
                    textView1.setText(choosenCategories[0]);
                    textView2.setText(choosenCategories[1]);
                    textView3.setText(choosenCategories[2]);
                    textView4.setText(choosenCategories[3]);
                    textView5.setText(choosenCategories[4]);
                    textView6.setText(choosenCategories[5]);
                    textView7.setText(choosenCategories[6]);
                    textView8.setText(choosenCategories[7]);
                    break;
                case 9:
                    textView1.setText(choosenCategories[0]);
                    textView2.setText(choosenCategories[1]);
                    textView3.setText(choosenCategories[2]);
                    textView4.setText(choosenCategories[3]);
                    textView5.setText(choosenCategories[4]);
                    textView6.setText(choosenCategories[5]);
                    textView7.setText(choosenCategories[6]);
                    textView8.setText(choosenCategories[7]);
                    textView9.setText(choosenCategories[8]);
                    break;
                default:
                    break;
            }
            */