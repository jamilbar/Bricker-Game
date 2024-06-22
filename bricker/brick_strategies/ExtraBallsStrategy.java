package bricker.brick_strategies;

//import bricker.gameobjects.PuckBall;
import bricker.gameobjects.Ball;
import bricker.main.BrickerGameManager;
import bricker.main.Constants;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.Sound;
import danogl.gui.SoundReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

/**
 * This class represent the extra ball strategy that creates another two balls after the main ball collide
 * with a brick that have this strategy. The class implements the CollisionStrategy interface.
 */
public class ExtraBallsStrategy implements CollisionStrategy {
    /* an image reader to create an image of the objects*/
    private ImageReader imageReader;
    /* a SoundReader instance for the sound of the object*/
    private SoundReader soundReader;
    /* the number of bricks*/
    private Counter bricksNum;
    /* a game objects to add and remove objects*/
    private GameObjectCollection gameObjects;
    /* a vector that represents the window dimension of the game*/
    private Vector2 windowDimension;
    /**
     * A constructor.
     * @param bricksNum a Counter that represents the bricks number
     * @param gameObjects a game objects
     * @param windowDimension a vector that represents the window dimension
     * @param soundReader a SoundReader.
     * @param imageReader an image reader to display an image on the screen

     */
    public ExtraBallsStrategy(Counter bricksNum, GameObjectCollection gameObjects, Vector2 windowDimension,
                              SoundReader soundReader, ImageReader imageReader) {
        this.bricksNum = bricksNum;
        this.windowDimension = windowDimension;
        this.gameObjects = gameObjects;
        this.imageReader = imageReader;
        this.soundReader = soundReader;

    }
    /**
     * A public void method that overrides the method of the CollisionStrategy interface. This method creates
     * extra two puck balls of instance ball with a velocity and add them to the game, it also removes.
     * @param gameObject1 a game object
     * @param gameObject2 a game object
     */
    @Override
    public void onCollision(GameObject gameObject1, GameObject gameObject2) {
        Renderable puckImage = this.imageReader.readImage("assets/mockBall.png",
                false);
        Sound ballSound = this.soundReader.readSound("assets/blop_cut_silenced.wav");

        //remove brick

        if(this.gameObjects.removeGameObject(gameObject1, Layer.STATIC_OBJECTS)){
            this.bricksNum.decrement();
        }

        //create pucks

        float xCoordinationOfBall = (float) (gameObject1.getTopLeftCorner().x() +
                gameObject1.getDimensions().x() * 0.5);
        float yCoordinationOfBall = (float) (gameObject1.getTopLeftCorner().y() +
                gameObject1.getDimensions().y() * 0.5);
        Ball puck1 = new Ball(new Vector2(xCoordinationOfBall, yCoordinationOfBall),
                new Vector2((float) (Constants.BALL_WIDTH * Constants.BALL_PUCK_BALL_SIZE_RATIO),
                (float) (Constants.BALL_WIDTH * Constants.BALL_PUCK_BALL_SIZE_RATIO)), puckImage, ballSound,
                windowDimension, gameObjects, null);
        Ball puck2 = new Ball(new Vector2(xCoordinationOfBall, yCoordinationOfBall),
                new Vector2((float) (Constants.BALL_WIDTH * Constants.BALL_PUCK_BALL_SIZE_RATIO),
                (float) (Constants.BALL_WIDTH * Constants.BALL_PUCK_BALL_SIZE_RATIO)), puckImage, ballSound,
                windowDimension, gameObjects,null);

        //add two buck to the game
        puck1.setTag("PuckBall");
        puck2.setTag("PuckBall");
        this.gameObjects.addGameObject(puck1);
        this.gameObjects.addGameObject(puck2);
        ballVelocity(puck1);
        ballVelocity(puck2);
    }

    /**
     * A private method that set the velocity of the extra balls
     * @param puck an instance of Ball.
     */
    private void ballVelocity(Ball puck){
        Random rand = new Random();
        double angel = rand.nextDouble() * Math.PI;
        float velocityX = (float) Math.cos(angel) * Constants.BALL_SPEED;
        float velocityY = (float) Math.sin(angel) * Constants.BALL_SPEED;
        puck.setVelocity(new Vector2(velocityX,velocityY));
    }


}
