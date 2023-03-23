package cs3500.animator.model;

import java.awt.*;

/**
 * Class to represent an oval shape.
 */
public class Oval extends AbstractShape {


  public Oval(String name, Color color, Dimensions dimensions, Position2D position2D) {
    super(name, color, dimensions, position2D);

  }

  public Oval(String name, int layer, Color color, Dimensions dimensions, Position2D position2D) {
    super(name, layer, color, dimensions, position2D);

  }

  @Override
  public ShapeType getShapeType() {
    return ShapeType.Oval;
  }

}
