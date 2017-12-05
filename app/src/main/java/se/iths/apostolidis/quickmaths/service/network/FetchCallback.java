package se.iths.apostolidis.quickmaths.service.network;

import java.util.List;

import se.iths.apostolidis.quickmaths.containers.Quiz;

/**
 * Created by Kakashi on 18/11/17.
 */

public interface FetchCallback {
    void didReceiveData(List<Quiz> quizzes);
    void didReceiveError();
}