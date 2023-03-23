package cs3500.animator.model;

import java.awt.*;

/**
 * Class to represent a rectangle shape.
 */
public class Rectangle extends AbstractShape {


  public Rectangle(String name, Color color, Dimensions dimensions, Position2D position2D) {
    super(name, color, dimensions, position2D);


  }

  public Rectangle(String name, int layer, Color color, Dimensions dimensions, Position2D position2D) {
    super(name, layer, color, dimensions, position2D);


  }


  @Override
  public ShapeType getShapeType() {
    return ShapeType.Rectangle;
  }

}
