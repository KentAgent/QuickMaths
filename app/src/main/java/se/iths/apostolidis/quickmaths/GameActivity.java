package se.iths.apostolidis.quickmaths;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class
GameActivity extends AppCompatActivity {
    Button buttonRollDice;
    MPhotoView gridMPhotoView;
    MPhotoView map;
    TextView textViewScoreBoard;
    private static int numOfCoordinates = 37;
    private int maxX;
    private int maxY;
    // private View view = new View(this);
    //private Bitmap bitmap = Bitmap.createBitmap(maxX, maxY, Bitmap.Config.ARGB_8888);
    private static RandomHelper randomHelper = new RandomHelper();
    FirebaseAuth mAuth;
    SurfaceView surfaceView;
    private Bitmap gridBitmap;
    private Bitmap originalMapBitmap;
    private Point[] assetCoordinates = new Point[numOfCoordinates];
    private String[] genreList = new String[numOfCoordinates];
    private Drawable d;
    private SurfaceHolder surfaceHolder;
    private Context context;
    private Paint paint = new Paint();
    private ImageView drawView;
    GameEngineSinglePlayer engine;
    private int numberOfPlayer = 2;
    Canvas canvas;
    Canvas tempCanvas;
    Bundle extras;
    List<String> randomCategoryStrings;
    HashMap<String, Bitmap> hashMapAssets;

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<RandomAssetObject> randomAssets = new ArrayList<>();

    private boolean someoneWon;
    private int playerTurnIndex;
    private TextView turnTracker;

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



        mAuth = FirebaseAuth.getInstance();
        paint.setColor(Color.BLACK);

        randomCategoryStrings = listOfGenres();
        hashMapAssets = pairHashmapWithKey();

        //randomAssets = setRandomAssets();

        engine = new GameEngineSinglePlayer();


        assetCoordinates = new Point[numOfCoordinates];
        setAssetPosList(assetCoordinates);


        //map.setScaleType(PhotoView.ScaleType.FIT_XY);
        //map.setImageBitmap(setMap(assetCoordinates));
        Log.d("Wille", "Map width size: " + map.getWidth());
        Log.d("Wille", "Map height size: " + map.getHeight());


        clearMap();
        gameSetUp(gridMPhotoView);
        updateScoreBoard();
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

    public void upDate(ArrayList <Player> players ){
        draw(players);

    }
    public void draw(ArrayList<Player> players){
        //clearMap();
        clearPlayers();

        for (int i = 0; i < players.size(); i++){
            canvas.save();
            canvas.drawBitmap(players.get(i).getAvatar(), players.get(i).getPosX() + (i*50), players.get(i).getPosY(), null);
            canvas.restore();
        }
    }


    public void gameSetUp (MPhotoView view){

        playerTurnIndex = 0;
        turnTracker.setVisibility(View.GONE);
        for (int i = 0; i < numberOfPlayer; i++){

            Player player = setPlayer(players, i);
            //RectF avatarRect = new RectF();
            //avatarRect.set(player.getPosX(), player.getPosY(), 200, 200);
            canvas.save();
            canvas.drawBitmap(player.getAvatar(), player.getPosX(), player.getPosY(), null);
            canvas.restore();
            Log.d("Wille", "Player id: " + String.valueOf(player.getId()));
        }

        //startGame(players);
    }

    public void playerTurn(Player player) {
        player.setLastThrownDie(rollDice());
        movePlayer(player, player.getLastThrownDie());

        String category = randomCategoryStrings.get(player.getCoordinateIndex());
        getQuestion(category);
    }

    public void onClickButtonRollDice (View view){


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
            if(requestCode == 1 ){
                if(resultCode == Activity.RESULT_OK){
                 players.get(playerTurnIndex).addScore(3);
                 updateScoreBoard();
                }
                if (resultCode == Activity.RESULT_CANCELED){
                    moveBackPlayer();
                }
            }
              playerTurnIndex ++;
        if (playerTurnIndex == numberOfPlayer){
            playerTurnIndex = 0;
        }

        turnTracker.setVisibility(View.VISIBLE);

        turnTracker.setText("Player " + (playerTurnIndex + 1) + " turn to play");
        turnTracker.postDelayed(new Runnable() {
            @Override
            public void run() {
                turnTracker.setVisibility(View.INVISIBLE);

            }
        },3000);


    }

    private void moveBackPlayer() {

        for(int i = 0; i < players.get(playerTurnIndex).getLastThrownDie(); i ++) {

            players.get(playerTurnIndex).setPos(assetCoordinates[players.get(playerTurnIndex).getCoordinateIndex() - 1].x, assetCoordinates[players.get(playerTurnIndex).getCoordinateIndex() - 1 ].y);
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

        for(int i = 0; i < steps; i ++) {

            player.setPos(assetCoordinates[player.getCoordinateIndex() + 1].x, assetCoordinates[player.getCoordinateIndex() + 1].y);
            player.setCoordinateIndex(player.getCoordinateIndex()+1);
            Log.d("Wille", "Player Coordinate X :" + player.getPosX() + " Y : " + player.getPosY());

            if (player.getCoordinateIndex() >= assetCoordinates.length){
                wonGame();
                break;
            }
        }

        upDate(players);
    }

    private void wonGame() {

    }

    @NonNull
    private Player setPlayer(ArrayList<Player> players, int i) {
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.player1);
        Bitmap scaledAvatar = Bitmap.createScaledBitmap(avatar, 200, 100, false);
        Player player = new Player();
        //player.setId();
        player.setCoordinateIndex(0);
        player.setName("Nisse");
        player.setPos(assetCoordinates[0].x, assetCoordinates[0].y);
        player.setAvatar(scaledAvatar);
        players.add(player);
        return player;
    }


    public void updateScoreBoard(){
        textViewScoreBoard.setText(scoreBoardSetup());
    }

    public String scoreBoardSetup(){
        String scoreBoard = "";
        for (int i = 0; i <players.size() ; i++) {
            scoreBoard += "Player " + (i+1) + " Score: "  + players.get(i).getScore();
            if (i < players.size() -1)
                scoreBoard += "\n";
        } return scoreBoard;
    }
    public Bitmap setMap (Point[] assetCoordinates){

        Bitmap backgroundImage = BitmapFactory.decodeResource(getResources(), R.mipmap.gameboard_transparent);
        Bitmap mergedCoordinateAssets = backgroundImage;
        Bitmap previousImage;
        Bitmap scaledCoordinateAsset = backgroundImage;
        for (int i = 0; i < assetCoordinates.length; i++){

            if (i == 0) {
                previousImage = backgroundImage;
            } else {
                previousImage = mergedCoordinateAssets;
            }

            //Bitmap coordinateAsset = BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_sport);
            //Bitmap coordinateAsset = randomAssets.get(i).getAsset().copy(randomAssets.get(i).getAsset().getConfig(), true);

            Bitmap coordinateAsset = hashMapAssets.get(listOfGenres().get(i).toString());


            //coordinateAsset = randomAssets.get(i).getAsset();
            scaledCoordinateAsset = Bitmap.createScaledBitmap(coordinateAsset, 50, 50, false);
            Log.d("Wille", "X value: " + assetCoordinates[i].x);

            mergedCoordinateAssets = mergeImages(previousImage, scaledCoordinateAsset, 0, 0, assetCoordinates[i].x + 20, assetCoordinates[i].y + 20);
        }

        Log.d("Wille", "Bigmerge height: " + mergedCoordinateAssets.getHeight());
        return mergedCoordinateAssets;
    }

    private Bitmap mergeImages(Bitmap firstImage, Bitmap secondImage, int firstImageX, int firstImageY, int secondImageX, int secondImageY){
        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, firstImageX, firstImageY, null);
        canvas.drawBitmap(secondImage, secondImageX, secondImageY, null);
        return result;
    }

    public HashMap pairHashmapWithKey (){
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

        return hashMapAssets;

        //TODO FIX getting points when wrong answer
        //TODO SHow whose turn it is
    }


    public List<String> listOfGenres(){
        List<String> stringGenres = new ArrayList<>();
        stringGenres.add("E-Sport");
        stringGenres.add("Musik");
        stringGenres.add("Sport");
        stringGenres.add("Random");
        stringGenres.add("Film");
        stringGenres.add("Vetenskap");
        stringGenres.add("Humor");

        //stringGenres.add("Humor");

        List<String> stringHashmapPairs = new ArrayList<>();

        for (int i = 0; i < numOfCoordinates; i++){
            stringHashmapPairs.add(stringGenres.get(randomHelper.randomBoundedIndex(stringGenres.size())));

        }

        return stringHashmapPairs;
    }

    /*public ArrayList<RandomAssetObject> setRandomAssets(){
        ArrayList<RandomAssetObject> randomAssetList = new ArrayList<>();
        ArrayList<RandomAssetObject> assetGenre = new ArrayList<>();

        RandomAssetObject assetMath = new RandomAssetObject();
        Bitmap bitmapMath = BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_maths);
        assetMath.setGenre("Math");
        assetMath.setAsset(bitmapMath);
        assetGenre.add(assetMath);

        RandomAssetObject assetNature = new RandomAssetObject();
        Bitmap bitmapNature = BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_nature);
        assetNature.setGenre("Nature");
        assetNature.setAsset(bitmapNature);
        assetGenre.add(assetNature);

        RandomAssetObject assetCultureMusic = new RandomAssetObject();
        Bitmap bitmapCultureMusic= BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_culture_music);
        assetCultureMusic.setGenre("Culture");
        assetCultureMusic.setAsset(bitmapCultureMusic);
        assetGenre.add(assetCultureMusic);

        RandomAssetObject assetEsport = new RandomAssetObject();
        Bitmap bitmapEsport= BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_games);
        assetEsport.setGenre("Esport");
        assetEsport.setAsset(bitmapEsport);
        assetGenre.add(assetEsport);

        RandomAssetObject assetGeo = new RandomAssetObject();
        Bitmap bitmapGeo= BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_geography);
        assetEsport.setGenre("Geography");
        assetEsport.setAsset(bitmapGeo);
        assetGenre.add(assetGeo);

        RandomAssetObject assetRandom = new RandomAssetObject();
        Bitmap bitmapRandom= BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_random);
        assetRandom.setGenre("Random");
        assetRandom.setAsset(bitmapRandom);
        assetGenre.add(assetRandom);

        RandomAssetObject assetScience = new RandomAssetObject();
        Bitmap bitmapScience= BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_sience);
        assetScience.setGenre("Science");
        assetScience.setAsset(bitmapScience);
        assetGenre.add(assetScience);

        RandomAssetObject assetSport = new RandomAssetObject();
        Bitmap bitmapSport= BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_sport);
        assetSport.setGenre("Sport");
        assetSport.setAsset(bitmapSport);
        assetGenre.add(assetSport);

        int randomIndex;
        for (int i = 0; i < numOfCoordinates; i++){
            randomIndex = randomHelper.randomBoundedIndex(assetGenre.size());
            randomAssetList.add(assetGenre.get(randomIndex));
            genreList[i] = assetGenre.get(randomIndex).getGenre();
            Log.d("Wille", "Random asset add: " + randomAssetList.get(i).getGenre());
            Log.d("Wille", "Random asset add: " + randomAssetList.get(i));

        }

        return randomAssetList;
    }*/

    public void setAssetPosList (Point[] posList){

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

        /**
         * Vägdelning Rutt A
         */
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



        /**
         * Vägdelning Rutt B
         */


        /**
         * Sammankoppling
         */
        posList[23] = (new Point(1600, 710));
        posList[24] = (new Point(1585, 810));
        posList[25] = (new Point(1550, 1300));
        posList[26] = (new Point(1550, 1400));

        /**
         * Alt rutt A
         */
        posList[27] = (new Point(1460, 1500));

        /**
         * Alt Rutt B
         */

        /**
         * Sammankoppling
         */
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
