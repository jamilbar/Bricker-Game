package bricker.main;

/**
 * This class have all the constants in the project
 */
public class Constants {
    /**
     *The speed of the ball.
     */
    public static final float BALL_SPEED = 250;
    /**
     * The factor of mult method
     */
    public static final float FACTOR = 0.5f;
    /**
     *The width of the paddle
     */
    public static final int PADDLE_WIDTH = 100;
    /**
     *The height of the paddle
     */
    public static final int PADDLE_HEIGHT = 15;
    /**
     * The default number of bricks per row
     */
    public static final int DEFAULT_BRICKS_PER_ROW = 8;
    /**
     * The default number of row
     */
    public static final int DEFAULT_NUM_OF_ROWS = 7;
    /**
     * The space between bricks
     */
    public static final int SPACE_BETWEEN_BRICKS = 5;
    /**
     * The left wall width
     */
    public static final int LEFT_WALL_WIDTH = 30;
    /**
     * The up wall height
     */
    public static final int UP_WALL_HEIGHT = 15;
    /**
     * The space between rows
     */
    public static final int SPACE_BETWEEN_ROWS = 20;
    /**
     * The bricks height
     */
    public static final int BRICK_HEIGHT = 15;
    /**
     *The ball width
     */
    public static final int BALL_WIDTH = 20;
    /**
     * The ball height
     */
    public static final int BALL_HEIGHT = 20;
    /**
     * The heart width
     */
    public static final int HEART_WIDTH = 25;
    /**
     * The heart height
     */
    public static final int HEART_HEIGHT = 25;
    /**
     * The initial number of hearts.
     */
    public static final int HEART_NUMBER = 3;
    /**
     * Lose message
     */
    public static final String LOSE_MESSAGE = "You Lose!";
    /**
     * Win message
     */
    public static final String WIN_MESSAGE = "You win!";
    /**
     * Ask if to play again
     */
    public static final String RESTART_GAME_MESSAGE = " Play again?";
    /**
     * the speed of the paddle.
     */
    public static final int MOVEMENT_SPEED = 300;
    /**
     * The number of ball collision to change the camera
     */
    public static final int COLLISIONS_UNTIL_CAMERA_CHANGES = 4;
    /**
     * The speed of the heart.
     */
    public static final int HEART_SPEED = 100;
    /**
     * The maximum number of hearts
     */
    public static final int MAX_HEART_NUMBER = 4;
    /**
     * The number of ball collisions to remove the extra paddle
     */
    public static final int COLLISIONS_UNTIL_PADDLE_DISAPPEARS = 4;
    /**
     * The maximum number of double behavior strategy.
     */
    public static final int MAX_NUMBER_OF_DOUBLE_BEHAVIORS = 2;
    /**
     * the x coordinate of the text that represents the number of lives/hearts in the game.
     */
    public static  final int HEART_NUMBER_POSITION = 5;
    /**
     * The ratio between the size of the ball and the puck ball.
     */
    public static final double BALL_PUCK_BALL_SIZE_RATIO = 0.75;
    /**
     * The number of hearts to display to green color
     */
    public  static  final int GREEN_COLOR_HEART_NUMBER = 3;
    /**
     * The number of hearts to display to yellow color
     */
    public static final int  YELLOW_COLOR_HEART_NUMBER = 2;
    /**
     * The number of hearts to display to red color
     */

    public static final int  RED_COLOR_HEART_NUMBER = 1;

