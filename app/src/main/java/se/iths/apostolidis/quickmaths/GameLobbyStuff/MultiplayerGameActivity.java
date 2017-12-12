package se.iths.apostolidis.quickmaths.GameLobbyStuff;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.iths.apostolidis.quickmaths.GameEngineSinglePlayer;
import se.iths.apostolidis.quickmaths.MPhotoView;
import se.iths.apostolidis.quickmaths.Player;
import se.iths.apostolidis.quickmaths.QuestionActivity;
import se.iths.apostolidis.quickmaths.R;
import se.iths.apostolidis.quickmaths.RandomHelper;

public class MultiplayerGameActivity extends AppCompatActivity {

    private ImageView die;
    Button buttonRollDice;
    private TextView turnTracker;
    public MPhotoView gridMPhotoView;
    MPhotoView map;
    private static int numOfCoordinates = 37;
    private Point[] assetCoordinates = new Point[numOfCoordinates];
    GameEngineSinglePlayer engine;
    HashMap<String, Bitmap> hashMapAssets;
    List<String> randomCategoryStrings;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<String> playerUids = new ArrayList<>();
    private ArrayList<String> playerNames = new ArrayList<>();
    private int numberOfPlayer;
    private String scoreBoard1;
    private String scoreBoard2;
    TextView textViewScoreBoard;
    TextView textViewScoreBoardExtra;
    List<String> chosenCategories;
    private static RandomHelper randomHelper = new RandomHelper();
    private Bitmap gridBitmap;
    private Bitmap originalMapBitmap;
    private Paint paint = new Paint();
    Canvas canvas;
    private int playerTurnIndex;
    private String TAG = "Wille";
    private Player player = new Player();

    ChildEventListener mChildEventListener;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseUser user;
    private DatabaseReference mMessagesDatabaseRefrence;
    private FirebaseAuth mFirebaseAuth;
    String lobbyID;


    public MultiplayerGameActivity(){


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        map = findViewById(R.id.photo_view);
        gridMPhotoView = findViewById(R.id.photo_viewGrid);
        //map.setImageResource(R.mipmap.gamemap);
        //map.setImageBitmap(setMap());
        turnTracker = findViewById(R.id.textViewTurnTracker);
        buttonRollDice = findViewById(R.id.buttonRollDice);
        textViewScoreBoard = findViewById(R.id.textViewScoreBoard);
        textViewScoreBoardExtra = findViewById(R.id.textViewScoreBoardExtra);
        assetCoordinates = new Point[numOfCoordinates];
        setAssetPosList(assetCoordinates);
        chosenCategories = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mMessagesDatabaseRefrence = mFirebaseDatabase.getReference();
        user = mFirebaseAuth.getCurrentUser();
      //  mMessagesDatabaseRefrence = mFirebaseDatabase.getReference();





        //Bundle bundle = getIntent().getExtras();

        //if (bundle.getStringArrayList(("chosenCategories")) != null) {
        //chosenCategories = bundle.getStringArrayList("chosenCategories");
        //} else


        onlineSetUp();
        numberOfPlayer = playerUids.size();




        paint.setColor(Color.BLACK);  //TODO Använda i offline?

        die = findViewById(R.id.imageViewDie);
        die.setVisibility(View.INVISIBLE);

        randomCategoryStrings = listOfGenres();
        hashMapAssets = pairHashmapWithKey();


        //randomAssets = setRandomAssets();

        engine = new GameEngineSinglePlayer();


        //map.setScaleType(PhotoView.ScaleType.FIT_XY);
        //map.setImageBitmap(setMap(assetCoordinates));
        Log.d("Wille", "Map width size: " + map.getWidth());
        Log.d("Wille", "Map height size: " + map.getHeight());


        clearMap();
        gameSetUp(gridMPhotoView);
        updateScoreBoard();
        attachDB();
    }


    private void onlineSetUp() {
        setOnlineCateGories();

        Bundle bundle = getIntent().getExtras();
        lobbyID = bundle.getString("LobbyID");
        Log.d("hund", "Lobby id: " + lobbyID);
        playerUids = bundle.getStringArrayList("PlayerUids");
        playerNames = bundle.getStringArrayList("PlayerNames");

        for (int i = 0; i < playerNames.size(); i++) {
            Log.d("Hund", "player " + i + ": " + playerNames.get(i));

        }
        for (int i = 0; i < playerUids.size(); i++) {
            Log.d("Hund", "player " + i + ": " + playerUids.get(i));

        }

        for (int i = 0; i < chosenCategories.size(); i++) {
            Log.d("Hund", chosenCategories.get(i));
        }
    }

