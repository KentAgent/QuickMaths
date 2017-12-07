package se.iths.apostolidis.quickmaths.GameLobbyStuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import se.iths.apostolidis.quickmaths.Player;
import se.iths.apostolidis.quickmaths.R;

public class ShowOnlineActivity extends AppCompatActivity {

    private TextView showOnline;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseRefrence;
    private FirebaseAuth mFirebaseAuth;
    private Button mSendButton;
    private FirebaseUser user;
    private Player player = new Player();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_online);
        showOnline = findViewById(R.id.textViewShowOnlinePlayers);
        mSendButton = findViewById(R.id.ButtonTestDB);
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://quizapp-5e35c-727f6.firebaseio.com/");
        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();

        mMessagesDatabaseRefrence = mFirebaseDatabase.getReference().child("LobbyID").child("Players");

        player.setUid(user.getUid());


        showCurrentPlayersOnline();

    }

    private void showCurrentPlayersOnline(){
       // mMessagesDatabaseRefrence.getDatabase().getReference().push().setValue(user.getUid());
    }

    public void OnClickTestDB(View view) {
        GameLobbyClass gameLobbyClass = new GameLobbyClass(player.getUid());
        mMessagesDatabaseRefrence.push().setValue(gameLobbyClass);

    }
}