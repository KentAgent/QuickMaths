package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import se.iths.apostolidis.quickmaths.service.database.DBHelper;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText question;
    private EditText answer_1;
    private EditText answer_2;
    private EditText answer_3;
    private EditText answer_4;
    private Button submit;
    private DBHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        question = findViewById(R.id.editTextQuestion);
        answer_1 = findViewById(R.id.editTextCorrectAnswer);
        answer_2 = findViewById(R.id.editTextSecondAnswer);
        answer_3 = findViewById(R.id.editTextThirdAnswer);
        answer_4 = findViewById(R.id.editTextFourthAnswer);
        database = DBHelper.getInstance(this);

    }

    public void onClickSubmit(View view){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }

    public void onClickBack(View view){finish();}
}
