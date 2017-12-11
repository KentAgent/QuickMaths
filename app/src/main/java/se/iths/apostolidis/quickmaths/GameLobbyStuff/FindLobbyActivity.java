package se.iths.apostolidis.quickmaths.GameLobbyStuff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import se.iths.apostolidis.quickmaths.GameActivity;
import se.iths.apostolidis.quickmaths.Player;
import se.iths.apostolidis.quickmaths.R;

public class FindLobbyActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseRefrence;
    private FirebaseAuth mFirebaseAuth;
    private ChildEventListener mChildEventListener;
    private FirebaseUser user;
    private Player player = new Player();
    private String lobbySearch;
    private EditText editText;
    private TextView textView;
    private Button searchButton;
    private ArrayList<String> playerIds = new ArrayList<>();
    private String lobbyID;
    private boolean success;
    private String snapShot = "";
    private Button removeUser;
    private GameActivity gameActivity;
    private ArrayList<Player> players = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_lobby);
        editText = findViewById(R.id.editTextSeachLobby);
        textView = findViewById(R.id.textViewPlayerIDs);
        searchButton = findViewById(R.id.buttonCreateLobby);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();
        player.setUid(user.getUid());
        mMessagesDatabaseRefrence = mFirebaseDatabase.getReference();
        removeUser =findViewById(R.id.button2);



        attachDatabaseReadListener();

    }
    public void searchLobby(View view){
        mMessagesDatabaseRefrence.child("Players");



    }



    public void onClickleaveLobby(View view){

        Query query = mMessagesDatabaseRefrence.child("Lobbies").orderByChild(player.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    dataSnapshot1.getRef().child(player.getUid()).removeValue();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.d("bror", "onClickleaveLobby: " );
    }

    public void onClickSearchLobby (View view){
        //CREATE LOBBY!
        lobbySearch = editText.getText().toString();
        Map<String, Map<String, Player>> lobby = new HashMap<>();
        Map<String,Object> users = new HashMap<>();
        
        users.put(player.getUid(),player);
        users.put("Player2", new Player());
        users.put("Player3", new Player());
        users.put("Player4", new Player());



        //lobby.put(lobbySearch, users);
        mMessagesDatabaseRefrence.child("Lobbies").child(lobbySearch).updateChildren(users);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String firebaseId = dataSnapshot.toString();

                textView.setText(dataSnapshot.getChildren().toString());






              //  mMessagesDatabaseRefrence.child(s).

              //  dataSnapshot.getRef().setValue(null);

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
        mMessagesDatabaseRefrence.child("Lobbies").child(lobbySearch).addChildEventListener(mChildEventListener);


    }

    private void printPlayers() {
        mMessagesDatabaseRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //textView.setText(dataSnapshot.getValue().toString() + "\n");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Hund", "Failed ", databaseError.toException());
            }
        });
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
    public void onClickStartGame(View view) {
        gameActivity = new GameActivity();
        gameActivity.gameSetUp(gameActivity.gridMPhotoView);
    }



}