    private void setOnlineCateGories() {
        chosenCategories.add("E-Sport");
        chosenCategories.add("Musik");
        chosenCategories.add("Sport");
        chosenCategories.add("Random");
        chosenCategories.add("Film");
        chosenCategories.add("Vetenskap");
        chosenCategories.add("Humor");
    }


    private void clearMap() {

//        gridBitmap = originalMapBitmap.copy(originalMapBitmap.getConfig(), true);


        gridBitmap = setMap(assetCoordinates);
        originalMapBitmap = gridBitmap.copy(gridBitmap.getConfig(), true);

        //gridBitmap.copy(originalMapBitmap.getConfig(), true);
        //gridBitmap = originalMapBitmap.copy(originalMapBitmap.getConfig(), true);
        //gridMPhotoView.setBack
        gridMPhotoView.setImageBitmap(gridBitmap);
        //gridMPhotoView.setScaleType(ImageView.ScaleType.FIT_XY);
        canvas = new Canvas(gridBitmap);
    }


    private void clearPlayers() {
        gridBitmap = originalMapBitmap.copy(originalMapBitmap.getConfig(), true);
        gridMPhotoView.setImageBitmap(gridBitmap);
        //gridMPhotoView.setScaleType(ImageView.ScaleType.FIT_XY);
        canvas = new Canvas(gridBitmap);

    }


