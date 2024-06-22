package bricker.main;

import bricker.brick_strategies.*;
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
 *This class is responsible for creating strategies for every brick according to probability
 */
public class Factory {
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
     * @param imageReader an image reader to create an image of the objects
     * @param gameManager instance of GameManger class, the responsible for all the object in the game
     * @param inputListener an input listener that listens to the keyboard
     * @param windowController the window controller of the game, it is instance of WindowController.
     * @param heartNum the number of current hearts in the game.
     * @param heartArray an array of the hearts in the game.
     */
    public Factory(Counter bricksNum, GameObjectCollection gameObjects, Vector2 windowDimension
            , SoundReader soundReader, ImageReader imageReader,
                   UserInputListener inputListener, GameManager gameManager,
                   WindowController windowController, Counter heartNum, ArrayList<GameObject> heartArray) {
        this.bricksNum = bricksNum;
        this.gameObjects = gameObjects;
        this.windowDimension = windowDimension;
        this.soundReader = soundReader;
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.gameManager = gameManager;
        this.windowController = windowController;
        this.heartNum = heartNum;
        this.heartArray = heartArray;

    }
    /**
     * A public method that create the strategies for all the bricks, the method receive one parameter
     * and according to this parameter we chose which strategy to return: if the percentage smaller than 0.5
     * the method return BasicCollisionStrategy.Otherwise, all the other strategies have the same probability
     * to be chosen
     * @param percentage a number between 0 and 9
     * @return a collision strategy to the brick.
     */
    public CollisionStrategy buildBricks(int percentage) {
        switch (percentage){
            case Constants.BASIC_STRATEGY_PROBABILITY_ZERO:
            case Constants.BASIC_STRATEGY_PROBABILITY_ONE:
            case Constants.BASIC_STRATEGY_PROBABILITY_TWO:
            case Constants.BASIC_STRATEGY_PROBABILITY_THREE:
            case Constants.BASIC_STRATEGY_PROBABILITY_FOUR:
                return new BasicCollisionStrategy(gameObjects,bricksNum);
            case Constants.EXTRA_BALL_STRATEGY_PROBABILITY:
                return new ExtraBallsStrategy(bricksNum,gameObjects,windowDimension,soundReader,imageReader);
            case Constants.EXTRA_PADDLE_STRATEGY_PROBABILITY:
                return new ExtraPaddleStrategy(gameObjects,imageReader,inputListener,windowDimension,
                        bricksNum);
            case Constants.CAMERA_STRATEGY_PROBABILITY:
                return new CameraStrategy(gameManager,gameObjects,windowController,bricksNum);
            case Constants.EXTRA_HEART_STRATEGY_PROBABILITY:
                return new ExtraHeartStrategy(imageReader,gameObjects,bricksNum,heartNum,windowDimension,
                        heartArray);
            case Constants.DOUBLE_BEHAVIOR_STRATEGY_PROBABILITY:
                return new DoubleBehaviorStrategy(bricksNum, gameObjects, windowDimension, soundReader,
                        imageReader, inputListener, gameManager, windowController, heartNum,
                        Constants.INITIAL_DEPTH_OF_DOUBLE_BEHAVIOR, heartArray);
        }
       return null;
    }
}
