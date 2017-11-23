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

    private ImageView eyes;
    private TypedArray eyesPictures;
    private int eyesCount = 0;

    private ImageView mouth;
    private TypedArray mouthPictures;
    private int mouthCount = 0;

    private ImageView hair;
    private TypedArray hairPictures;
    private int hairCount = 0;

    private ImageView mouthAccesory;
    private TypedArray mouthAccesoryPictures;
    private int mouthAccesoryCount = 0;

    private ImageView scar;
    private TypedArray scarPictures;
    private int scarCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);


        facePictures = getResources().obtainTypedArray(R.array.face);
        face = findViewById(R.id.imageViewFace);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceCount++;
                if( faceCount > facePictures.length()-1)
                    faceCount = 0;
                face.setImageResource(facePictures.getResourceId(faceCount, 0));
            }
        });

        eyesPictures = getResources().obtainTypedArray(R.array.eyes);
        eyes = findViewById(R.id.imageViewEyes);
        eyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eyesCount++;
                if( eyesCount > eyesPictures.length()-1)
                    eyesCount = 0;
                eyes.setImageResource(eyesPictures.getResourceId(eyesCount, 0));
            }
        });

        mouthPictures = getResources().obtainTypedArray(R.array.mouth);
        mouth = findViewById(R.id.imageViewMouth);
        mouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mouthCount++;
                if( mouthCount > mouthPictures.length()-1)
                    mouthCount = 0;
                mouth.setImageResource(mouthPictures.getResourceId(mouthCount, 0));
            }
        });

        hairPictures = getResources().obtainTypedArray(R.array.hair);
        hair = findViewById(R.id.imageViewHair);
        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hairCount++;
                if( hairCount > hairPictures.length()-1)
                    hairCount = 0;
                hair.setImageResource(hairPictures.getResourceId(hairCount, 0));
            }
        });

        mouthAccesoryPictures = getResources().obtainTypedArray(R.array.mouthaccesory);
        mouthAccesory = findViewById(R.id.imageViewMouthAccesory);
        mouthAccesory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mouthAccesoryCount++;
                if( mouthAccesoryCount > mouthAccesoryPictures.length()-1)
                    mouthAccesoryCount = 0;
                mouthAccesory.setImageResource(mouthAccesoryPictures.getResourceId(mouthAccesoryCount, 0));
            }
        });

        scarPictures = getResources().obtainTypedArray(R.array.scar);
        scar = findViewById(R.id.imageViewNose);
        scar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scarCount++;
                if( scarCount > scarPictures.length()-1)
                    scarCount = 0;
                scar.setImageResource(scarPictures.getResourceId(scarCount, 0));
            }
        });
    }

    public void aboutButton (View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}