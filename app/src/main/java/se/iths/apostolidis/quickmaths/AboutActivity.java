package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import se.iths.apostolidis.quickmaths.service.database.DBHelper;
import se.iths.apostolidis.quickmaths.service.network.RemoteDataManager;

public class AboutActivity extends AppCompatActivity {
    private TextView textView;
    private DBHelper database;
    private Button questionButton;
    private RemoteDataManager remoteDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        questionButton = findViewById(R.id.button123);
        textView = findViewById(R.id.textView);
        database = DBHelper.getInstance(this);
       textView.setText(database.getAllQuizzes().get(10).getQuestion());
    }

    public void onClickExpandTextView(View v){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
    public void onClickTestQuestion (View v){
        //      Intent intent = new Intent(this, QuestionActivity.class);
        //      startActivity(intent);

        Random random = new Random();

        //int index = random.nextInt(database.getAllQuizzes().size());
        //textView.setText(database.getAllQuizzes().get(index).getQuestion());

        int index = random.nextInt(database.getQuizCategory("Humor").size());
        textView.setText(database.getQuizCategory("Humor").get(index).getGenre());
    }

    public void testDatabase(View view){
        Intent intent = new Intent(getApplicationContext(), CopyDbActivity.class);
        startActivity(intent);
    }

    public void testaDen(View view){


    }

    public void avatarButton(View view) {
        Intent intent = new Intent(getApplicationContext(), AvatarActivity.class);
        startActivity(intent);
    }

    public void onClickGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onClickBack(View view) {
        finish();
    }

}
