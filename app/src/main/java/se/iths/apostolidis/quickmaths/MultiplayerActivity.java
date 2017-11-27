package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.InvitationsClient;
import com.google.android.gms.games.TurnBasedMultiplayerClient;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.tasks.Task;

public class MultiplayerActivity extends AppCompatActivity {

    private TextView textView;
    private GoogleSignInAccount gsa;
    private Task<GoogleSignInAccount> andreas;
    private TurnBasedMultiplayerClient turnBasedMultiplayerClient;
    private ImageView googleImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        textView = findViewById(R.id.textViewEmail);
        Intent intent = getIntent();
        Boolean s = isSignedIn();
        String t = "t" + s;
        textView.setText(t);
        googleImage = findViewById(R.id.imageViewGoogleImage);

        gsa = GoogleSignIn.getLastSignedInAccount(this);
        String st = gsa.getEmail().toString();
        googleImage.setImageURI(gsa.getPhotoUrl());
        Glide.with(this)
                        .load(gsa.getPhotoUrl())
                        .into(googleImage);
         textView.setText(st);
        
        //turnBasedMultiplayerClient = Games.getTurnBasedMultiplayerClient(this, gsa);

    }

    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }

    //public void tbmc(){
     //   try {
      //      turnBasedMultiplayerClient = Games.getTurnBasedMultiplayerClient(this,gsa) ;
       // }
    //}
}
