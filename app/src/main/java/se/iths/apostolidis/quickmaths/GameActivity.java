package se.iths.apostolidis.quickmaths;
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

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class
GameActivity extends AppCompatActivity {
    Button buttonRollDice;
    MPhotoView gridMPhotoView;
    MPhotoView map;
    private static int gridSize = 37;
    private int maxX;
    private int maxY;
    // private View view = new View(this);
    //private Bitmap bitmap = Bitmap.createBitmap(maxX, maxY, Bitmap.Config.ARGB_8888);
    private static RandomHelper randomHelper = new RandomHelper();
    FirebaseAuth mAuth;
    SurfaceView surfaceView;
    private Bitmap gridBitmap;
    private Bitmap originalMapBitmap;
    private Point[] assetCoordinates = new Point[gridSize];
    private String[] genreList = new String[gridSize];
    private Drawable d;
    private SurfaceHolder surfaceHolder;
    private Context context;
    private Paint paint = new Paint();
    private ImageView drawView;
    GameEngineSinglePlayer engine;
    private int numberOfPlayer = 3;
    Canvas canvas;
    Canvas tempCanvas;


    ArrayList<Player> players = new ArrayList<>();
    ArrayList<RandomAssetObject> randomAssets = new ArrayList<>();


    private boolean someoneWon;
    private int playerTurnIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        map = findViewById(R.id.photo_view);
        gridMPhotoView = findViewById(R.id.photo_viewGrid);
        //map.setImageResource(R.mipmap.gamemap);
        //map.setImageBitmap(setMap());
        buttonRollDice = findViewById(R.id.buttonRollDice);



        mAuth = FirebaseAuth.getInstance();
        paint.setColor(Color.BLACK);


        randomAssets = setRandomAssets();

        engine = new GameEngineSinglePlayer();


        assetCoordinates = new Point[gridSize];
        setAssetPosList(assetCoordinates);


        //map.setScaleType(PhotoView.ScaleType.FIT_XY);
        //map.setImageBitmap(setMap(assetCoordinates));
        Log.d("Wille", "Map width size: " + map.getWidth());
        Log.d("Wille", "Map height size: " + map.getHeight());

        gridBitmap = setMap(assetCoordinates);
        originalMapBitmap = setMap(assetCoordinates);

        clearMap();
        gameSetUp(gridMPhotoView);
    }

    private void clearMap() {
        gridBitmap = setMap(assetCoordinates);
        //gridBitmap.copy(originalMapBitmap.getConfig(), true);
        //gridBitmap = originalMapBitmap.copy(originalMapBitmap.getConfig(), true);
        gridMPhotoView.setImageBitmap(gridBitmap);
        gridMPhotoView.setScaleType(ImageView.ScaleType.FIT_XY);
        canvas = new Canvas(gridBitmap);
    }


    public void upDate(ArrayList <Player> players ){
        playerTurnIndex ++;
        draw(players);

    }
    public void draw(ArrayList<Player> players){
        clearMap();

        for (int i = 0; i < players.size(); i++){
            canvas.save();
            canvas.drawBitmap(players.get(i).getAvatar(), players.get(i).getPosX() + (i*50), players.get(i).getPosY(), null);
            canvas.restore();
        }
    }

    public void gameSetUp (MPhotoView view){

        playerTurnIndex = 0;
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
        movePlayer(player, rollDice());

        String cat = randomAssets.get(player.getCoordinateIndex()).getGenre();

        getQuestion(cat);
    }

    public void onClickButtonRollDice (View view){
        if (playerTurnIndex == players.size()){
            playerTurnIndex = 0;
        }
        playerTurn(players.get(playerTurnIndex));
    }

    private int rollDice() {
        return engine.rollDice();
    }

    private void getQuestion(String category) {

        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);

    }

    private void movePlayer(Player player, int steps) {

        for(int i = 0; i < steps; i ++) {

            player.setPos(assetCoordinates[player.getCoordinateIndex() + 1].x, assetCoordinates[player.getCoordinateIndex() + 1].y);
            player.setCoordinateIndex(player.getCoordinateIndex()+1);
            Log.d("Wille", "Player Coordinate X :" + player.getPosX() + " Y : " + player.getPosY());

            if (player.getCoordinateIndex() == assetCoordinates.length){
                player.setCoordinateIndex(0);
            }
        }

        upDate(players);

    }

    @NonNull
    private Player setPlayer(ArrayList<Player> players, int i) {
        Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.face1);
        Bitmap scaledAvatar = Bitmap.createScaledBitmap(avatar, 100, 50, false);
        Player player = new Player();
        //player.setId();
        player.setCoordinateIndex(0);
        player.setName("Nisse");
        player.setPos(assetCoordinates[0].x, assetCoordinates[0].y);
        player.setAvatar(scaledAvatar);
        players.add(player);
        return player;
    }

    public Bitmap createGrid(Bitmap screenSizeBitmap){
        gridBitmap = screenSizeBitmap;

        int numOfDots = 40;
        int height = screenSizeBitmap.getHeight();
        int width = screenSizeBitmap.getWidth();
        int spaceX = width / numOfDots;
        int spaceY = height / numOfDots;
        int posX = 0;
        int posY = 0;
        Canvas canvas = new Canvas (gridBitmap);

        for (int i = 0; i < numOfDots; i++){
            posX = 0;
            for (int j = 0; j < numOfDots; j++){
                canvas.save();

                canvas.drawCircle(posX + 10, posY + 10, 10, paint);
                Log.d("Wille", "PosX : " + String.valueOf(posX));
                canvas.restore();
                posX += spaceX;
            }
            posY += spaceY;
        }

        return gridBitmap;

    }

    public Bitmap setMap (Point[] assetCoordinates){

        Bitmap backgroundImage = BitmapFactory.decodeResource(getResources(), R.mipmap.gamemap);
        Bitmap mergedCoordinateAssets = backgroundImage;
        Bitmap previousImage;
        Bitmap scaledCoordinateAsset = backgroundImage;
        for (int i = 0; i < assetCoordinates.length; i++){

            if (i == 0) {
                previousImage = backgroundImage;
            } else {
                previousImage = mergedCoordinateAssets;
            }

            Bitmap coordinateAsset = randomAssets.get(i).getAsset().copy(randomAssets.get(i).getAsset().getConfig(), true);
            //Bitmap coordinateAsset = BitmapFactory.decodeResource(getResources(), R.drawable.gameboardassets_sport);

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

    public ArrayList<RandomAssetObject> setRandomAssets(){
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
        for (int i = 0; i < gridSize; i++){
            randomIndex = randomHelper.randomBoundedIndex(assetGenre.size());
            randomAssetList.add(assetGenre.get(randomIndex));
            genreList[i] = assetGenre.get(randomIndex).getGenre();
            Log.d("Wille", "Random asset add: " + randomAssetList.get(i).getGenre());
            Log.d("Wille", "Random asset add: " + randomAssetList.get(i));

        }

        return randomAssetList;
    }

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
