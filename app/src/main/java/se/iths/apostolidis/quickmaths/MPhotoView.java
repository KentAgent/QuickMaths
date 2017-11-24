package se.iths.apostolidis.quickmaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by Kakashi on 24/11/17.
 */

public class MPhotoView extends PhotoView {
    public MPhotoView(Context context) {
        super(context);
    }
    public MPhotoView(Context context, AttributeSet attr) {
        super(context, attr);
    }
    public MPhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }
    public MPhotoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.setMatrix(this.getAttacher().getImageMatrix());
        canvas.drawRect(new Rect(100,100,200,200), new Paint(Color.BLUE));
        canvas.restore();
    }
}
