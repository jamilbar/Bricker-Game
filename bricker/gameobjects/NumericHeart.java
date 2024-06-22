package bricker.gameobjects;

import bricker.main.Constants;
import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;

/**
 * This class represents a numeric hearts object in the game.It extends GameObject class.
 * This class is responsible for displaying the number of hearts we have in the
 * game.
 */
public class NumericHeart extends GameObject {

    /* instance of TextRenderable class.*/
    private TextRenderable text;
    /* The number of Graphic hearts in the game*/
    private Counter heartNum;
    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     * @param heartNum      represent the number of hearts.
     */
    public NumericHeart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Counter heartNum){
        super(topLeftCorner, dimensions, renderable);
        this.text = (TextRenderable) renderable;
        this.heartNum = heartNum;
    }
    /**
     * A public void method that overrides the update method of the GameObject class. It updates the text
     * rendering and the color of the heart based on the numeric value associated with it.
     * @param deltaTime the time of the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.text.setString(String.valueOf(this.heartNum.value()));
        if (this.heartNum.value() >= Constants.GREEN_COLOR_HEART_NUMBER){
            this.text.setColor(Color.green);
        }
        if (this.heartNum.value() == Constants.YELLOW_COLOR_HEART_NUMBER){
            this.text.setColor(Color.yellow);
        } else if (this.heartNum.value() == Constants.RED_COLOR_HEART_NUMBER) {
            this.text.setColor(Color.red);
        }
    }


}
