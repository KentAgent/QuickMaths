package se.iths.apostolidis.quickmaths.service.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import se.iths.apostolidis.quickmaths.containers.Quiz;


public class DBHelper extends SQLiteOpenHelper {


    private static DBHelper instance = null;

    static final String ID = "id";
    private static final String DATABASE_NAME = "quiz_database";
    private static final int DATABASE_VERSION = 1;
    private final QuizOperations quizOperations;

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        quizOperations = new QuizOperations(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, QuizOperations.QUIZ_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTable(SQLiteDatabase db, String operation){
        db.execSQL(operation);
    }


    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = quizOperations.getAllQuizzes();
        return quizzes != null ? quizzes : new ArrayList<Quiz>(); // shorthand -if

    }

    public List<Quiz> getQuizCategory(String category){
        /**
         * Improved quiz getter based on category
         */
        List<Quiz> quizCategory = quizOperations.getQuizCategory(category);
        return quizCategory != null ? quizCategory : new ArrayList<Quiz>(); // shorthand -if

        /**
         * Old way. Not very efficient
         */
        //List<Quiz> allQuizzes = quizOperations.getAllQuizzes();
//        ArrayList<Quiz> quizCategory = new ArrayList<>();
//        for (int i = 0; i < allQuizzes.size(); i++){
//            if (allQuizzes.get(i).getGenre().equals(category)){
//                quizCategory.add(allQuizzes.get(i));
//            }
//        }
//        return quizCategory;
    }

    public void removeoldQuizzes(List<Quiz> quizzesFromServer, List<Quiz> QuizzesFromDatabase) {
        for (int x = 0, xLen = QuizzesFromDatabase.size(); x < xLen;  x++) {
            for (int y = 0, yLen = quizzesFromServer.size(); y < yLen; y++) {
                if (QuizzesFromDatabase.get(x).getId().equals(quizzesFromServer.get(y).getId())){

                    break;
                }
                if(y == yLen - 1){
                    quizOperations.removeQuiz(QuizzesFromDatabase.get(x));

                }
            }
        }
    }

    public void updateQuizzes(List<Quiz> quizzesFromServer) {
        for (int i = 0, len = quizzesFromServer.size(); i < len ; i++) {
            if(!quizOperations.updateQuiz(quizzesFromServer.get(i))){
                quizOperations.insertQuiz(quizzesFromServer.get(i));
            }
        }
    }

    public void insertQuizzes(List<Quiz> quizzesFromServer, List<Quiz> quizzesFromDatabase) {
        for (int x = 0, xLen = quizzesFromServer.size(); x < xLen; x++) {
            for (int y = 0, yLen = quizzesFromDatabase.size(); y < yLen ; y++) {
                if(quizzesFromServer.get(x).getId().equals(quizzesFromDatabase.get(y).getId())){
                    break;
                }
                if(y == yLen - 1){
                    quizOperations.insertQuiz(quizzesFromServer.get(x));
                }
            }

        }
    }
}
