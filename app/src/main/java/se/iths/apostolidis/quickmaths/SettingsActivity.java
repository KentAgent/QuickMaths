package se.iths.apostolidis.quickmaths;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import se.iths.apostolidis.quickmaths.service.database.DBHelper;

public class SettingsActivity extends AppCompatActivity {

    private Button changeName;
    private Button changeAvatar;
    private Button changeNameOk;
    private EditText writeName;
    private TextView textViewTest; //testwidget
    private TextView nameIsBusy;
    private ArrayList<String> names = new ArrayList<>();
    private DBHelper database;
    private ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        changeName = findViewById(R.id.buttonChangeName);
        changeAvatar = findViewById(R.id.buttonChangeAvatar);
        changeNameOk = findViewById(R.id.buttonChangeNameOk);
        writeName = findViewById(R.id.editTextChangeName);
        textViewTest = findViewById(R.id.textViewTest);
        back = findViewById(R.id.imageButtonBack);

        writeName.setHint(textViewTest.getText());
        nameIsBusy = findViewById(R.id.textViewNameNotAvaliable);

        database = DBHelper.getInstance(this);
        names.add("Benny");
        names.add("Karl");
        names.add("Lisa");

    }

    public void onClickChangeName(View view){
        if(writeName.getVisibility() == View.INVISIBLE) {
            writeName.setVisibility(View.VISIBLE);
            changeNameOk.setVisibility(View.VISIBLE);
            textViewTest.setText(writeName.getText());

        }
        else{
            writeName.setVisibility(View.INVISIBLE);
            changeNameOk.setVisibility(View.INVISIBLE);
        }
    }

    public void onClickChangeNameOk(View view){
        boolean isNotEqual = true;
        for (int i = 0; i < names.size(); i++) {
            if(writeName.getText().toString().equals(names.get(i))){
                writeName.setVisibility(View.VISIBLE);
                changeNameOk.setVisibility(View.VISIBLE);
                isNotEqual = false;
                nameIsBusy.setVisibility(View.VISIBLE);
            }

        }
        if(isNotEqual) {
            textViewTest.setText(writeName.getText());//TODO Spara i db.tabell(player).kolumn(namn)
            writeName.setVisibility(View.INVISIBLE);
            changeNameOk.setVisibility(View.INVISIBLE);
            nameIsBusy.setVisibility(View.INVISIBLE);
        }
    }

    //TODO:Change intent to startActivityForResult and get avatar info instead of startActivity();
    public void onClickChangeAvatar(View view){
        Intent intent = new Intent(this, AvatarActivity.class);
        startActivity(intent);
    }

    public void onClickBack(View view) {finish();}





    //TODO:Method for sound.
}
