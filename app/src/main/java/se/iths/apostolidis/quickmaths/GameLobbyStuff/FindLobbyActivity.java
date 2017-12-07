package se.iths.apostolidis.quickmaths.GameLobbyStuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import se.iths.apostolidis.quickmaths.Player;
import se.iths.apostolidis.quickmaths.R;

public class FindLobbyActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseRefrence;
    private FirebaseAuth mFirebaseAuth;
    private ChildEventListener mChildEventListener;
    private FirebaseUser user;
    private Player player = new Player();
    private EditText editText;
    private TextView textView;
    private Button searchButton;
    private ArrayList<String> playerIds = new ArrayList<>();
    private String lobbyID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_lobby);
        editText = findViewById(R.id.editTextSeachLobby);
        textView = findViewById(R.id.textViewShowOnlinePlayers);
        searchButton = findViewById(R.id.buttonSearchLobby);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();
        player.setUid(user.getUid());
        mMessagesDatabaseRefrence = mFirebaseDatabase.getReference().child("LobbyID").child("Players");

        attachDatabaseReadListener();

    }
    public void searchLobby(View view){
        lobbyID = "" + editText.getText();

    }

    public void onClickSearchLobby (View view){

        String lobbySearch = editText.getText().toString();

            if (lobbySearch.equals("LobbyID")){
                mMessagesDatabaseRefrence.setValue(player.getUid());
            }
    }


    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {

            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                    GameLobbyClass gameLobbyClass = dataSnapshot.getValue(GameLobbyClass.class);
//
//                    textView.setText(gameLobbyClass.toString());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            //defines what we are listening to .addChild defies what will happen when the event occurs
            mMessagesDatabaseRefrence.addChildEventListener(mChildEventListener);

        }
    }

}
