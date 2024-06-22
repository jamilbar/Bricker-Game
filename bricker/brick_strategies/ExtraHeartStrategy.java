package bricker.brick_strategies;

//import bricker.gameobjects.ExtraHeart;
import bricker.gameobjects.GraphicHeart;
import bricker.main.BrickerGameManager;
import bricker.main.Constants;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.ArrayList;
/**
 * This class represent the extra heat strategy that creates hearts after the ball collide with
 * the brick that have this strategy.This class implement the interface CollisionStrategy.
 */
public class ExtraHeartStrategy implements CollisionStrategy{
    /* a game objects to add and remove objects*/
    private  GameObjectCollection gameObjects;
    /* the number of bricks*/
    private  Counter bricksNum;
    /* an image reader to create an image of the objects*/
    private ImageReader imageReader;
    /*The number of hearts in the game*/
    private  Counter heartNum;
    /* a vector that represents the window dimension of the game*/
    private Vector2 windowDimension;
    /* an instance of ArrayList interface, that have all the graphic hearts */
    private ArrayList<GameObject> heartArray;
    
    /**
     * A constructor.
     * @param imageReader an image reader to display an image on the screen
     * @param gameObjects a game objects
     * @param bricksNum a Counter that represents the bricks number
     * @param heartNum the number of hearts.
     * @param windowDimension a vector that represents the window dimension
     * @param heartArray an array list of the hearts in the game.
     */
    public ExtraHeartStrategy(ImageReader imageReader, GameObjectCollection gameObjects, Counter bricksNum,
                              Counter heartNum, Vector2 windowDimension, ArrayList<GameObject> heartArray) {

        this.gameObjects = gameObjects;
        this.imageReader = imageReader;
        this.bricksNum = bricksNum;
        this.heartNum = heartNum;
        this.windowDimension = windowDimension;
        this.heartArray = heartArray;

    }
    
    /**
     * A public void method that overrides the method of the interface. This method create an extra heart,
     * add it to the game and remove the brick that the ball collide with.
     * @param gameObject1 a game object
     * @param gameObject2 a game object
     */
    @Override
    public void onCollision(GameObject gameObject1, GameObject gameObject2) {
        Renderable heartImage = this.imageReader.readImage("assets/heart.png",
                true);
        float middleBrickXCoordinate = (float) (gameObject1.getTopLeftCorner().x() +
                gameObject1.getDimensions().x() * 0.5);
        float middleBrickYCoordinate = (float) (gameObject1.getTopLeftCorner().y() +
                gameObject1.getDimensions().y() * 0.5);
        GraphicHeart heart = new GraphicHeart(new Vector2(middleBrickXCoordinate, middleBrickYCoordinate),
                new Vector2(Constants.HEART_WIDTH, Constants.HEART_HEIGHT), heartImage,gameObjects,heartNum,
                windowDimension, heartArray);
        heart.addExtraHearts();
       if(this.gameObjects.removeGameObject(gameObject1,Layer.STATIC_OBJECTS)){
           this.bricksNum.decrement();
       }

    }


}
