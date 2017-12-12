package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about = findViewById(R.id.textViewAbout);
        about.setText("The first version of this app \nwas created as a group project"+
                      "\n during 08/11/16 to 12/12/16");
    }

    public void onClickExpandTextView(View v){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }




    public void onClickBack(View view) {
        finish();
    }

}
