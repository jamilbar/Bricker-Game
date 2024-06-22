package bricker.gameobjects;

import bricker.main.Constants;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.ArrayList;
/**
 * This class represents a graphic hearts object in the game.It extends GameObject class.
 * This class is responsible for displaying all the hearts we have in the game.
 */
public class GraphicHeart extends GameObject{
    /*an instance of Renderable interface. the image of the heart*/
    private Renderable heartImage;
    /* an instance of GameObjectCollection game, to add and remove objects*/
    private GameObjectCollection gameObjects;
    /* the number of hearts*/
    private Counter heartNum;
    /* a vector that represents the window dimension of the game*/
    private Vector2 windowDimension;
    /* an instance of ArrayList interface, that have all the graphic hearts */
    private ArrayList<GameObject> heartArray;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     * @param gameObjects   a game object
     * @param heartNum      represent the number of hearts.
     * @param windowDimension a vector represent the dimension of the window
     * @param heartArray      an ArrayList of GameObjects.
     */
    public GraphicHeart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                        GameObjectCollection gameObjects, Counter heartNum, Vector2 windowDimension,
                        ArrayList<GameObject> heartArray) {
        super(topLeftCorner, dimensions, renderable);
        this.heartImage = renderable;
        this.gameObjects = gameObjects;
        this.heartNum = heartNum;
        this.windowDimension = windowDimension;
        this.heartArray = heartArray;


    }
    /**
     * A public method that creates the initial hearts and add it to the game, and to the array list.
     */
    public void createInitialHearts(){
        int x = Constants.LEFT_WALL_WIDTH;
        for (int i = 0; i < this.heartNum.value(); i++) {
            GameObject heart = new GameObject(new Vector2(x,
                    this.windowDimension.y() - Constants.HEART_SPACE_FROM_BOTTOM),
                    new Vector2(Constants.HEART_WIDTH, Constants.HEART_HEIGHT), heartImage);
            heart.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
            this.heartArray.add(heart);
            this.gameObjects.addGameObject(heart, Layer.BACKGROUND);
            x += Constants.LEFT_WALL_WIDTH;
        }

    }
    /**
     * A public method that add a heart to the game with velocity.
     */
    public void addExtraHearts() {
        setTag("ExtraHeart");
        gameObjects.addGameObject(this);
        setVelocity(Vector2.DOWN.mult(Constants.HEART_SPEED));

    }
    /**
     * A public method that remove a heart from the list and the game.
     */
    public void removeHearts() {
        GameObject removedHeart = this.heartArray.remove(this.heartNum.value());
        this.gameObjects.removeGameObject(removedHeart, Layer.BACKGROUND);
    }
    /**
     *A public method that overrides the shouldCollideWith method of the GameObject class.This method
     * specify that hearts should only collide with the Paddle object.
     * @param other a game object that collide with the heart.
     * @return return true if the heart collide with the paddle.Otherwise, false.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        super.shouldCollideWith(other);
        return other.getTag().equals("Paddle");
    }

    /**
     * A public void method that overrides the onCollisionEnter method of the GameObject class. This method
     * add the extra heart to the heart list and to the other graphic hearts when  collide with the paddle
     * and the number of heart less than 4.
     * @param other a game object that collide with the heart.
     * @param collision a Collision instance.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (this.getTag().equals("ExtraHeart")){
            if (this.heartNum.value() < Constants.MAX_HEART_NUMBER) {
                GameObject lastHeart = this.heartArray.get(this.heartNum.value() - 1);
                this.heartNum.increment();
                this.heartArray.add(this);
                gameObjects.removeGameObject(this);
                gameObjects.addGameObject(this, Layer.BACKGROUND);
                setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
                this.setCenter(new Vector2(
                        lastHeart.getTopLeftCorner().x() + lastHeart.getDimensions().x() +
                                Constants.EXTRA_HEART_SPACE_FROM_HEARTS,
                        windowDimension.y() -  Constants.EXTRA_HEART_SPACE_FROM_HEARTS));
                this.setVelocity(Vector2.ZERO);
            }
            else {
                gameObjects.removeGameObject(this);
            }
        }
    }

    /**
     * A public void method that overrides the update method of the GameObject class.This method
     *  remove the extra hearts from the game when they go beyond the window dimensions.
     * @param deltaTime the time of the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (this.getTag().equals("ExtraHart") && getCenter().y() > this.windowDimension.y()){
            gameObjects.removeGameObject(this);
        }
    }

}
