package se.iths.apostolidis.quickmaths;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import se.iths.apostolidis.quickmaths.containers.Quiz;
import se.iths.apostolidis.quickmaths.service.database.DBHelper;

public class QuestionActivity extends AppCompatActivity {

    Intent returnIntent;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private TextView textViewQuestion;
    private TextView textViewCorrectAnswer;
    private DBHelper database;
    private String correctAnswer;
    private RandomHelper randomHelper = new RandomHelper();
    private ArrayList<String> usedQuestions= new ArrayList<>();
    private String TAG = "questionLogTag";
    private String genre;
    private DBHelper dbHelper;
    private boolean result = false;
    private Handler handler;
    private long timeLeft = 10000;
    private TextView countdown;
    private int displayCountdown = 10;
    private Button newQuestion;
    private Quiz quiz = new Quiz();
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        newQuestion = findViewById(R.id.buttonNewQuestion);
        genre = getIntent().getExtras().getString("Category");
        Log.d("Wille", genre);
        importViewElemets();
        setQuestion();
        countdown = findViewById(R.id.textViewCountdown);

        countDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("Grekolas", "Countdown" + displayCountdown);
                countdown.setText("" + displayCountdown--);
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        countDownTimer.start();

    }


        /**
         * Sets a random question based on category input
         */
    public void setQuestion () {

        int index = randomHelper.randomBoundedIndex(database.getQuizCategory(genre).size());
        quiz = database.getQuizCategory(genre).get(index);
        textViewQuestion.setText(quiz.getQuestion());
        btnAnswer1.setText(quiz.getAnswer1());
        btnAnswer2.setText(quiz.getAnswer2());
        btnAnswer3.setText(quiz.getAnswer3());
        btnAnswer4.setText(quiz.getAnswer4());

        correctAnswer = quiz.getCorrectAnswer();


        usedQuestions.add(database.getQuizCategory(genre).get(index).getQuestion());

    }

    public void getQuestionIntent(Boolean b){
        returnIntent = new Intent();
        returnIntent.putExtra("result", b);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }

    public void onClickAnswerAlt1 (View v){
        rightOrWrongAnswer(btnAnswer1.getText().toString());
        //rightOrWrongAnswer(questionEngine.onClickAnswer1());
    }
    public void onClickAnswerAlt2 (View v){
        rightOrWrongAnswer(btnAnswer2.getText().toString());
        //rightOrWrongAnswer(questionEngine.onClickAnswer2());
    }
    public void onClickAnswerAlt3 (View v){
        rightOrWrongAnswer(btnAnswer3.getText().toString());
        //rightOrWrongAnswer(questionEngine.onClickAnswer3());
    }
    public void onClickAnswerAlt4 (View v){
        rightOrWrongAnswer(btnAnswer4.getText().toString());
        //rightOrWrongAnswer(questionEngine.onClickAnswer4());
    }

    public void rightOrWrongAnswer (String guess){
        if (guess.equals(correctAnswer)){
            result = true;
            getQuestionIntent(result);
            textViewCorrectAnswer.setText("You're right, bastard");
            textViewCorrectAnswer.setVisibility(TextView.VISIBLE);
        } else {
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();

        }
    }


    public void importViewElemets(){
        textViewQuestion = findViewById(R.id.textViewQuestion);
        database = DBHelper.getInstance(this);
        btnAnswer1 = findViewById(R.id.buttonAnswer1);
        btnAnswer2 = findViewById(R.id.buttonAnswer2);
        btnAnswer3 = findViewById(R.id.buttonAnswer3);
        btnAnswer4 = findViewById(R.id.buttonAnswer4);
        textViewCorrectAnswer = findViewById(R.id.textViewIfCorrectAnswer);
    }
    public void onClickNewQuestion(View v){
        setQuestion();
        textViewCorrectAnswer.setVisibility(TextView.INVISIBLE);
        newQuestion.setVisibility(View.INVISIBLE);

    }

}
