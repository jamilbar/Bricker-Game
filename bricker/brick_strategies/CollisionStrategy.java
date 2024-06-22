package bricker.brick_strategies;

import danogl.GameObject;

/**
 * An interface that have one public method, all the classes that implement this interface have to implement
 * the onCollision methods
 */
public interface CollisionStrategy {
     /**
      * A method that call when a collision occurs.
      * @param gameObject1 a game object that collide with another game object
      * @param gameObject2  a game object that collided with the other game object.
      */
     void onCollision(GameObject gameObject1, GameObject gameObject2);
}
