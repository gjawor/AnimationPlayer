package cs3500.animator.model;

import java.util.Objects;

/**
 * Class to represent an animation of a shape between a start and end tick.
 */
public class Animation implements IAnimation, Comparable<Animation> {
  private final AnimationType animationType;
  private final int startTick;
  private final int endTick;
  private Shape startShape;
  private Shape endShape;
  private int layer;


  public Animation(AnimationType animationType, int startTick, int endTick, Shape startShape,
                   Shape endShape, int layer) {
    this.animationType = animationType;
    this.startTick = startTick;
    this.endTick = endTick;
    this.startShape = startShape;
    this.endShape = endShape;
    this.layer = startShape.getLayer();

  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Animation animation = (Animation) o;
    return startTick == animation.startTick && endTick == animation.endTick &&
            Objects.equals(startShape, animation.startShape) && Objects.equals(endShape,
            animation.endShape);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startTick, endTick, startShape, endShape);
  }

  @Override
  public String toString() {
    String str = "";
    str += "motion " + this.getAnimationType() + " " + this.startShape.getName() + " " +
            this.startTick + " " + this.startShape.toString() + "     ";
    str += this.endTick + " " + this.endShape.toString() + " " + this.layer;
    return str;
  }

  @Override
  public ShapeType getShapeTypeStart() {
    return this.startShape.getShapeType();
  }

  @Override
  public ShapeType getShapeTypeEnd() {
    return this.endShape.getShapeType();
  }

  @Override
  public String getNameStart() {
    return this.startShape.getName();
  }

  @Override
  public String getNameEnd() {
    return this.endShape.getName();
  }

  @Override
  public AnimationType getAnimationType() {
    return this.animationType;
  }

  @Override
  public Shape getStartShape() {
    return this.startShape;
  }

  @Override
  public Shape getEndShape() {
    return this.endShape;
  }

  @Override
  public int getStartTick() {
    return this.startTick;
  }

  @Override
  public int getEndTick() {
    return this.endTick;
  }

  @Override
  public int getLayer() {
    return this.layer;
  }


  @Override
  public int compareTo(Animation o) {
    if (this.startTick < o.startTick) {
      return -1;
    } else if (this.startTick > o.startTick) {
      return 1;
    } else {
      return 0;
    }

  }


}
