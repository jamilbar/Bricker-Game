package bricker.main;

import bricker.brick_strategies.*;
import bricker.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * This class extend the GameManger class. This class is responsible for initializing, and the objects
 * in the game.
 *
 */

public class BrickerGameManager extends GameManager {
    /* an instance of the Ball class*/
    private Ball ball;
    /* number of the bricks in each row*/
    private int brickPerRow;
    /* the number of hearts in the game*/
    private Counter heartNum;
    /* number of rows that have bricks*/
    private int rowsNum;
    /* a vector that represents the window dimension of the game*/
    private Vector2 windowDimensions;
    /* an instance of ArrayList interface, that have all the graphic hearts */
    private ArrayList<GameObject> heartArray;
    /* an input listener that listens to the keyboard*/
    private UserInputListener inputListener;
    /* the window controller of the game, it is instance of WindowController.*/
    private WindowController windowController;
    /* The number of bricks in the game*/
    private Counter bricksNum;
    /* an instance of NumericHeart class*/
    private NumericHeart numericHeart;
    /* an instance of GraphicHeart class*/
    private GraphicHeart graphicHeart;
    /**
     * A constructor
     * @param windowTitle the title name of the window
     * @param windowDimensions the window dimensions
     * @param brickPerRow the number of bricks in each row
     * @param rowsNum the number of rows that have bricks
     */

    public BrickerGameManager(String windowTitle, Vector2 windowDimensions,
                              int brickPerRow, int rowsNum) {
        super(windowTitle, windowDimensions);
        this.brickPerRow = brickPerRow;
        this.rowsNum = rowsNum;
        this.heartNum = new Counter(Constants.HEART_NUMBER);
        this.bricksNum = new Counter(this.brickPerRow * this.rowsNum);
    }

