package cs3500.animator.model;

/**
 * Interface to represent an animation which includes an animation type, start and end tick, and
 * start and end shape.
 */
public interface IAnimation {

  /**
   * Turns an animation into a string object.
   *
   * @return animation in readable string format
   */
  String toString();


  /**
   * Gets the type of shape from the start shape.
   *
   * @return shape type enum
   */
  ShapeType getShapeTypeStart();


  /**
   * Gets the type of shape from the end shape.
   *
   * @return shape type enum
   */
  ShapeType getShapeTypeEnd();


  /**
   * gets the name of the start shape.
   *
   * @return name string
   */
  String getNameStart();

  /**
   * gets the name of the end shape.
   *
   * @return name string
   */
  String getNameEnd();

  /**
   * gets the type of animation motion.
   *
   * @return animation type enum
   */
  AnimationType getAnimationType();


  /**
   * gets the start shape of the animation (before motion).
   *
   * @return shape start
   */
  Shape getStartShape();

  /**
   * gets ths end shape of the animation (after motion).
   *
   * @return shape end
   */
  Shape getEndShape();

  /**
   * gets the starting tick of the animation.
   *
   * @return int tick
   */
  int getStartTick();

  /**
   * gets the ending tick of the animation.
   *
   * @return int tick
   */
  int getEndTick();

  /**
   * gets the layer of a shape.
   *
   * @return int greater than 0
   */
  int getLayer();

  /**
   * compares one animation to another based off their start ticks.
   *
   * @param animation animation
   * @return 0, 1, or -1 based on comparison
   */
  int compareTo(Animation animation);


}
