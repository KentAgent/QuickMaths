package se.iths.apostolidis.quickmaths;

import java.util.Map;

/**
 * Created by Kakashi on 08/12/17.
 */

public class LobbyClass {
    public String lobbyName;
    Map<String, Boolean> players;


    public LobbyClass(String lobbyName, Map<String,Boolean> players){
        this.lobbyName = lobbyName;
        this.players = players;

    }


}
