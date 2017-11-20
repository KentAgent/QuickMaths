package se.iths.apostolidis.quickmaths;
/*<<<<<<< HEAD

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
=======
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//public class QuestionActivity extends AppCompatActivity {
//
//
//    private Button buttonTestPopUp;
//    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
//    private TextView textViewQuestion;
//    private TextView textViewCorrectAnswer;
//    private QuestionEngine questionEngine = new QuestionEngine();
//    private DBHelper dbHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_question);
//        dbHelper = new DBHelper(this);
//        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
//        if (false == database.exists()){
//            Toast.makeText(this, "Copy Database sucess",Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Copy Database error",Toast.LENGTH_SHORT).show();
//
//        }
//
//        importViewElemets();
//        setQuestion();
//
//
//    }
//
//    public boolean copyDatabase(Context context){
//        try{
//            InputStream inputStream = context.getAssets().open(dbHelper.DBNAME);
//            String outFileName = dbHelper.DBLOCATION + dbHelper.DBNAME;
//            OutputStream outputStream =  new FileOutputStream(outFileName);
//            byte [] buff = new byte[1024];
//            int length = 0;
//            while ((length = inputStream.read(buff)) > 0 ){
//                outputStream.write(buff,0,length);
//            }
//            outputStream.flush();
//            outputStream.close();
//            Log.v("QuestinActivity", "dbCopied");
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public void setQuestion (){
//        textViewQuestion.setText(questionEngine.getQuestion());
//        btnAnswer1.setText(questionEngine.getAnswerChoise1());
//        btnAnswer2.setText(questionEngine.getAnswerChoise2());
//        btnAnswer3.setText(questionEngine.getAnswerChoise3());
//        btnAnswer4.setText(questionEngine.getAnswerChoise4());
//    }
//
//    public void onClickAnswerAlt1 (View v){
//
//        questionEngine.onClickAnswer1();
//        textViewCorrectAnswer.setText("You're right, bastard");
//        textViewCorrectAnswer.setVisibility(TextView.VISIBLE);
//    }
//    public void onClickAnswerAlt2 (View v){
//        questionEngine.onClickAnswer2();
//    }
//    public void onClickAnswerAlt3 (View v){
//        questionEngine.onClickAnswer3();
//    }
//    public void onClickAnswerAlt4 (View v){
//        questionEngine.onClickAnswer4();
//    }
//
//    public void importViewElemets(){
//        textViewQuestion = findViewById(R.id.textViewQuestion);
//        btnAnswer1 = findViewById(R.id.buttonAnswer1);
//        btnAnswer2 = findViewById(R.id.buttonAnswer2);
//        btnAnswer3 = findViewById(R.id.buttonAnswer3);
//        btnAnswer4 = findViewById(R.id.buttonAnswer4);
//        textViewCorrectAnswer = findViewById(R.id.textViewIfCorrectAnswer);
//
//
//        buttonTestPopUp = findViewById(R.id.buttonPopUpTest);
//    }
//    public void onClickbtnTestPopUp(View v){
//        textViewQuestion.setText(dbHelper.getQuestion().getAnswerChoise3());
//    }
//}
>>>>>>> robkam_setup_database*/