    public void upDate(ArrayList<Player> players) {
        Map<String, Object> users = new HashMap<>();
        users.put(String.valueOf(players.get(0).getCoordinateIndex()), players.get(0));

        mMessagesDatabaseRefrence.child("Lobbies").child(lobbyID).child(playerUids.get(0));
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("yo", "onChildAdded: " + dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mMessagesDatabaseRefrence.child("Lobbies").child(lobbyID).addChildEventListener(mChildEventListener);
      //  mMessagesDatabaseRefrence.child("Lobbies").child("gg").child(user.getUid()).child("coordinateIndex").setValue("hejhejhje");
        draw(players);
    }


    public void draw(final ArrayList<Player> players) {
        Map<String, Object> users = new HashMap<>();
       // users.put(player.getUid(), player);
        clearPlayers();
        scoreBoards();



        Log.d("kakan", "robin :"+ playerUids.get(0));
        Log.d("kakan", "wille :" + playerUids.get(1));
        Log.d("kakan", "3: " +mMessagesDatabaseRefrence.child("Lobbies").child(lobbyID).child("xpFrFG3RYigTGCZihA8vlfNAZJu2").getKey());

        for (int i = 0; i < players.size(); i++) {

            players.get(i).setPos(assetCoordinates[players.get(i).getCoordinateIndex()].x, assetCoordinates[players.get(i).getCoordinateIndex()].y);

            canvas.save();
            canvas.drawBitmap(players.get(i).getAvatar(), players.get(i).getPosX() + (i * 50), players.get(i).getPosY(), null);
            canvas.restore();

        }
    }


    public void attachDB() {


        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                dataSnapshot.getValue();

                dataSnapshot.child("coordinateIndex").getRef().getKey().equals(player.getCoordinateIndex());


                Log.d("bror", "DATASNAPSHOT!: " + dataSnapshot.getKey());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals("ammountOfTurns")){
                    players.get(1).setAmmountOfTurns(((Long) dataSnapshot.getValue()).intValue());
                    Log.d("bror", "Player AMOUNTOFTURNS: " + players.get(1).getAmmountOfTurns());
                } else if (dataSnapshot.getKey().equals("coordinateIndex")){
                    players.get(1).setCoordinateIndex(((Long) dataSnapshot.getValue()).intValue());
                    players.get(1).setScore(players.get(1).getScore() + 3);
                    Log.d("Wille", "Player coordinateIndex: " + players.get(1).getCoordinateIndex());
                }

                draw(players);
                Log.d("bror1", "ChildChanged Datasnapshot " + dataSnapshot.getValue());
                Log.d("Bror2", "Player 1 pos :" + players.get(1).getCoordinateIndex());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        };
        //mMessagesDatabaseRefrence.child("Lobbies").child(lobbyID).child(playerNames.get(1)).child("coordinateIndex").addChildEventListener(mChildEventListener);
        mMessagesDatabaseRefrence.child("Lobbies").child(lobbyID).child(playerUids.get(0)).addChildEventListener(mChildEventListener);

    }

    public void gameSetUp(MPhotoView view) {

        playerTurnIndex = 0;
        turnTracker.setVisibility(View.GONE);
        for (int i = 0; i < numberOfPlayer; i++) {
            Player player = setPlayer(players, i);
            //RectF avatarRect = new RectF();
            //avatarRect.set(player.getPosX(), player.getPosY(), 200, 200);
            canvas.save();
            canvas.drawBitmap(player.getAvatar(), player.getPosX(), player.getPosY(), null);
            canvas.restore();
          //  Log.d("Wille", "Player id: " + String.valueOf(player.getId()));
        }
        playerTurnIndex = 0;
        players.get(0).setName(user.getDisplayName());
        //startGame(players);
    }


    public void playerTurn(Player player) {
        player.setLastThrownDie(rollDice());
        rollDieAnimation();
        movePlayer(player, player.getLastThrownDie());



        String category = randomCategoryStrings.get(player.getCoordinateIndex());
        getQuestion(category);
        players.get(0).setAmmountOfTurns((players.get(0).getAmmountOfTurns()) + 1);
        mMessagesDatabaseRefrence.child("Lobbies").child(lobbyID).child(user.getUid()).child("ammountOfTurns").setValue(players.get(0).getAmmountOfTurns());
    }


    public void onClickButtonRollDice(View view) {
        upDate(players);
        playerTurn(players.get(playerTurnIndex));
    }


    private int rollDice() {
        return engine.rollDice();
    }


    private void getQuestion(String category) {

        // Intent intent = new Intent(this, QuestionActivity.class);

        //startActivityForResult(intent, 1);
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("Category", category);
        startActivityForResult(intent, 1);


        //finish()

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                players.get(playerTurnIndex).addScore(3);
                mMessagesDatabaseRefrence.child("Lobbies").child(lobbyID).child(user.getUid()).child("coordinateIndex").setValue(players.get(0).getCoordinateIndex());
                updateScoreBoard();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                moveBackPlayer();
            }
        }
        //playerTurnIndex++;
        if (playerTurnIndex == numberOfPlayer) {
            playerTurnIndex = 0;
        }

        die.setVisibility(View.INVISIBLE);
        turnTracker.setVisibility(View.VISIBLE);

        turnTracker.setText("Player " + (playerTurnIndex + 1) + " turn to play");
        turnTracker.postDelayed(new Runnable() {
            @Override
            public void run() {
                turnTracker.setVisibility(View.INVISIBLE);

            }
        }, 3000);


    }


    private void moveBackPlayer() {

        for (int i = 0; i < players.get(playerTurnIndex).getLastThrownDie(); i++) {

            players.get(playerTurnIndex).setPos(assetCoordinates[players.get(playerTurnIndex).getCoordinateIndex() - 1].x, assetCoordinates[players.get(playerTurnIndex).getCoordinateIndex() - 1].y);
            players.get(playerTurnIndex).setCoordinateIndex(players.get(playerTurnIndex).getCoordinateIndex() - 1);

            Log.d("Wille", "Player Coordinate X :" + players.get(playerTurnIndex).getPosX());


        }

        upDate(players);
    }


    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                Boolean b = extras.getBoolean("Result");
