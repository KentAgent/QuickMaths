package se.iths.apostolidis.quickmaths;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {


    private Button buttonTestPopUp;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private TextView textViewQuestion;
    private TextView textViewCorrectAnswer;
    private QuestionEngine questionEngine = new QuestionEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        importViewElemets();
        setQuestion();


    }
    public void setQuestion (){
        questionEngine.setQuestion();
        textViewQuestion.setText(questionEngine.getQuestion());
        btnAnswer1.setText(questionEngine.getAnswerChoise1());
        btnAnswer2.setText(questionEngine.getAnswerChoise2());
        btnAnswer3.setText(questionEngine.getAnswerChoise3());
        btnAnswer4.setText(questionEngine.getAnswerChoise4());
    }

    public void onClickAnswerAlt1 (View v){
        rightOrWrongAnswer(questionEngine.onClickAnswer1());
    }
    public void onClickAnswerAlt2 (View v){
        rightOrWrongAnswer(questionEngine.onClickAnswer2());
    }
    public void onClickAnswerAlt3 (View v){
        rightOrWrongAnswer(questionEngine.onClickAnswer3());
    }
    public void onClickAnswerAlt4 (View v){
        rightOrWrongAnswer(questionEngine.onClickAnswer4());
    }
    public void rightOrWrongAnswer (Boolean b){
        if (b){
            textViewCorrectAnswer.setText("You're right, bastard");
            textViewCorrectAnswer.setVisibility(TextView.VISIBLE);
        }
    }

    public void importViewElemets(){
        textViewQuestion = findViewById(R.id.textViewQuestion);
        btnAnswer1 = findViewById(R.id.buttonAnswer1);
        btnAnswer2 = findViewById(R.id.buttonAnswer2);
        btnAnswer3 = findViewById(R.id.buttonAnswer3);
        btnAnswer4 = findViewById(R.id.buttonAnswer4);
        textViewCorrectAnswer = findViewById(R.id.textViewIfCorrectAnswer);


        buttonTestPopUp = findViewById(R.id.buttonPopUpTest);
    }
    public void onClickbtnTestPopUp(View v){
        textViewCorrectAnswer.setVisibility(TextView.INVISIBLE);

    }
}
