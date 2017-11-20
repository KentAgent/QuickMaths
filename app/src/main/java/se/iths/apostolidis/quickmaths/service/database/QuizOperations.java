package se.iths.apostolidis.quickmaths.service.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.iths.apostolidis.quickmaths.containers.Quiz;

/**
 * Created by Kakashi on 18/11/17.
 */

class QuizOperations {

    private final SQLiteDatabase reableDatabase;
    private final SQLiteDatabase writableDatabase;

    private static final String TABLE_QUIZ = "table_quiz";

    private static final String ID = DBHelper.ID;
    private static final String QUIZ_ID = "quiz_id";
    private static final String QUIZ_QUESTION = "question";
    private static final String QUIZ_ANSWER_1 = "answer_1";
    private static final String QUIZ_ANSWER_2 = "answer_2";
    private static final String QUIZ_ANSWER_3 = "answer_3";
    private static final String QUIZ_ANSWER_4 = "answer_4";
    private static final String QUIZ_CORRECT_ANSWER = "correct_answer";
    private static final String QUIZ_GENRE = "genre";

    static final String QUIZ_TABLE =
            "CREATE TABLE " + TABLE_QUIZ +
            "(" +
            ID + " INTEGER PRIMARY KEY," +
            QUIZ_ID + " TEXT," +
            QUIZ_QUESTION + " TEXT," +
            QUIZ_ANSWER_1 + " TEXT," +
            QUIZ_ANSWER_2 + " TEXT," +
            QUIZ_ANSWER_3 + " TEXT," +
            QUIZ_ANSWER_4 + " TEXT," +
            QUIZ_CORRECT_ANSWER + " TEXT," +
            QUIZ_GENRE + " TEXT" +
            ")";

    QuizOperations(DBHelper dbHelper) {
        reableDatabase = dbHelper.getReadableDatabase();
        writableDatabase = dbHelper.getWritableDatabase();
    }


    List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = null;
        String selectQuery = "SELECT * FROM " + TABLE_QUIZ;

        Cursor cursor = reableDatabase.rawQuery(selectQuery, null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                quizzes = new ArrayList<>();
                Log.d("test2", "getAllQuizzes: ");
                do{
                    quizzes.add(fromCursorToQuiz(cursor));
                } while (cursor.moveToNext());
            }
        }
        return quizzes;
    }

    private Quiz fromCursorToQuiz(Cursor cursor) {
        Quiz quiz = new Quiz();
        quiz.setId(cursor.getString(cursor.getColumnIndex(QUIZ_ID)));
        quiz.setQuestion(cursor.getString(cursor.getColumnIndex(QUIZ_QUESTION)));
        quiz.setAnswer1(cursor.getString(cursor.getColumnIndex(QUIZ_ANSWER_1)));
        quiz.setAnswer2(cursor.getString(cursor.getColumnIndex(QUIZ_ANSWER_2)));
        quiz.setAnswer3(cursor.getString(cursor.getColumnIndex(QUIZ_ANSWER_3)));
        quiz.setAnswer4(cursor.getString(cursor.getColumnIndex(QUIZ_ANSWER_4)));
        quiz.setCorrectAnswer(cursor.getString(cursor.getColumnIndex(QUIZ_CORRECT_ANSWER)));
        quiz.setGenre(cursor.getString(cursor.getColumnIndex(QUIZ_GENRE)));
        return quiz;
    }

    void insertQuiz(Quiz quiz) {
        writableDatabase.insert(TABLE_QUIZ, null, contentValuesOfQuiz(quiz));

    }

    private ContentValues contentValuesOfQuiz(Quiz quiz) {
        ContentValues values = new ContentValues();
        values.put(QUIZ_ID, quiz.getId());
        values.put(QUIZ_QUESTION, quiz.getQuestion());
        values.put(QUIZ_ANSWER_1, quiz.getAnswer1());
        values.put(QUIZ_ANSWER_2, quiz.getAnswer2());
        values.put(QUIZ_ANSWER_3, quiz.getAnswer3());
        values.put(QUIZ_ANSWER_4, quiz.getAnswer4());
        values.put(QUIZ_CORRECT_ANSWER, quiz.getCorrectAnswer());
        values.put(QUIZ_GENRE, quiz.getGenre());
        return values;
    }

    void removeQuiz(Quiz quiz) {
        writableDatabase.delete(TABLE_QUIZ, QUIZ_ID + " = " + quiz.getId(), null);
    }

    public boolean updateQuiz(Quiz quiz) {
        return writableDatabase.update(TABLE_QUIZ, contentValuesOfQuiz(quiz), QUIZ_ID + " = ?", new String[]{ quiz.getId() }) > 0;

    }
}
