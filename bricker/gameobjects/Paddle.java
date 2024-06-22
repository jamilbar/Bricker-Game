package bricker.gameobjects;

import bricker.brick_strategies.CollisionStrategy;
import bricker.brick_strategies.ExtraPaddleStrategy;
import bricker.main.BrickerGameManager;
import bricker.main.Constants;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import java.awt.event.KeyEvent;

/**
 * This class represents a paddle object in the game. The class extends GameObject class.
 */
public class Paddle extends GameObject {
    /* an input listener that listens to the keyboard*/

    private  UserInputListener inputListener;
    /* a vector that represents the window dimension of the game*/
    private Vector2 windowDimensions;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     * @param inputListener an input listener that listen to the keyboard input.
     * @param windowDimensions a vector that represents the window dimension
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                  UserInputListener inputListener, Vector2 windowDimensions) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;

    }
    /**
     * A public void method that overrides the update method of the GameObject class. It updates the position
     * and the velocity of the paddle based on which key pressed and ensures that the paddle stays within
     * the boundaries of the game.
     * @param deltaTime the time from the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Vector2 movementDir = Vector2.ZERO;
        if (getTopLeftCorner().x() <= 0) {
            setTopLeftCorner(new Vector2(0, getTopLeftCorner().y()));
        }
        if (getTopLeftCorner().x() + Constants.PADDLE_WIDTH >= this.windowDimensions.x()) {
            setTopLeftCorner(new Vector2(windowDimensions.x() - Constants.PADDLE_WIDTH,
                    getTopLeftCorner().y()));
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            movementDir = movementDir.add(Vector2.RIGHT);
        }
        setVelocity(movementDir.mult(Constants.MOVEMENT_SPEED));
    }


}