    /**
     * A public void method that overrides the initializeGame method in the GameManger class. This class
     * initialize tha game. creates the objects and add them.
     * @param imageReader an image reader to create an image of the objects
     * @param soundReader a SoundReader instance, to create sounds for some objects
     * @param inputListener an input listener that listens to the keyboard
     * @param windowController the window controller of the game, it is instance of WindowController.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        this.inputListener = inputListener;
        this.windowController = windowController;
        //creating ball
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        createBall(imageReader, soundReader);
        this.windowDimensions = windowController.getWindowDimensions();
        // set the ball to the center of the screen
        this.ball.setCenter(this.windowDimensions.mult(Constants.FACTOR));
        //create paddle
        createPaddle(imageReader);
        //create walls
        createWalls();
        //create background
        createBackground(imageReader);
        //ball velocity
        ballVelocity();
        //create graphic hearts
        createGraphicHearts(imageReader);
        //create brick
        createBricks(imageReader, soundReader);
        //create numeric hearts
        createNumericHeart();
    }
    /**
     * A public void method that overrides the update method of GameManger class. This method decrement the
     * heart count if the ball falls below the window, checking for win conditions, displaying text for win
     * or loss, and handling game restart or exit based on user input.
     * @param deltaTime the last update time
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float ballHeight = ball.getCenter().y();
        String prompt = "";
        if (ballHeight > this.windowDimensions.y()) {

            this.heartNum.decrement();
            if (this.heartNum.value() == 0) {
                prompt += Constants.LOSE_MESSAGE;
            } else {
                this.graphicHeart.removeHearts();
                ball.setCenter(Vector2.ZERO);
                ball.setCenter(this.windowDimensions.mult(Constants.FACTOR));
                ballVelocity();
                this.numericHeart.update(deltaTime);
            }
        }
        if (this.bricksNum.value() == 0 || this.inputListener.isKeyPressed(KeyEvent.VK_W)) {
            prompt += Constants.WIN_MESSAGE;
        }
        if (!prompt.isEmpty()) {
            prompt += Constants.RESTART_GAME_MESSAGE;
            if (this.windowController.openYesNoDialog(prompt)) {
                this.heartNum = new Counter(Constants.HEART_NUMBER);
                this.bricksNum = new Counter(this.brickPerRow * this.rowsNum);
                this.windowController.resetGame();
                ExtraPaddle.extraPaddle = false;

            } else {
                this.windowController.closeWindow();
            }
        }
    }

    /*This method set the velocity of the main ball.*/
    private void ballVelocity() {
        float ballVelocityX = Constants.BALL_SPEED;
        float ballVelocityY = Constants.BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            ballVelocityX *= -1;
        }
        if (rand.nextBoolean()) {
            ballVelocityY *= -1;
        }
        this.ball.setVelocity(new Vector2(ballVelocityX, ballVelocityY));
    }
    /*This method create the ball and add it to the game with a velocity*/
    private void createBall(ImageReader imageReader, SoundReader soundReader){
        Sound ballSound = soundReader.readSound("assets/blop_cut_silenced.wav");
        Renderable ballImage = imageReader.readImage("assets/ball.png", true);
        this.ball = new Ball(Vector2.ZERO, new Vector2(Constants.BALL_WIDTH, Constants.BALL_HEIGHT),
                ballImage, ballSound,windowDimensions, gameObjects(),
                new CameraStrategy(this, this.gameObjects(), this.windowController,
                        this.bricksNum));
        this.ball.setTag("Ball");
        gameObjects().addGameObject(ball);
        this.ball.setVelocity(Vector2.DOWN.mult(Constants.BALL_SPEED));

    }

    /*This method create the initial graphic hearts and add it to the game*/

    private void createGraphicHearts(ImageReader imageReader) {
        heartArray = new ArrayList<>();
        Renderable heartImage = imageReader.readImage("assets/heart.png", true);
        graphicHeart = new GraphicHeart(Vector2.ZERO, new Vector2(Constants.HEART_WIDTH,
                Constants.HEART_HEIGHT),heartImage, gameObjects(), heartNum, windowDimensions, heartArray);
        graphicHeart.createInitialHearts();

    }
    /*This method create the numeric hearts and add it to the game*/
    private void createNumericHeart() {
        TextRenderable text = new TextRenderable(String.valueOf(this.heartNum.value()));
        this.numericHeart = new NumericHeart(new Vector2(Constants.HEART_NUMBER_POSITION,
                this.windowDimensions.y() - Constants.NUMERIC_HEART_SPACE_FROM_BOTTOM),
                new Vector2(Constants.HEART_WIDTH, Constants.HEART_HEIGHT), text, this.heartNum);
        numericHeart.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(numericHeart, Layer.BACKGROUND);
    }
    /*This method create the paddle and add it to the game*/

    private void createPaddle(ImageReader imageReader) {
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
        Paddle paddle = new Paddle(Vector2.ZERO, new Vector2(Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT), paddleImage,
                this.inputListener, this.windowDimensions);
        paddle.setCenter(new Vector2(this.windowDimensions.x() / 2, windowDimensions.y() -
                Constants.PADDLE_SPACE_FROM_BOTTOM));
        paddle.setTag("Paddle");
        gameObjects().addGameObject(paddle);
    }

    /*This method create the walls and add it to the game*/
    private void createWalls() {
        //create right wall
        GameObject rightWall = new GameObject(new Vector2(windowDimensions.x() -
                ((float) Constants.LEFT_WALL_WIDTH / 2), 0),
                new Vector2(Constants.LEFT_WALL_WIDTH, this.windowDimensions.y()),
                null);
        gameObjects().addGameObject(rightWall);

        //create left wall
        GameObject leftWall = new GameObject(Vector2.ZERO,
                new Vector2((float) Constants.LEFT_WALL_WIDTH / 2, this.windowDimensions.y()),
                null);
        gameObjects().addGameObject(leftWall);

        //create up wall
        GameObject uptWall = new GameObject(Vector2.ZERO, new Vector2(this.windowDimensions.x(),
                Constants.UP_WALL_HEIGHT), null);
        gameObjects().addGameObject(uptWall);
    }
    /*This method create the background of the game and add it to the game*/
    private void createBackground(ImageReader imageReader) {
        Renderable backgroundImage = imageReader.readImage("assets/DARK_BG2_small.jpeg",
                false);
        GameObject background = new GameObject(Vector2.ZERO, new Vector2(this.windowDimensions.x(),
                this.windowDimensions.y()), backgroundImage);
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    /*This method create the bricks with a collision strategy and add it to the game*/
    private void createBricks(ImageReader imageReader, SoundReader soundReader) {
        Random random = new Random();
        Factory factory = new Factory(bricksNum, gameObjects(), windowDimensions,
                soundReader, imageReader, inputListener, this,
                windowController, heartNum, heartArray);

        float brickWidth = (this.windowDimensions.x() - Constants.SPACE_BETWEEN_BRICKS_WALLS * 2 -
                (Constants.SPACE_BETWEEN_BRICKS * this.brickPerRow)) / this.brickPerRow;
        int y = Constants.BRICK_HEIGHT;
        for (int i = 0; i < this.rowsNum; i++) {
            int x = Constants.SPACE_BETWEEN_BRICKS_WALLS;
            for (int j = 0; j < this.brickPerRow; j++) {
                int percentage = random.nextInt(Constants.NUMBER_OF_PROBABILITY_CASES);
                CollisionStrategy collisionStrategy = factory.buildBricks(percentage);
                Renderable brickImage = imageReader.readImage("assets/brick.png",
                        false);
                Brick brick = new Brick(new Vector2(x, y), new Vector2(brickWidth, Constants.BRICK_HEIGHT),
                        brickImage, collisionStrategy);
                brick.setTag("Brick");
                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
                x += (int) (brickWidth) + Constants.SPACE_BETWEEN_BRICKS;
            }
            y += Constants.SPACE_BETWEEN_ROWS;
        }

    }

    /**
     * The main method that run the game.
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            new BrickerGameManager("Bricker", new Vector2(Constants.WINDOW_DIMENSION_X,
                    Constants.WINDOW_DIMENSION_Y), Integer.parseInt(args[0]),
                    Integer.parseInt(args[1])).run();
        } else {
            new BrickerGameManager("Bricker", new Vector2(Constants.WINDOW_DIMENSION_X,
                    Constants.WINDOW_DIMENSION_Y),
                    Constants.DEFAULT_BRICKS_PER_ROW, Constants.DEFAULT_NUM_OF_ROWS).run();

        }
    }
}
