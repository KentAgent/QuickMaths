package se.iths.apostolidis.quickmaths;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class CopyDbActivity extends AppCompatActivity {

    Cursor c = null;
    Button copyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_database);
        copyButton =  findViewById(R.id.buttonCopyDatabase);
    }
}

