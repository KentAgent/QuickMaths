package se.iths.apostolidis.quickmaths;

import com.apptakk.http_request.HttpRequest;
import com.apptakk.http_request.HttpRequestTask;
import com.apptakk.http_request.HttpResponse;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mrx on 2017-11-15.
 */

public class GameEngine {


    public static void rollDice() {

    }

    public static void answerQuestion() {


    }

    public static void endturn(int x, int y, FirebaseUser user) {
        new HttpRequestTask(
                new HttpRequest("https://quickmaths-88869.firebaseio.com/games/" + x + "/players/" + y, HttpRequest.POST, "{ \"turnToPlay\": \"false\" }" ),
                new HttpRequest.Handler() {
                    @Override
                    public void response(HttpResponse response) {
                        if (response.code == 200) {
                        }
                    }
                }).execute();


    }
}
