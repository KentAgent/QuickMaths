package se.iths.apostolidis.quickmaths;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class OfflineModeSetupActivity extends AppCompatActivity {

    private static String[] playerNameArray;
    private static Integer[] avatarArray;
    private static int numberOfPlayers = 0;
    private static boolean[] booleanCategories = new boolean[8];
    private String [] playerNames;

    private static EditText player1Name;
    private static EditText player2Name;
    private static EditText player3Name;
    private static EditText player4Name;
    private static EditText player5Name;
    private static EditText player6Name;
    private static EditText player7Name;
    private static EditText player8Name;


    //private Map map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode_setup);

        String numberOfPlayersTemp = "";

/**
 * Gets information from intent; Number of players and choice of question categories
 * puts boolean values of checkboxes in a boolean array.
 * Converts string value number of players to int.
 */

        Bundle extras = getIntent().getExtras();

        if(extras != null){

            booleanCategories[0] = extras.getBoolean("Humor");
            booleanCategories[1] = extras.getBoolean("Sport");
            booleanCategories[2] = extras.getBoolean("Film");
            booleanCategories[3] = extras.getBoolean("Musik");
            booleanCategories[4] = extras.getBoolean("Historia");
            booleanCategories[5] = extras.getBoolean("Geografi");
            booleanCategories[6] = extras.getBoolean("Random");
            booleanCategories[8] = extras.getBoolean("Sport");

            numberOfPlayersTemp = extras.getString("numberOfPlayers");
            numberOfPlayers = Integer.parseInt(numberOfPlayersTemp);

        }

        player1Name = findViewById(R.id.editTextPlayer1Name);
        player2Name = findViewById(R.id.editTextPlayer2Name);
        player3Name = findViewById(R.id.editTextPlayer3Name);
        player4Name = findViewById(R.id.editTextPlayer4Name);
        player5Name = findViewById(R.id.editTextPlayer5Name);
        player6Name = findViewById(R.id.editTextPlayer6Name);
        player7Name = findViewById(R.id.editTextPlayer7Name);
        player8Name = findViewById(R.id.editTextPlayer8Name);

        EditText [] editTexts = new EditText[8];
        editTexts[0] = player1Name;
        editTexts[1] = player1Name;
        editTexts[2] = player1Name;
        editTexts[3] = player1Name;
        editTexts[4] = player1Name;
        editTexts[5] = player1Name;
        editTexts[6] = player1Name;
        editTexts[7] = player1Name;

/**
 * Hides edit texts (player name entries) that goes beyond the number of players choosen
 *
  */
        int numberOfEditTextsToHide = 8 - numberOfPlayers;

        for (int i = 0; i < numberOfEditTextsToHide; i++) {
                int h = numberOfPlayers + i;
                editTexts[h].setVisibility(View.INVISIBLE);
        }


    }

    /*public void onClickStartNewGame(){
        Intent intent = new Intent(this, OfflineModeSetupActivity.class);
        intent.putExtra("numberOfPlayers", spinnerNumberOfPlayers.getSelectedItem().toString());
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



        /**
         * stores the avatar shortcuts in array
         */

        //avatarArray = { R.drawable.avatar1, R.drawable.avatar2, R.drawable.cube,
          //      R.drawable.fresh, R.drawable.guitar, R.drawable.orange, R.drawable.teapot };
}
    //ANVÄND i nästa offline_game_activity, kanske
    /*
    private void initializeImageList() {
        // TODO Auto-generated method stub
        for (int i = 0; i < avatarArray.length; i++) {
            map = new HashMap<Integer, Object>();

            map.put("Name", playerNameArray[i]);
            map.put("Avatar", avatarArray[i]);
            spinnerList.add(map);
        }
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource((spinnerList.get(0).get("Icon"));
        spinnerList.get(0).get("Name");

    }
    */
