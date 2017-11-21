package se.iths.apostolidis.quickmaths;

import java.util.Random;

/**
 * Created by mrx on 2017-11-15.
 */

public class RandomHelper {
    private Random random = new Random();

    public int randomBoundedIndex (int bound){
        int index = random.nextInt(bound);
        return index;
    }
}
