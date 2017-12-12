package se.iths.apostolidis.quickmaths;

import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mrx on 2017-11-28.
 */

public class Player {

    public String name = "";
    private String uid;
    private int ammountOfTurns;

    public int getAmmountOfTurns() {
        return ammountOfTurns;
    }

    public void setAmmountOfTurns(int ammountOfTurns) {
        this.ammountOfTurns = ammountOfTurns;
    }

    private int gold, score;
    private int posX, posY;
    private int lastThrownDie;
    private int coordinateIndex;
    private Bitmap avatar;
    private FirebaseUser fbUser;


    public Player(){

    }

    public Player(FirebaseUser user) {
        this.fbUser = user;

    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Player(int coordinateIndex, int gold, int lastThrownDie, String name, int posX, int posY, int score, String uid) {
        this.name = name;
        this.uid = uid;
        this.gold = gold;
        this.score = score;
        this.posX = posX;
        this.posY = posY;
        this.lastThrownDie = lastThrownDie;
        this.coordinateIndex = coordinateIndex;
    }

    public FirebaseUser getFbUser() {
        return fbUser;
    }

    public void setFbUser(FirebaseUser fbUser) {
        this.fbUser = fbUser;
    }

    public int getLastThrownDie() {
        return lastThrownDie;
    }

    public void setLastThrownDie(int lastThrownDie) {
        this.lastThrownDie = lastThrownDie;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int getCoordinateIndex() {
        return coordinateIndex;
    }

    public void setCoordinateIndex(int coordinateIndex) {
        this.coordinateIndex = coordinateIndex;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPosX() {
        return posX;
    }


    public int getPosY() {
        return posY;
    }

    public void setPos(int posX, int posY) {
        this.posY = posY;
        this.posX = posX;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

}
