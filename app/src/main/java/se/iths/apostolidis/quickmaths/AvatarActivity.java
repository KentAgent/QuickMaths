package se.iths.apostolidis.quickmaths;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class AvatarActivity extends AppCompatActivity {

    private ImageView imageView;
    private TypedArray pictures;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        //Load arrays of resourceIds from arrays.xml
        pictures = getResources().obtainTypedArray(R.array.face);

        imageView = findViewById(R.id.imageViewGubbe);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if( i > pictures.length()-1)
                    i = 0;
                imageView.setImageResource(pictures.getResourceId(i, 0));
            }
        });
    }
}