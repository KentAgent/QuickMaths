package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import se.iths.apostolidis.quickmaths.containers.Quiz;
import se.iths.apostolidis.quickmaths.service.database.DBHelper;
import se.iths.apostolidis.quickmaths.service.network.FetchCallback;
import se.iths.apostolidis.quickmaths.service.network.RemoteDataManager;

import static android.media.AudioManager.ADJUST_MUTE;
import static android.media.AudioManager.ADJUST_UNMUTE;

public class MainActivity extends AppCompatActivity {
    private ImageButton muteBtn;
    private ImageButton infoButton;
    private AudioManager am;
    private Boolean isMuted = true;
    private TextView aboutUs;
    private Button singlePlayerButton;
    private Button multiplayerButton;
    private Button friendsButton;
    private Button addQuestionButton;
    private Button aboutButton;
    private TextView start;
    private ImageButton account;
    private ImageButton settings;
    private DBHelper database;
    private RemoteDataManager remoteDataManager;
    private ImageView background;


    //Boolean to check if first click has been used to set visibility
    private boolean firstClick = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        muteBtn =findViewById(R.id.imageButtonSound);
        infoButton = findViewById(R.id.imageButtonAbout);
        aboutUs = findViewById(R.id.textViewAboutUs);
        singlePlayerButton = findViewById(R.id.buttonSinglePlayer);
        multiplayerButton = findViewById(R.id.buttonMultiplayer);
        friendsButton = findViewById(R.id.buttonFriends);
        addQuestionButton = findViewById(R.id.buttonAddQuestion);
        aboutButton = findViewById(R.id.buttonAbout);
        start = findViewById(R.id.textViewStart);
        account = findViewById(R.id.imageButtonAccount);
        settings = findViewById(R.id.imageButtonSettings);
        background = findViewById(R.id.imageViewBackground);
        background.setScaleType(ImageView.ScaleType.FIT_XY);


        aboutUs.setText("CODED BY:\n\nKentAgent\nKakashi\nTantMutti\nFyrren\nSchwimpy\nMik3nox");
        database = DBHelper.getInstance(this);

        remoteDataManager = RemoteDataManager.getInstance();

        remoteDataManager.getQuizzes(new FetchCallback() {
            @Override
            public void didReceiveData(List<Quiz> quizzesFromServer) {

                database.removeoldQuizzes(quizzesFromServer, database.getAllQuizzes());
                database.updateQuizzes(quizzesFromServer);
                database.insertQuizzes(quizzesFromServer, database.getAllQuizzes());
                List<Quiz> quizzes = database.getAllQuizzes();
                for(int i = 0, len = quizzes.size(); i < len; i++) {
                    Log.d("test1", String.valueOf(quizzes.get(i)));
                    /*Log.e("", "");
                    Log.w("", "");
                    Log.i("", "");
                    Log.wtf("", "");
                    Log.v("", "");*/
                }

                //Log.d("test1", String.valueOf(database.getAllQuizzes()));
            }

            @Override
            public void didReceiveError() {

            }
        });


        //textViewtester.setText(database.getAllQuizzes().get(22).getQuestion());
    }

    public void onClickSinglePlayer(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        muteBtn.setVisibility(View.INVISIBLE);
        infoButton.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }

    public void onClickMultiplayer(View view) {
        Intent intent = new Intent(this, MultiplayerActivity.class);
        muteBtn.setVisibility(View.INVISIBLE);
        infoButton.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }

    public void onClickFriends(View view) {
        Intent intent = new Intent(this, FriendsActivity.class);
        muteBtn.setVisibility(View.INVISIBLE);
        infoButton.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }

    public void onClickAddQuestion(View view) {
        Intent intent = new Intent(this, AddQuestionActivity.class);
        muteBtn.setVisibility(View.INVISIBLE);
        infoButton.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }

    public void onClickImageButtonSettings(View view) {
        if (muteBtn.getVisibility() == View.INVISIBLE) {
            muteBtn.setVisibility(View.VISIBLE);
            infoButton.setVisibility(View.VISIBLE);

        }
        else {
            muteBtn.setVisibility(View.INVISIBLE);
            infoButton.setVisibility(View.INVISIBLE);

        }
    }

    public void onClickAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        muteBtn.setVisibility(View.INVISIBLE);
        infoButton.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }

    public void onClickImageButtonAbout(View view) {
        if (aboutUs.getVisibility() == View.INVISIBLE) {
            aboutUs.setVisibility(View.VISIBLE);
            addQuestionButton.setClickable(false);
            singlePlayerButton.setClickable(false);
            multiplayerButton.setClickable(false);
            aboutButton.setClickable(false);
            friendsButton.setClickable(false);

        }
        else
            aboutUs.setVisibility(View.INVISIBLE);


    }

    public void onClickImageViewBackground(View view) {
        if (!firstClick) {
            firstClick = true;
            setVisibleStartup();
        }
        if (aboutUs.getVisibility() == View.VISIBLE || muteBtn.getVisibility() == View.VISIBLE && firstClick) {
            aboutUs.setVisibility(View.INVISIBLE);
            muteBtn.setVisibility(View.INVISIBLE);
            infoButton.setVisibility(View.INVISIBLE);
            addQuestionButton.setClickable(true);
            singlePlayerButton.setClickable(true);
            multiplayerButton.setClickable(true);
            aboutButton.setClickable(true);
            friendsButton.setClickable(true);
        }
    }

    public void onClickStart(View view) {
        if (singlePlayerButton.getVisibility() == View.INVISIBLE) {
            setVisibleStartup();
        }

    }

    public void onClickAccountButton(View view) {
        Intent intent = new Intent(this, SettingsActivty.class);
        startActivity(intent);
    }

    public void setVisibleStartup() {
        singlePlayerButton.setVisibility(View.VISIBLE);
        multiplayerButton.setVisibility(View.VISIBLE);
        friendsButton.setVisibility(View.VISIBLE);
        addQuestionButton.setVisibility(View.VISIBLE);
        aboutButton.setVisibility(View.VISIBLE);
        account.setVisibility(View.VISIBLE);
        settings.setVisibility(View.VISIBLE);
        start.setVisibility(View.INVISIBLE);
        firstClick = true;
    }



    //Mute sound of application
    public void onClickMuteSounds(View view) {
        if(isMuted){
        am.adjustVolume(ADJUST_MUTE, 0);
        isMuted = false;
        muteBtn.setImageResource(R.drawable.ic_volume_off_black_24dp);
        } else {
            muteBtn.setImageResource(R.drawable.ic_volume_up_black_24dp);
            am.adjustVolume(ADJUST_UNMUTE, 0);
            isMuted = true;
        }

    }
}
