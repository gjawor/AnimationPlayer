package cs3500.animator.model;

import java.util.Objects;

/**
 * Class to represent the position of a shape in 2d coordinates.
 */
public class Position2D {
  private final double x;
  private final double y;


  public Position2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the x coord from the position.
   *
   * @return double x
   */
  public double getX() {
    return x;
  }

  /**
   * Gets the y coord from the position.
   *
   * @return double y
   */
  public double getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Position2D that = (Position2D) o;
    return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
