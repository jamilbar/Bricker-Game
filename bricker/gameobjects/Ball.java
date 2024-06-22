package bricker.gameobjects;

import bricker.brick_strategies.CameraStrategy;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
/**
 * This class extend the GameObject class.This class implements the onCollisionEnter method when a ball
 * collide with any object.And also implements getter and setter for the collision counter of the ball.
 */
public class Ball extends GameObject {
    /* the collision sound of the ball, it is instance of Sound class*/
    private  Sound collisionSound;
    /* a counter that counts the number of ball collisions with any object in the game*/
    private int collisionCounter;
    /* an instance of CameraStrategy class*/
    private CameraStrategy cameraStrategy;
    /* a vector that represents the window dimension of the game*/
    private Vector2 windowDimension;
    /* a game objects to add and remove objects*/
    private GameObjectCollection gameObjects;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     * @param collisionSound the sound of the ball after collision.
     * @param windowDimension a vector that represents the window dimension
     * @param gameObjects a game objects
     * @param cameraStrategy         a cameraStrategy instance.
     */

    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound,
                Vector2 windowDimension, GameObjectCollection gameObjects, CameraStrategy cameraStrategy) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
        this.collisionCounter = 0;
        this.cameraStrategy = cameraStrategy;
        this.windowDimension = windowDimension;
        this.gameObjects = gameObjects;
    }
    /**
     * A public void method that overrides the onCollisionEnter method of the GameObject class.This method
     * flipped the velocity of the ball when the ball collide with any other object, call the onCollision
     * method of the camera strategy and  count the number of collisions when the ball collide with a brick
     * that have a camera strategy behavior.
     * @param other a game object that represent the object that collide with the ball.
     * @param collision a Collision instance.
     */

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        this.collisionSound.play();
        if (cameraStrategy == null){
            return;
        }
        this.collisionCounter++;
        cameraStrategy.onCollision(this, other);
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(getTag().equals("PuckBall") && getCenter().y() > this.windowDimension.y() ){
            this.gameObjects.removeGameObject(this);
        }
    }
    /**
     * A public method that returns the number of collisions of the ball with all the objects in the game.
     * @return the number of collisions of the ball.
     */
    public int getCollisionCounter() {
        return this.collisionCounter;
    }
    /**
     * A setter method to chane the collisionCounter to the parameter the method receive.
     * @param collisionCounter an integer of the number of collisions.
     */
    public void setCollisionCounter(int collisionCounter) {
        this.collisionCounter = collisionCounter;
    }
}
