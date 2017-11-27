package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.TurnBasedMultiplayerClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MultiplayerActivity extends AppCompatActivity {

    TextView testUsername;
    FirebaseAuth mAuth;
    FirebaseUser bror;
    Context context;
    Button startMatch;
    private GoogleSignInAccount user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        testUsername = findViewById(R.id.testUser);
        context = getApplicationContext();
        startMatch = findViewById(R.id.ButtonStartMatch);
        mAuth = FirebaseAuth.getInstance();
        bror = mAuth.getCurrentUser();

        user = GoogleSignIn.getLastSignedInAccount(this);

        TurnBasedMultiplayerClient turnBasedMultiplayerClient = Games.getTurnBasedMultiplayerClient(this, user);


        testUsername.setText(user.getEmail());



    }

}


