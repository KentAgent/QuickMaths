package se.iths.apostolidis.quickmaths;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

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

    private final Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        startAvatar();

    }

    public void onClickFaceRight(View view) {
        faceCount++;
        if (faceCount > facePictures.length() - 1)
            faceCount = 0;
        face.setImageResource(facePictures.getResourceId(faceCount, 0));
            }

    public void onClickFaceLeft(View view) {
        faceCount--;
        if (faceCount < 0)
            faceCount = facePictures.length()-1;
        face.setImageResource(facePictures.getResourceId(faceCount, 0));
    }


    public void onClickMouthRight(View view) {
        mouthCount++;
        if( mouthCount > mouthPictures.length()-1)
            mouthCount = 0;
        mouth.setImageResource(mouthPictures.getResourceId(mouthCount, 0));
        }

    public void onClickMouthLeft(View view) {
        mouthCount--;
        if( mouthCount < 0)
            mouthCount = mouthPictures.length()-1;
        mouth.setImageResource(mouthPictures.getResourceId(mouthCount, 0));
    }

    public void onClickRightEyeRight(View view) {
        eyeRightCount++;
        if (eyeRightCount > eyeRightPictures.length() - 1)
            eyeRightCount = 0;
        eyeRight.setImageResource(eyeRightPictures.getResourceId(eyeRightCount, 0));
            }

    public void onClickRightEyeLeft(View view) {
        eyeRightCount--;
        if (eyeRightCount < 0)
            eyeRightCount = eyeRightPictures.length()-1;
        eyeRight.setImageResource(eyeRightPictures.getResourceId(eyeRightCount, 0));
    }

    public void onClickLeftEyeRight(View view) {
        eyeLeftCount++;
        if( eyeLeftCount > eyeLeftPictures.length()-1)
            eyeLeftCount = 0;
        eyeLeft.setImageResource(eyeLeftPictures.getResourceId(eyeLeftCount, 0));
            }

    public void onClickLeftEyeLeft(View view) {
        eyeLeftCount--;
        if( eyeLeftCount < 0)
            eyeLeftCount = eyeLeftPictures.length()-1;
        eyeLeft.setImageResource(eyeLeftPictures.getResourceId(eyeLeftCount, 0));
    }

    public void onClickHairRight(View view) {
        hairCount++;
        if( hairCount > hairPictures.length()-1)
            hairCount = 0;
        hair.setImageResource(hairPictures.getResourceId(hairCount, 0));
            }

    public void onClickHairLeft(View view) {
        hairCount--;
        if( hairCount < 0)
            hairCount = hairPictures.length()-1;
        hair.setImageResource(hairPictures.getResourceId(hairCount, 0));
    }
    public void onClickNoseRight(View view) {
        noseCount++;
        if( noseCount > nosePictures.length()-1)
            noseCount = 0;
        nose.setImageResource(nosePictures.getResourceId(noseCount, 0));
            }

    public void onClickNoseLeft(View view) {
        noseCount--;
        if( noseCount < 0)
            noseCount = nosePictures.length()-1;
        nose.setImageResource(nosePictures.getResourceId(noseCount, 0));
    }

    public void onClickAboutActivity(View view) {
        finish();
    }

    public void onClickPreview(View view) {

    }

    public void startAvatar () {
        hairPictures = getResources().obtainTypedArray(R.array.hair);
        hair = findViewById(R.id.imageViewHair);
        hairCount = random.nextInt(hairPictures.length());
        hair.setImageResource(hairPictures.getResourceId(hairCount, 0));

        facePictures = getResources().obtainTypedArray(R.array.face);
        face = findViewById(R.id.imageViewFace);
        faceCount = random.nextInt(facePictures.length());
        face.setImageResource(facePictures.getResourceId(faceCount, 0));

        eyeLeftPictures = getResources().obtainTypedArray(R.array.eyeleft);
        eyeLeft = findViewById(R.id.imageViewLeftEye);
        eyeLeftCount = random.nextInt(eyeLeftPictures.length());
        eyeLeft.setImageResource(eyeLeftPictures.getResourceId(eyeLeftCount, 0));

        eyeRightPictures = getResources().obtainTypedArray(R.array.eyeright);
        eyeRight = findViewById(R.id.imageViewRightEye);
        eyeRight.setImageResource(eyeRightPictures.getResourceId(eyeLeftCount, 0));

        nosePictures = getResources().obtainTypedArray(R.array.nose);
        nose = findViewById(R.id.imageViewNose);
        noseCount = random.nextInt(nosePictures.length());
        nose.setImageResource(nosePictures.getResourceId(noseCount, 0));

        mouthPictures = getResources().obtainTypedArray(R.array.mouth);
        mouth = findViewById(R.id.imageViewMouth);
        mouthCount = random.nextInt(mouthPictures.length());
        mouth.setImageResource(mouthPictures.getResourceId(mouthCount, 0));
    }

    public void onClickRandom(View view) {

        hairCount = random.nextInt(hairPictures.length());
        hair.setImageResource(hairPictures.getResourceId(hairCount, 0));

        faceCount = random.nextInt(facePictures.length());
        face.setImageResource(facePictures.getResourceId(faceCount, 0));

        eyeLeftCount = random.nextInt(eyeLeftPictures.length());
        eyeLeft.setImageResource(eyeLeftPictures.getResourceId(eyeLeftCount, 0));

        eyeRightCount = random.nextInt(eyeRightPictures.length());
        eyeRight.setImageResource(eyeRightPictures.getResourceId(eyeRightCount, 0));

        noseCount = random.nextInt(nosePictures.length());
        nose.setImageResource(nosePictures.getResourceId(noseCount, 0));

        mouthCount = random.nextInt(mouthPictures.length());
        mouth.setImageResource(mouthPictures.getResourceId(mouthCount, 0));
    }

}