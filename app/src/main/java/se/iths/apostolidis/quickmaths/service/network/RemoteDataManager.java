package se.iths.apostolidis.quickmaths.service.network;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import se.iths.apostolidis.quickmaths.containers.Quiz;

/**
 * Created by Kakashi on 18/11/17.
 */

public class RemoteDataManager {

    private static RemoteDataManager instance = null;

    private RemoteDataManager(){}

    public static RemoteDataManager getInstance(){
        if(instance == null){
            instance = new RemoteDataManager();
        }
        return instance;
    }

    public void getQuizzes(final QuizCallback callback){
        new HttpRequestTask(
                new HttpRequest("https://quizapp-5e35c.firebaseio.com/.json"),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        List<Quiz> quizzes = null;
                        if (response.code == 200) {
                            JSONArray jsonArray = null;
                            try {
                                jsonArray = new JSONArray(response.body);
                            } catch (JSONException e) {
                                callback.didReceiveError(e.getMessage());
                                e.printStackTrace();
                            }
                            quizzes = new ArrayList<>();
                            for(int i = 0, len = jsonArray.length(); i < len; i++) {
                                try {
                                    quizzes.add(Quiz.fromJSON(((JSONObject)jsonArray.get(i))));

                                } catch (JSONException e) {
                                    callback.didReceiveError(e.getMessage());
                                    e.printStackTrace();
                                }
                            }

                            callback.didReceiveQuizzes(quizzes);

                        }
                    }
                }).execute();
    }

    public void getGames(final GameCallback callback){
        new HttpRequestTask(
                new HttpRequest("https://quickmaths-88869.firebaseio.com/.json"),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        if (response.code == 200) {
                            callback.didReceiveGames(response.body);

                        }
                    }
                }).execute();
    }

}
