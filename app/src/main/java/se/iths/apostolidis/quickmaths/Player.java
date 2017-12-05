package se.iths.apostolidis.quickmaths;

import android.graphics.Bitmap;

/**
 * Created by mrx on 2017-11-28.
 */

public class Player {

    private String id;
    private String name;
    private int gold;
    private int posX, posY;
    private Bitmap avatar;


    public int getCoordinateIndex() {
        return coordinateIndex;
    }

    public void setCoordinateIndex(int coordinateIndex) {
        this.coordinateIndex = coordinateIndex;
    }

    private int coordinateIndex;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
