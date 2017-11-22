package se.iths.apostolidis.quickmaths;

import se.iths.apostolidis.quickmaths.service.database.DBHelper;

/**
 * Created by mrx on 2017-11-15.
 */

public class QuestionEngine {

    public QuestionEngine(){

    }

    private RandomHelper randomHelper = new RandomHelper();

    private String playerGuess;
    private String answerChoise1;
    private String answerChoise2;
    private String answerChoise3;
    private String answerChoise4;
    private String correctAnswer;
    private String questionText;

    public QuestionEngine(String answerChoise1, String answerChoise2, String answerChoise3, String answerChoise4, String correctAnswer, String questionText) {
        this.answerChoise1 = answerChoise1;
        this.answerChoise2 = answerChoise2;
        this.answerChoise3 = answerChoise3;
        this.answerChoise4 = answerChoise4;
        this.correctAnswer = correctAnswer;
        this.questionText = questionText;
    }

    public String getQuestion (){
        return questionText;
    }
    public String getAnswerChoise1 (){
        return answerChoise1;
    }
    public String getAnswerChoise2 (){
        return answerChoise2;
    }
    public String getAnswerChoise3 (){
        return answerChoise3;
    }
    public String getAnswerChoise4 (){
        return answerChoise4;
    }
    public String getCorrectAnswer (){
        return correctAnswer;
    }

    public boolean onClickAnswer1(){
        if (answerChoise1.equals(correctAnswer)) {
            return true;
        }
        else return false;
    }
    public boolean onClickAnswer2(){
        if (answerChoise2.equals(correctAnswer)) {
            return true;
        }
        else return false;
    }
    public boolean onClickAnswer3(){
        if (answerChoise3.equals(correctAnswer)) {
            return true;
        }
        else return false;
    }
    public boolean onClickAnswer4(){
        if (answerChoise4.equals(correctAnswer)) {
            return true;
        }
        else return false;
    }
}
