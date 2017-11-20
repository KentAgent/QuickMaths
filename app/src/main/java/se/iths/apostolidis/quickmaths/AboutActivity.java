package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import se.iths.apostolidis.quickmaths.service.database.DBHelper;
import se.iths.apostolidis.quickmaths.service.network.RemoteDataManager;

public class AboutActivity extends AppCompatActivity {
    private TextView textView;
    private DBHelper database;
    private RemoteDataManager remoteDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        textView = findViewById(R.id.textView);

        textView.setText(database.getAllQuizzes().get(33).getQuestion());
    }

    public void onClickExpandTextView(View v){
        //Hide / show more text


    }
    public void onClickTestQuestion (View v){


    }

    public void testDatabase(View view){
        Intent intent = new Intent(getApplicationContext(), CopyDbActivity.class);
        startActivity(intent);
    }

    public void testaDen(View view){

    }

}
