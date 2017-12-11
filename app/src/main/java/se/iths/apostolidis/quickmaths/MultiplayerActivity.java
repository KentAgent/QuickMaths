package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

import se.iths.apostolidis.quickmaths.GameLobbyStuff.FindLobbyActivity;
import se.iths.apostolidis.quickmaths.GameLobbyStuff.GameLobbyActivity;

public class MultiplayerActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "KakashiDota";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private Button checkUsers;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private static final int RC_SIGN_IN = 1;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Button signoutBtn;
    private String mUsername;
    private TextView userInfo;
    private FirebaseUser user;
    private Player player = new Player();


    Button lobbyBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
            lobbyBtn = findViewById(R.id.ButtonCreateLobby);
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://quizapp-5e35c-727f6.firebaseio.com/");
        mFirebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        signoutBtn = findViewById(R.id.buttonLog_Out);
        userInfo = findViewById(R.id.textViewYourStats);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user !=null){
                    player.setName(user.getDisplayName());

                    player.setUid(user.getUid());
                    userInfo.setText("Name: " + player.getName() + "\n" + user.getEmail() + "\n" + player.getUid());

                    //         onSignedInInitialize(user.getDisplayName());
                } else {
                    //user is signed out
             //       onSignedOutCleanUp();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setLogo(R.drawable.player1)
                                    .setTheme(R.style.LoginTheme)
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()))
                                    .build(),

                            RC_SIGN_IN);
                }
            }

        };

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            if (requestCode == RESULT_OK){
                Toast.makeText(this, "Signed in brov!", Toast.LENGTH_SHORT).show();
            } else if (requestCode == RESULT_CANCELED){
                Toast.makeText(this, "Signed in canceled!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAuthStateListener != null){
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }

    public void onClickSignout(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
    }


    public void onClickFindLobby (View view ){
        Intent intent = new Intent(this, FindLobbyActivity.class);
        startActivity(intent);
    }
    public void startLobby(View view){
        Intent myIntent = new Intent(getApplicationContext(), GameLobbyActivity.class);
        startActivity(myIntent);
    }

    public void onClickGoBack(View view){
        finish();
    }
}












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