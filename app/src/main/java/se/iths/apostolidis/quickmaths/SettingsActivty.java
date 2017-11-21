package se.iths.apostolidis.quickmaths;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import se.iths.apostolidis.quickmaths.service.database.DBHelper;

public class SettingsActivty extends AppCompatActivity {

    private Button changeName;
    private Button changeAvatar;
    private Button connectToFacebook;
    private Button changeNameOk;
    private EditText writeName;
    private TextView textViewTest; //testwidget
    private TextView nameIsBusy;
    private ArrayList<String> names = new ArrayList<>();
    private DBHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_activty);

    changeName = findViewById(R.id.buttonChangeName);
    changeAvatar = findViewById(R.id.buttonChangeAvatar);
    connectToFacebook = findViewById(R.id.buttonConnectToFacebook);
    changeNameOk = findViewById(R.id.buttonChangeNameOk);
    writeName = findViewById(R.id.editTextChangeName);
    textViewTest = findViewById(R.id.textViewTest);
    textViewTest.setText("Player1");
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

    public void onClickChangeAvatar(View view){}





    //TODO:Method for sound.
}
