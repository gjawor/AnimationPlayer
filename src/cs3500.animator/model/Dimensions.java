package cs3500.animator.model;

import java.util.Objects;

/**
 * Class to represent the dimensions of a shape (height/width or radius/radius).
 */
public class Dimensions {
  private final double height;
  private final double width;

  public Dimensions(double width, double height) {
    this.height = height;
    this.width = width;
  }

  /**
   * Gets the height of the dimension.
   *
   * @return double height
   */
  public double getHeight() {
    return height;
  }

  /**
   * Gets the width of the dimension.
   *
   * @return double width
   */
  public double getWidth() {
    return width;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Dimensions that = (Dimensions) o;
    return Double.compare(that.height, height) == 0 && Double.compare(that.width, width) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, width);
  }
}