//                Log.d("hund", "ANDRA IFEN!: " + b);
//                if (b) {
//                    Log.d("Wille", "VINNER MASSA CASH!!!");
//                    //Stå kvar på din plats
//                    players.get(playerTurnIndex).addScore(3);
//                    updateScoreBoard();
//                } else {
//                    //Gå tillbak
//
//                }
//            }
//        }
//    }


    private void movePlayer(Player player, int steps) {

        if ((steps + player.getCoordinateIndex()) > assetCoordinates.length){
            steps = assetCoordinates.length - player.getCoordinateIndex();
            player.setLastThrownDie(steps);
        }

        for (int i = 0; i < steps; i++) {

            player.setPos(assetCoordinates[player.getCoordinateIndex() + 1].x, assetCoordinates[player.getCoordinateIndex() + 1].y);
            player.setCoordinateIndex(player.getCoordinateIndex() + 1);
            Log.d("Wille", "Player Coordinate X :" + player.getPosX() + " Y : " + player.getPosY());

            if (player.getCoordinateIndex() >= assetCoordinates.length) {
                wonGame();
                break;
            }
        }

        upDate(players);
    }


    private void wonGame() {
        //Intent myIntent = new Intent(this, WinnerActiv7ity.class);
        // startActivity(myIntent);

        finish();

    }


    @NonNull
    private Player setPlayer(ArrayList<Player> players, int i) {
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.player1);
        Bitmap scaledAvatar = Bitmap.createScaledBitmap(avatar, 200, 100, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();


        Player player = new Player();

        player.setUid(playerUids.get(playerTurnIndex));
        player.setName(playerNames.get(playerTurnIndex));
        //player.setId();
        player.setCoordinateIndex(0);
        player.setPos(assetCoordinates[0].x, assetCoordinates[0].y);
        player.setAvatar(scaledAvatar);
        players.add(player);
        playerTurnIndex++;
        return player;
    }


    public void updateScoreBoard() {
        //textViewScoreBoard.setText(scoreBoardSetup());
        scoreBoards();
        //textViewScoreBoard.setText(players.get(0).getName());
        //textViewScoreBoardExtra.setText(players.get(1).getName());
    }


    public String scoreBoardSetup() {
        String scoreBoard = "";
        for (int i = 0; i < players.size(); i++) {
            scoreBoard += "Player " + (i + 1) + " Score: " + players.get(i).getScore();
            if (i < players.size() - 1)
                scoreBoard += "\n";
        }
        return scoreBoard;
    }

    public void scoreBoards() {
        scoreBoard2 = players.get(1).getName();
        String scoreBoard1 = " ";
        for (int i = 0; i < players.size(); i++) {

            scoreBoard1 += " " + playerNames.get(i) + " " + players.get(i).getScore();
            textViewScoreBoard.setText(scoreBoard1);
            if (i < 1)
                scoreBoard1 += "\n";
            if (i >= 1) {
                textViewScoreBoardExtra.setVisibility(View.VISIBLE);
                scoreBoard2 += "Player " + (i + 1) + " Score: " + players.get(i).getScore();
                if (i < 3)
                    scoreBoard2 += "\n";
            }
        }
    }


    public Bitmap setMap(Point[] assetCoordinates) {

        Bitmap backgroundImage = BitmapFactory.decodeResource(getResources(), R.mipmap.gameboard_transparent);
        Bitmap mergedCoordinateAssets = backgroundImage;
        Bitmap previousImage;
        Bitmap scaledCoordinateAsset = backgroundImage;
        for (int i = 0; i < assetCoordinates.length; i++) {

            if (i == 0) {
                previousImage = backgroundImage;
            } else {
                previousImage = mergedCoordinateAssets;
            }

            //Bitmap coordinateAsset = BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_sport);
            //Bitmap coordinateAsset = randomAssets.get(i).getAsset().copy(randomAssets.get(i).getAsset().getConfig(), true);
            Log.d("Wille", "Setmap Loop");
            Bitmap coordinateAsset = hashMapAssets.get(randomCategoryStrings.get(i));


            //coordinateAsset = randomAssets.get(i).getAsset();
            scaledCoordinateAsset = Bitmap.createScaledBitmap(coordinateAsset, 50, 50, false);
            Log.d("Wille", "X value: " + assetCoordinates[i].x);

            mergedCoordinateAssets = mergeImages(previousImage, scaledCoordinateAsset, 0, 0, assetCoordinates[i].x + 20, assetCoordinates[i].y + 20);
        }

        Log.d("Wille", "Bigmerge height: " + mergedCoordinateAssets.getHeight());
        return mergedCoordinateAssets;
    }


    private Bitmap mergeImages(Bitmap firstImage, Bitmap secondImage, int firstImageX, int firstImageY, int secondImageX, int secondImageY) {
        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, firstImageX, firstImageY, null);
        canvas.drawBitmap(secondImage, secondImageX, secondImageY, null);
        return result;
    }


    public HashMap pairHashmapWithKey() {
        HashMap<String, Bitmap> hashMapAssets = new HashMap();
        hashMapAssets.put("E-Sport", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_games));
        hashMapAssets.put("Musik", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_culture_music));
        hashMapAssets.put("Sport", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_sport));
        hashMapAssets.put("Random", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_random));
        hashMapAssets.put("Film", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_maths));
        hashMapAssets.put("Vetenskap", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_sience));
        hashMapAssets.put("Humor", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_nature));
        //hashMapAssets.put("Maths", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_maths));
        //hashMapAssets.put("Science", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_sience));
        //hashMapAssets.put("Geography", BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_geography));

        HashMap<String, Bitmap> chosenHashMapAssets = new HashMap<>();

        for (int i = 0; i < chosenCategories.size(); i++) {
            if (!chosenCategories.get(i).equals(hashMapAssets.get(chosenCategories.get(i)).toString())) {
                hashMapAssets.remove(hashMapAssets.get(chosenCategories.get(i)).toString());
            }

        }

        return hashMapAssets;

    }


    public List<String> listOfGenres() {
        List<String> stringGenres = new ArrayList<>();

        stringGenres.add("E-Sport");
        stringGenres.add("Musik");
        stringGenres.add("Sport");
        stringGenres.add("Random");
        stringGenres.add("Film");
        stringGenres.add("Vetenskap");
        stringGenres.add("Humor");

//        for (int i = 0; i < chosenCategories.size(); i++) {
//
//            for (int j = 0; j < stringGenres.size(); j++) {
//                if (chosenCategories.get(i).equals(stringGenres.get(j))){
//                    stringGenres.add(chosenCategories.get(i));
//                }
//            }
//        }

        //stringGenres.add("Humor");

        List<String> stringHashmapPairs = new ArrayList<>();

        for (int i = 0; i < numOfCoordinates; i++) {
            stringHashmapPairs.add(chosenCategories.get(randomHelper.randomBoundedIndex(chosenCategories.size())));
        }
        return stringHashmapPairs;
    }


    public void rollDieAnimation(){

        die.setVisibility(View.VISIBLE);
        if (rollDice() == 1) {
            die.setImageResource(R.drawable.die1);
        } else if (rollDice() == 2) {
            die.setImageResource(R.drawable.die2);
        } else if (rollDice() == 3) {
            die.setImageResource(R.drawable.die3);
        } else if (rollDice() == 4) {
            die.setImageResource(R.drawable.die4);
        } else {
            die.setImageResource(R.drawable.die5);
        }
    }


    public void setAssetPosList(Point[] posList) {

        posList[0] = (new Point(170, 1530));
        posList[1] = (new Point(310, 1530));
        posList[2] = (new Point(330, 1430));
        posList[3] = (new Point(360, 1340));
        posList[4] = (new Point(430, 1240));
        posList[5] = (new Point(500, 1170));
        posList[6] = (new Point(580, 1070));
        posList[7] = (new Point(650, 940));
        posList[8] = (new Point(680, 840));
        posList[9] = (new Point(700, 740));
        posList[10] = (new Point(710, 640));
        posList[11] = (new Point(800, 540));


        //Vägdelning Rutt A


        posList[12] = (new Point(880, 450));
        posList[13] = (new Point(880, 350));
        posList[14] = (new Point(900, 250));
        posList[15] = (new Point(970, 170));
        posList[16] = (new Point(1100, 150));
        posList[17] = (new Point(1220, 170));
        posList[18] = (new Point(1320, 250));
        posList[19] = (new Point(1440, 330));
        posList[20] = (new Point(1520, 430));
        posList[21] = (new Point(1520, 530));
        posList[22] = (new Point(1500, 610));


        //Vägdelning Rutt B


        // Sammankoppling

        posList[23] = (new Point(1600, 710));
        posList[24] = (new Point(1585, 810));
        posList[25] = (new Point(1550, 1300));
        posList[26] = (new Point(1550, 1400));


        // Alt rutt A

        posList[27] = (new Point(1460, 1500));


        //Alt Rutt B


        //Sammankoppling

        posList[28] = (new Point(1330, 1550));
        posList[29] = (new Point(1200, 1570));
        posList[30] = (new Point(1100, 1620));
        posList[31] = (new Point(1000, 1690));
        posList[32] = (new Point(860, 1670));
        posList[33] = (new Point(710, 1690));
        posList[34] = (new Point(600, 1730));
        posList[35] = (new Point(450, 1700));
        posList[36] = (new Point(320, 1620));

    }
}



