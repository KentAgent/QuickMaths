package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import se.iths.apostolidis.quickmaths.GameLobbyStuff.GameLobbyActivity;
import se.iths.apostolidis.quickmaths.service.network.RemoteDataManager;

public class MultiplayerActivity extends AppCompatActivity {

    RemoteDataManager remoteDataManager;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    Player player = new Player();
    Button lobbyBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
            lobbyBtn = findViewById(R.id.ButtonCreateLobby);
//        mAuth = FirebaseAuth.getInstance();
//        user = mAuth.getCurrentUser();
//
//       //player.setId(user.getUid());
//        //Log.d("hund",   String.valueOf(user.getUid()));
//
//        remoteDataManager = RemoteDataManager.getInstance();
//
//        remoteDataManager.getGames(new GameCallback() {
//            @Override
//            public void didReceiveGames(String body) {
//                try {
//                    JSONObject jsonObject = new JSONObject(body);
//                    JSONArray games = jsonObject.getJSONArray("games");
//                    for (int x = 0, xLen = games.length(); x < xLen; x++) {
//                        JSONArray players = games.getJSONObject(x).getJSONArray("players");
//                        if (games.getJSONObject(x).getBoolean("isActive")) {
//                            for (int y = 0, yLen = players.length(); y < yLen; y++) {
//                                if (players.getJSONObject(y).getString("uid").equals(user.getUid())) {
//                                    if (players.getJSONObject(y).getBoolean("turnToPlay")) {
//                                        // GameEngine.rollDice();
//                                        //GameEngine.answerQuestion();
//                                        //GameEngine.endturn(x, y, user);
//
//
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                } catch (JSONException e) {
//                    Log.e("hund", e.getMessage());
//                    e.printStackTrace();
//                }
//
//            }
//
//        });
//    }
    }


    public void startLobby(View view){
        Intent myIntent = new Intent(getApplicationContext(), GameLobbyActivity.class);
        startActivity(myIntent);
    }
}