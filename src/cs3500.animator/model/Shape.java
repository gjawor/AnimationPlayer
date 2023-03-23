package cs3500.animator.model;

import java.awt.*;

/**
 * Interface to represent a shape. Shapes have a shape type, name, color, position, and dimensions.
 */
public interface Shape {

  /**
   * gets the name of the shape.
   *
   * @return name string
   */
  String getName();


  /**
   * gets the type of shape , specific to each shape class.
   *
   * @return shapetype enum
   */
  ShapeType getShapeType();


  /**
   * gets the color of the shape.
   *
   * @return color rgb style
   */
  Color getColor();


  /**
   * gets the length of x (width/radius).
   *
   * @return length x int
   */
  double getXLength();


  /**
   * gets the length of y (height/radius).
   *
   * @return length y int
   */
  double getYLength();


  /**
   * gets the x coordinate of a shape's position.
   *
   * @return x position double must be greater than 0
   */
  double getXPos();


  /**
   * gets the y coordinate of a shape's position.
   *
   * @return y position double must be greater than 0
   */
  double getYPos();


  /**
   * gets the layer of a shape for placement on canvas in relation to other shapes.
   *
   * @return layer int must be greater than 0
   */
  int getLayer();


  /**
   * moves a shape to a new position.
   *
   * @param x double position 2d
   * @param y double position 2d
   * @throws IllegalArgumentException if x or y is less than 0
   */
  void move(double x, double y) throws IllegalArgumentException;


  /**
   * changes the color of a shape.
   *
   * @param color 3 int rgb value
   * @throws IllegalArgumentException if color is null
   */
  void changeColor(Color color) throws IllegalArgumentException;


  /**
   * changes the size of a shape.
   *
   * @param x length double
   * @param y length double
   * @throws IllegalArgumentException if x or y less than 0
   */
  void changeSize(double x, double y) throws IllegalArgumentException;


  /**
   * altered toString method to create text output of a shape.
   *
   * @return shape string
   */
  String toString();


}
