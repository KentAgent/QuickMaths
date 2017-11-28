package se.iths.apostolidis.quickmaths.containers;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by simon on 2017-11-28.
 */

public class Screenshot {

    public static Bitmap takeScreenShot(View view){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takeScreenShotOfRootView(View view){
        return takeScreenShot(view.getRootView());
    }
}
