package bricker.brick_strategies;

import bricker.main.Constants;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class implements the CollisionStrategy interface.This strategy randomly selects two collision
 * strategies to the brick that have Double behavior.
 */
public class DoubleBehaviorStrategy implements CollisionStrategy {
    /* an instance of Random class*/
    private Random random;
    /*he number of double behavior the brick have.*/
    private int currentDepth;
    /* instance of CollisionStrategy interface*/
    private CollisionStrategy firstCollisionStrategy;
    /* instance of CollisionStrategy interface*/
    private CollisionStrategy secondCollisionStrategy;
    /* the number of bricks*/
    private Counter bricksNum;
    /* a game objects to add and remove objects*/
    private GameObjectCollection gameObjects;
    /* a vector that represents the window dimension of the game*/
    private Vector2 windowDimension;
    /* a SoundReader instance for the sound of the object*/
    private SoundReader soundReader;
    /* an image reader to create an image of the objects*/
    private ImageReader imageReader;
    /* an input listener that listens to the keyboard*/
    private UserInputListener inputListener;
    /* instance of GameManger class, the responsible for all the object in the game*/
    private GameManager gameManager;
    /* the window controller of the game, it is instance of WindowController.*/
    private WindowController windowController;
    /*The number of hearts in the game*/
    private Counter heartNum;
    /* an instance of ArrayList interface, that have all the graphic hearts */
    private ArrayList<GameObject> heartArray;

    /**
     * A constructor.
     * @param bricksNum the number of bricks
     * @param gameObjects a game objects
     * @param windowDimension a vector that represents the window dimension
     * @param soundReader a SoundReader instance
     * @param imageReader an image reader to display an image on the screen
     * @param inputListener an input listener that listens to the keyboard
     * @param gameManager instance of GameManger class, the responsible for all the object in the game
     * @param windowController the window controller of the game, it is instance of WindowController.
     * @param heartNum the number of current hearts in the game.
     * @param currentDepth the number of double behavior the brick have.
     * @param heartArray an array of the hearts in the game.
     */
    public DoubleBehaviorStrategy(Counter bricksNum, GameObjectCollection gameObjects,Vector2 windowDimension
            , SoundReader soundReader, ImageReader imageReader, UserInputListener inputListener,
            GameManager gameManager, WindowController windowController, Counter heartNum,int currentDepth,
            ArrayList<GameObject> heartArray) {

        this.random = new Random();
        this.bricksNum = bricksNum;
        this.gameObjects = gameObjects;
        this.windowDimension = windowDimension;
        this.soundReader = soundReader;
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.gameManager = gameManager;
        this.currentDepth = currentDepth;
        this.windowController = windowController;
        this.heartNum = heartNum;
        this.heartArray = heartArray;
    }
    /**
     * A public void method that overrides the onCollision method of CollisionStrategy interface.This method
     * randomly selects two collision strategies out of 5 strategies to the brick that have Double behavior,
     * each strategy have the same chane to be chosen.It also calls the onCollision method of the two chosen
     * strategies
     * @param gameObject1 a game object.
     * @param gameObject2 a game object.
     */
    @Override
    public void onCollision(GameObject gameObject1, GameObject gameObject2) {
        CollisionStrategy[] collisionStrategies = {this.firstCollisionStrategy, this.secondCollisionStrategy};
        for (int i = 0; i < collisionStrategies.length; i++) {
            int collisionProbability = random.nextInt(Constants.NUMBER_OF_PROBABILITY_CASES);
            switch (collisionProbability){
                case Constants.DOUBLE_EXTRA_BALL_PROBABILITY_ZERO:
                case Constants.DOUBLE_EXTRA_BALL_PROBABILITY_ONE:
                    collisionStrategies[i] = new ExtraBallsStrategy(bricksNum, gameObjects, windowDimension,
                            soundReader, imageReader);
                    break;
                case Constants.DOUBLE_EXTRA_PADDLE_PROBABILITY_TWO:
                case Constants.DOUBLE_EXTRA_PADDLE_PROBABILITY_THREE:
                    collisionStrategies[i] = new ExtraPaddleStrategy(gameObjects, imageReader, inputListener,
                            windowDimension, bricksNum);
                    break;
                case Constants.DOUBLE_CAMERA_PROBABILITY_FOUR:
                case Constants.DOUBLE_CAMERA_PROBABILITY_FIVE:
                    collisionStrategies[i] = new CameraStrategy(gameManager, gameObjects, windowController,
                            bricksNum);
                    break;
                case Constants.DOUBLE_EXTRA_HEART_PROBABILITY_SIX:
                case Constants.DOUBLE_EXTRA_HEART_PROBABILITY_SEVEN:
                    collisionStrategies[i] = new ExtraHeartStrategy(imageReader, gameObjects, bricksNum,
                            heartNum, windowDimension, heartArray);
                    break;
                case Constants.DOUBLE_BEHAVIOR_PROBABILITY_EIGHT:
                case Constants.DOUBLE_BEHAVIOR_PROBABILITY_NINE:
                    if (this.currentDepth < Constants.MAX_NUMBER_OF_DOUBLE_BEHAVIORS) {
                        this.currentDepth++;
                        collisionStrategies[i] = new DoubleBehaviorStrategy(bricksNum, gameObjects,
                                windowDimension, soundReader, imageReader, inputListener, gameManager,
                                windowController, heartNum,this.currentDepth, heartArray);
                        break;
                    }else {
                        i--;
                    }
            }
        }
        collisionStrategies[0].onCollision(gameObject1, gameObject2);
        collisionStrategies[1].onCollision(gameObject1, gameObject2);
        }

}



