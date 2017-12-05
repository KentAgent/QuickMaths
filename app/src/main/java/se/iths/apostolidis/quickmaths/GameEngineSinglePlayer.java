package se.iths.apostolidis.quickmaths;

import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by mrx on 2017-11-28.
 */

public class GameEngineSinglePlayer {
    ArrayList<Player> players = new ArrayList<>();

    public void startGame(ArrayList<Player> player){

    }

    public void gameSetUp (ArrayList<Player> players){
        this.players = players;
        Log.d("Wille", "Inside GameEngine");
    }

    public int rollDice() {
        RandomHelper randomHelper = new RandomHelper();
        int random =randomHelper.randomBoundedIndex(5) + 1;
        Log.d("Wille", "Game Engine rollDice : " + random);
        return random;
    }
}
