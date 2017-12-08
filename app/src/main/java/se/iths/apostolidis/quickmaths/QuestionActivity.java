package se.iths.apostolidis.quickmaths;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import se.iths.apostolidis.quickmaths.containers.Quiz;
import se.iths.apostolidis.quickmaths.service.database.DBHelper;

public class QuestionActivity extends AppCompatActivity {

    Intent returnIntent;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private TextView textViewQuestion;
    private TextView textViewCorrectAnswer;
    private DBHelper database;
    private String correctAnswer;
    private RandomHelper randomHelper;
    private ArrayList<String> usedQuestions= new ArrayList<>();
    private String TAG = "questionLogTag";
    private String genre;
    private DBHelper dbHelper;
    private boolean result = false;
    private Handler handler;
    private long timeLeft = 10000;
    private TextView countdown;
    private int displayCountdown = 10;
    private Quiz quiz = new Quiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        genre = getIntent().getExtras().getString("Category");
        Log.d("Wille", genre);
        importViewElemets();
        setQuestion();
        countdown = findViewById(R.id.textViewCountdown);



//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                Log.d("Grekolas", "Countdown");
//                finish();
//            }
//        }, 10000);
//
//

    }


    public void setQuestion (){

        //Importera ett quiz objekt istället för database.getQuiz

        /**
         * Sets a random question based on category input
         */
//        int index = 1;
//        boolean isUsed;
            Random random = new Random();
//
////        Log.d(TAG, "startup");
//        if (usedQuestions.size() == (int)database.getQuizCategory(genre).size() * 0.8){
//            for(int i = usedQuestions.size() - 1; i >= 0; i--){
////                Log.d(TAG, "Removing object from usedQuestions");
//                usedQuestions.remove(i);
//            }
//        }
//
//        if(!usedQuestions.isEmpty()) {
//            do {
//                isUsed = false;
//                index = random.nextInt(database.getQuizCategory(genre).size());
//                //Log.d(TAG, "random index before loop");
//                //Log.d(TAG, String.valueOf(index));
//                for (int i = 0; i < usedQuestions.size(); i++) {
//                    //Log.d(TAG, "inside the for loop");
//                    if (database.getQuizCategory(genre).get(index).getQuestion().equals(usedQuestions.get(i))) {
//                        //Log.d(TAG, "isUsed is set to true");
//                        isUsed = true;
//                    }
//                }
//            }
//            while (isUsed);
//        } else {
            //Log.d(TAG, "Else statement");
            int index = random.nextInt(database.getQuizCategory(genre).size());
            quiz = database.getQuizCategory(genre).get(index);

//        }
        //TODO://Fixa randomHelper classen!
        //int index = randomHelper.randomBoundedIndex(database.getQuizCategory(genre).size());


        textViewQuestion.setText(quiz.getQuestion());
        btnAnswer1.setText(quiz.getAnswer1());
        btnAnswer2.setText(quiz.getAnswer2());
        btnAnswer3.setText(quiz.getAnswer3());
        btnAnswer4.setText(quiz.getAnswer4());
        correctAnswer = quiz.getCorrectAnswer();


        //Log.d(TAG, "adding to usedQuestions");
//        usedQuestions.add(database.getQuizCategory(genre).get(index).getQuestion());
        //Log.d(TAG, "Used questions size: " + String.valueOf(usedQuestions.size()));
        /**
         * Sets a random question from the database
         **/
//        Random random = new Random();
//        int index = random.nextInt(database.getAllQuizzes().size());
//        textViewQuestion.setText(database.getAllQuizzes().get(index).getQuestion());
//        btnAnswer1.setText(database.getAllQuizzes().get(index).getAnswer1());
//        btnAnswer2.setText(database.getAllQuizzes().get(index).getAnswer2());
//        btnAnswer3.setText(database.getAllQuizzes().get(index).getAnswer3());
//        btnAnswer4.setText(database.getAllQuizzes().get(index).getAnswer4());
//        correctAnswer = database.getAllQuizzes().get(index).getCorrectAnswer();
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
    public void onClickbtnTestPopUp(View v){
        setQuestion();
        textViewCorrectAnswer.setVisibility(TextView.INVISIBLE);

    }

}
