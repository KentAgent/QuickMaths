package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class AvatarActivity extends AppCompatActivity {

    private ImageView face;
    private TypedArray facePictures;
    private int faceCount = 0;

    private ImageView eyeRight;
    private TypedArray eyeRightPictures;
    private int eyeRightCount = 0;

    private ImageView mouth;
    private TypedArray mouthPictures;
    private int mouthCount = 0;

    private ImageView hair;
    private TypedArray hairPictures;
    private int hairCount = 0;

    private ImageView eyeLeft;
    private TypedArray eyeLeftPictures;
    private int eyeLeftCount = 0;

    private ImageView nose;
    private TypedArray nosePictures;
    private int noseCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);


        facePictures = getResources().obtainTypedArray(R.array.face);
        face = findViewById(R.id.imageViewFace);

        eyeRightPictures = getResources().obtainTypedArray(R.array.righteye);
        eyeRight = findViewById(R.id.imageViewRightEye);

        mouthPictures = getResources().obtainTypedArray(R.array.mouth);
        mouth = findViewById(R.id.imageViewMouth);

        hairPictures = getResources().obtainTypedArray(R.array.hair);
        hair = findViewById(R.id.imageViewHair);

        eyeLeftPictures = getResources().obtainTypedArray(R.array.lefteye);
        eyeLeft = findViewById(R.id.imageViewLeftEye);

        nosePictures = getResources().obtainTypedArray(R.array.nose);
        nose = findViewById(R.id.imageViewNose);
    }

    public void faceButtonRight (View view) {
        faceCount++;
        if (faceCount > facePictures.length() - 1)
            faceCount = 0;
        face.setImageResource(facePictures.getResourceId(faceCount, 0));
            }

    public void faceButtonLeft (View view) {
        faceCount--;
        if (faceCount < 0)
            faceCount = facePictures.length()-1;
        face.setImageResource(facePictures.getResourceId(faceCount, 0));
    }


    public void mouthButtonRight (View view) {
        mouthCount++;
        if( mouthCount > mouthPictures.length()-1)
            mouthCount = 0;
        mouth.setImageResource(mouthPictures.getResourceId(mouthCount, 0));
        }

    public void mouthButtonLeft (View view) {
        mouthCount--;
        if( mouthCount < 0)
            mouthCount = mouthPictures.length()-1;
        mouth.setImageResource(mouthPictures.getResourceId(mouthCount, 0));
    }

    public void rightEyeButtonRight (View view) {
        eyeRightCount++;
        if (eyeRightCount > eyeRightPictures.length() - 1)
            eyeRightCount = 0;
        eyeRight.setImageResource(eyeRightPictures.getResourceId(eyeRightCount, 0));
            }

    public void rightEyeButtonLeft (View view) {
        eyeRightCount--;
        if (eyeRightCount < 0)
            eyeRightCount = eyeRightPictures.length()-1;
        eyeRight.setImageResource(eyeRightPictures.getResourceId(eyeRightCount, 0));
    }

    public void leftEyeButtonRight (View view) {
        eyeLeftCount++;
        if( eyeLeftCount > eyeLeftPictures.length()-1)
            eyeLeftCount = 0;
        eyeLeft.setImageResource(eyeLeftPictures.getResourceId(eyeLeftCount, 0));
            }

    public void leftEyeButtonLeft (View view) {
        eyeLeftCount--;
        if( eyeLeftCount < 0)
            eyeLeftCount = eyeLeftPictures.length()-1;
        eyeLeft.setImageResource(eyeLeftPictures.getResourceId(eyeLeftCount, 0));
    }

    public void hairButtonRight (View view) {
        hairCount++;
        if( hairCount > hairPictures.length()-1)
            hairCount = 0;
        hair.setImageResource(hairPictures.getResourceId(hairCount, 0));
            }

    public void hairButtonLeft (View view) {
        hairCount--;
        if( hairCount < 0)
            hairCount = hairPictures.length()-1;
        hair.setImageResource(hairPictures.getResourceId(hairCount, 0));
    }
    public void noseButtonRight (View view) {
        noseCount++;
        if( noseCount > nosePictures.length()-1)
            noseCount = 0;
        nose.setImageResource(nosePictures.getResourceId(noseCount, 0));
            }

    public void noseButtonLeft (View view) {
        noseCount--;
        if( noseCount < 0)
            noseCount = nosePictures.length()-1;
        nose.setImageResource(nosePictures.getResourceId(noseCount, 0));
    }

    public void backButton (View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void previewButton (View view) {

    }



}