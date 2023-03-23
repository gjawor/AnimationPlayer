package cs3500.animator.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Interface to represent a canvas to display shape animations.
 */
public interface ICanvas {

  /**
   * get the type of shape from the animation.
   *
   * @param name of shape
   * @return type of shape
   */
  ShapeType getShapeType(String name);

  /**
   * moves a shape by creating a new animation and adding it to animation list.
   *
   * @param name      of shape string
   * @param startTick start time for move
   * @param endTick   end time for move
   * @param xpos      position 2d
   * @param ypos      position 2d
   * @throws IllegalArgumentException if name null or xpos/ypos less than 0
   */
  void move(String name, int startTick, int endTick, double xpos, double ypos) throws IllegalArgumentException;

  /**
   * changes the color of a shape.
   *
   * @param name  of shape
   * @param color of shape
   * @throws IllegalArgumentException if name or color null
   */
  void changeColor(String name, int startTick, int endTick, Color color)
          throws IllegalArgumentException;

  /**
   * changes the size of a shape.
   *
   * @param name of shape
   * @param x    width
   * @param y    height
   * @throws IllegalArgumentException if name null or x/y < 0
   */
  void changeSize(String name, int startTick, int endTick, double x, double y)
          throws IllegalArgumentException;


  /**
   * gets the x length of the canvas bounds.
   *
   * @return int length
   */
  int getXLength();

  /**
   * gets the y length of the canvas bounds
   *
   * @return int length
   */
  int getYLength();

  /**
   * Gets the array list of animations from the canvas.
   *
   * @return animations array list
   */
  ArrayList<Animation> getAnimation();

  /**
   * adds an animation to list of animations.
   *
   * @param animation start and end shape and ticks
   */
  void addAnimation(Animation animation);

  /**
   * removes an animation from list of animations.
   *
   * @param animation animation
   */
  void removeAnimation(Animation animation);

  @Override
  String toString();

}
