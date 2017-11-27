package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MultiplayerActivity extends AppCompatActivity {

    TextView testUsername;
    FirebaseAuth mAuth;
    FirebaseUser bror;
    Context context;
    Button startMatch;
    GoogleApiClient googleApiClient;
    private GoogleSignInAccount user;
    private FirebaseAnalytics mFirebaseAnalytics;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        testUsername = findViewById(R.id.testUser);
        context = getApplicationContext();
        startMatch = findViewById(R.id.ButtonStartMatch);
        mAuth = FirebaseAuth.getInstance();
        bror = mAuth.getCurrentUser();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        user = GoogleSignIn.getLastSignedInAccount(this);

        Bundle bundle = new Bundle();

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);





    }

    public void test(){
      //  Intent myintent = new Intent(getApplicationContext(), LoginActivity2.class);
        //startActivity(myintent);

    }

}



