package se.iths.apostolidis.quickmaths;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import se.iths.apostolidis.quickmaths.service.network.GameCallback;
import se.iths.apostolidis.quickmaths.service.network.RemoteDataManager;

public class GameActivity extends AppCompatActivity {

    PhotoView map;
    private RemoteDataManager remoteDataManager;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        map = findViewById(R.id.photo_view);
        map.setImageResource(R.mipmap.gamemap);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        Log.d("hund", user.getUid());

        remoteDataManager = RemoteDataManager.getInstance();

        remoteDataManager.getGames(new GameCallback() {

            @Override
            public void didReceiveGames(String body) {
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    JSONArray games = jsonObject.getJSONArray("games");
                    for(int x = 0, xLen = games.length(); x < xLen; x++) {
                        JSONArray players = games.getJSONObject(x).getJSONArray("players");
                        if(games.getJSONObject(x).getBoolean("isActive")){
                            for(int y = 0, yLen = players.length(); y < yLen; y++) {
                                if(players.getJSONObject(y).getString("uid").equals(user.getUid())){
                                    if(players.getJSONObject(y).getBoolean("turnToPlay")){
                                        GameEngine.rollDice();
                                        GameEngine.answerQuestion();
                                        GameEngine.endturn(x, y, user);



                                    }
                                }
                            }

                        }
                    }
                } catch (JSONException e) {
                    Log.e("hund", e.getMessage());
                    e.printStackTrace();
                }

            }

        });

    }
}
