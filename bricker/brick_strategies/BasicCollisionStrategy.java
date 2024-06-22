package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Counter;

/**
 * This class implements the CollisionStrategy interface. This class remove the brick from the game only when
 * a ball collide with the brick and the brick have a basic collision strategy.
 */
public class BasicCollisionStrategy implements CollisionStrategy{
    /* a game object to add and remove objects*/
    private GameObjectCollection gameObjects;
    /*the number of bricks*/
    private  Counter bricksNum;

    /**
     * A constructor
     * @param gameObjects a game object of instance GameObject
     * @param bricksNum the number of bricks
     */
    public BasicCollisionStrategy(GameObjectCollection gameObjects, Counter bricksNum) {
        this.gameObjects = gameObjects;
        this.bricksNum = bricksNum;
    }

    /**
     * A public void method that overrides the onCollision method of CollisionStrategy interface. This method
     * removes the brick from the game .
     * @param gameObject1 a game object
     * @param gameObject2 a game object.
     */
    @Override
    public void onCollision(GameObject gameObject1, GameObject gameObject2) {

        if(this.gameObjects.removeGameObject(gameObject1, Layer.STATIC_OBJECTS)){
            this.bricksNum.decrement();
        }
    }

}
