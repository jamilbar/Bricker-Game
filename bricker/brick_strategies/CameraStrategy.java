package bricker.brick_strategies;

import bricker.gameobjects.Ball;
import bricker.main.Constants;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.util.Counter;
import danogl.util.Vector2;

/**
 * This class implements the CollisionStrategy interface and change the camera when the ball hit a brick
 * with a cameraStrategy.
 */
public class CameraStrategy implements CollisionStrategy  {
    /* instance of GameManger class, the responsible for all the object in the game*/
    private GameManager gameManager;
    /* the window controller of the game, it is instance of WindowController.*/
    private WindowController windowController;
    /* a game object to add and remove objects*/
    private GameObjectCollection gameObjects;
    /*the number of bricks*/
    private Counter bricksNum;

    /**
     * A constructor.
     * @param gameManager an instance of GameManger class
     * @param gameObjects an instance of GameObject class
     * @param windowController the window controller of the game.
     * @param bricksNum the number of bricks in the game.
     */
    public CameraStrategy(GameManager gameManager,GameObjectCollection gameObjects,
                          WindowController windowController,Counter bricksNum) {
        this.gameManager = gameManager;
        this.windowController = windowController;
        this.gameObjects =gameObjects;
        this.bricksNum = bricksNum;
    }


    /**
     * A public void method that overrides the onCollision of the CollisionStrategy interface. This method
     * change the focus of the camera to focus only on the main ball if and only of the main ball collide
     * with a brick that have a cameraStrategy. After for collision of the main ball with any other object in
     * the game the camera return to null
     * @param gameObject1 a game object.
     * @param gameObject2 a game object
     */
    @Override
    public void onCollision(GameObject gameObject1, GameObject gameObject2) {
        // after a ball collide with a brick
        if (gameObject2.getTag().equals("Ball")){
            Ball ball  = (Ball) gameObject2;
            if (this.gameManager.camera() == null){
                ball.setCollisionCounter(0);
                this.gameManager.setCamera(new Camera(ball, Vector2.ZERO,
                        this.windowController.getWindowDimensions().mult(Constants.CAMERA_FACTOR),
                        this.windowController.getWindowDimensions()));
            }

            if (ball.getCollisionCounter() == Constants.COLLISIONS_UNTIL_CAMERA_CHANGES  + 1){
                this.gameManager.setCamera(null);
            }
        }
        // the number of collisions of any objects with the ball
        if (gameObject1.getTag().equals("Ball")) {
            Ball ball  = (Ball) gameObject1;
            if (ball.getCollisionCounter() == Constants.COLLISIONS_UNTIL_CAMERA_CHANGES + 1 ){
                this.gameManager.setCamera(null);
            }
        }
        if (gameObject1.getTag().equals("Brick")){
            if(this.gameObjects.removeGameObject(gameObject1, Layer.STATIC_OBJECTS)){
                this.bricksNum.decrement();
            }
        }

    }

}