    /**
     * The initial depth of the double behavior strategy.
     */
    public static final int INITIAL_DEPTH_OF_DOUBLE_BEHAVIOR = 1;
    /**
     * the space between the hearts and the bottom of the window.
     */
    public static final int HEART_SPACE_FROM_BOTTOM = 30;
    /**
     * The space between the extra heart and the other hearts than display on the screen
     */
    public static final int EXTRA_HEART_SPACE_FROM_HEARTS = 18;
    /**
     * the space between the paddle and the bottom of the window.
     */
    public static final int PADDLE_SPACE_FROM_BOTTOM = 30;
    /**
     * the space between the numeric hearts and the bottom of the window.
     */
    public static final int NUMERIC_HEART_SPACE_FROM_BOTTOM = 33;
    /**
     * the space between the bricks and the left and right walls.
     */
    public static final int SPACE_BETWEEN_BRICKS_WALLS = 15;
    /**
     * the first probability that we choose the basic strategy(in the factory) out of 10
     */
    public static final int BASIC_STRATEGY_PROBABILITY_ZERO = 0;
    /**
     * the second probability that we choose the basic strategy(in the factory) out of 10
     */
    public static final int BASIC_STRATEGY_PROBABILITY_ONE = 1;
    /**
     * the third probability that we choose the basic strategy(in the factory) out of 10
     */
    public static final int BASIC_STRATEGY_PROBABILITY_TWO = 2;
    /**
     * the forth probability that we choose the basic strategy(in the factory) out of 10
     */
    public static final int BASIC_STRATEGY_PROBABILITY_THREE = 3;
    /**
     * the fifth probability that we choose the basic strategy(in the factory) out of 10
     */
    public static final int BASIC_STRATEGY_PROBABILITY_FOUR = 4;
    /**
     * the probability that we choose the extra ball strategy(int the factory) out of 10
     */
    public static final int EXTRA_BALL_STRATEGY_PROBABILITY = 5;
    /**
     * the probability that we choose the extra paddle strategy(int the factory) out of 10
     */
    public static final int EXTRA_PADDLE_STRATEGY_PROBABILITY = 6;
    /**
     * the probability that we choose the extra camera strategy(int the factory) out of 10
     */
    public static final int CAMERA_STRATEGY_PROBABILITY = 7;
    /**
     *  the probability that we choose the extra heart strategy(int the factory) out of 10
     */
    public static final int EXTRA_HEART_STRATEGY_PROBABILITY = 8;
    /**
     * the probability that we choose the double behavior strategy(int the factory) out of 10
     */
    public static final int DOUBLE_BEHAVIOR_STRATEGY_PROBABILITY = 9;
    /**
     * the first probability that we choose the extra ball strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_EXTRA_BALL_PROBABILITY_ZERO = 0;
    /**
     * the second probability that we choose the extra ball strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_EXTRA_BALL_PROBABILITY_ONE = 1;
    /**
     * the first probability that we choose the extra paddle strategy(in the double behavior strategy)
     * out of 10
     */

    public static final int DOUBLE_EXTRA_PADDLE_PROBABILITY_TWO = 2;
    /**
     * the second probability that we choose the extra paddle strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_EXTRA_PADDLE_PROBABILITY_THREE = 3;
    /**
     * the first probability that we choose the extra camera strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_CAMERA_PROBABILITY_FOUR = 4;
    /**
     * the second probability that we choose the extra camera strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_CAMERA_PROBABILITY_FIVE = 5;
    /**
     * the first probability that we choose the extra heart strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_EXTRA_HEART_PROBABILITY_SIX = 6;
    /**
     * the second probability that we choose the extra heart strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_EXTRA_HEART_PROBABILITY_SEVEN = 7;
    /**
     * the first probability that we choose the double behavior strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_BEHAVIOR_PROBABILITY_EIGHT = 8;
    /**
     * the second probability that we choose the double behavior strategy(in the double behavior strategy)
     * out of 10
     */
    public static final int DOUBLE_BEHAVIOR_PROBABILITY_NINE = 9;
    /**
     * the number of all cases of the probabilities to every brick(its behavior) after collision
     */
    public static final int NUMBER_OF_PROBABILITY_CASES = 10;
    /**
     * the x coordinate of the window dimension
     */
    public static final int WINDOW_DIMENSION_X = 700;
    /**
     * the y coordinate of the window dimension
     */
    public static final int WINDOW_DIMENSION_Y = 500;
    /**
     * the factor in the camera strategy to mult in order to  get a new window dimensions
     */
    public static final float CAMERA_FACTOR = 1.2f;

    /* A constructor */
    private Constants() {
    }
}
