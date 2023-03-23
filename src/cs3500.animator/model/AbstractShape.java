package cs3500.animator.model;

import java.awt.*;
import java.util.Objects;

/**
 * abstract class to represent a shape and eliminate duplicate code.
 */
public abstract class AbstractShape implements Shape {
  private String name;
  private Color color;
  private Position2D position2D;
  private Dimensions dimensions;
  private final int layer;


  public AbstractShape(String name, Color color, Dimensions dimensions, Position2D position2D) {
    this.name = name;
    this.color = color;
    this.dimensions = dimensions;
    this.position2D = position2D;
    this.layer = 1;


  }

  public AbstractShape(String name, int layer, Color color, Dimensions dimensions, Position2D position2D) {
    this.name = name;
    this.layer = layer;
    this.color = color;
    this.dimensions = dimensions;
    this.position2D = position2D;


  }


  @Override
  public String getName() {
    return this.name;
  }


  public abstract ShapeType getShapeType();


  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public double getXLength() {
    return this.dimensions.getWidth();
  }

  @Override
  public double getYLength() {
    return this.dimensions.getHeight();
  }

  @Override
  public double getXPos() {
    return this.position2D.getX();
  }

  @Override
  public double getYPos() {
    return this.position2D.getY();
  }

  @Override
  public int getLayer() {
    return this.layer;
  }


  @Override
  public void move(double xpos, double ypos) {
    if (xpos < 0 || ypos < 0) {
      throw new IllegalArgumentException();
    } else {
      this.position2D = new Position2D(xpos, ypos);
    }
  }

  @Override
  public void changeColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException();
    } else {
      this.color = color;
    }

  }

  @Override
  public void changeSize(double width, double height) {

    if (width < 0 || height < 0) {
      throw new IllegalArgumentException();
    } else {
      this.dimensions = new Dimensions(width, height);

    }

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractShape that = (AbstractShape) o;
    return Objects.equals(name, that.name) && Objects.equals(color, that.color)
            && Objects.equals(position2D, that.position2D) &&
            Objects.equals(dimensions, that.dimensions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, color, position2D, dimensions);
  }

  @Override
  public String toString() {
    String str = "";
    str += this.position2D.getX() + " " + this.position2D.getY() + " " +
            this.dimensions.getWidth() + " " + this.dimensions.getHeight() + " " +
            this.color.getRed() + " " + this.color.getGreen() + " " + this.color.getBlue();
    return str;
  }


}
