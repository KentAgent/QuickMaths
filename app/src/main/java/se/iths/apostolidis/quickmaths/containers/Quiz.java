package se.iths.apostolidis.quickmaths.containers;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kakashi on 18/11/17.
 */

public class Quiz {

    private String id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;
    private String genre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public static Quiz fromJSON(JSONObject jsonObject) {
        Quiz quiz = new Quiz();
        try {
            quiz.setId(jsonObject.getString("_id"));
            quiz.setQuestion(jsonObject.getString("question"));
            quiz.setAnswer1(jsonObject.getString("answer1"));
            quiz.setAnswer2(jsonObject.getString("answer2"));
            quiz.setAnswer3(jsonObject.getString("answer3"));
            quiz.setAnswer4(jsonObject.getString("answer4"));
            quiz.setCorrectAnswer(jsonObject.getString("correctanswer"));
            quiz.setGenre(jsonObject.getString("genre"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return quiz;
    }
}
