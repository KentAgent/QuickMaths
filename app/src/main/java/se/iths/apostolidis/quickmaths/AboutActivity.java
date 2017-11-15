package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutActivity extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        textView = findViewById(R.id.textView);
    }

    public void onClickExpandTextView(View v){
        //Hide / show more text


    }
    public void onClickTestQuestion (View v){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);

    }
}
