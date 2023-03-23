package cs3500.animator.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Class to represent a canvas and display animations on.
 */
public class Canvas implements ICanvas {
  private final int xLength;
  private final int yLength;
  private ArrayList<Animation> animation;

  public Canvas(int xLength, int yLength) {
    this.xLength = xLength;
    this.yLength = yLength;
    animation = new ArrayList<Animation>();

  }

  @Override
  public ArrayList<Animation> getAnimation() {
    return this.animation;
  }

  @Override
  public int getXLength() {
    return xLength;

  }

  @Override
  public int getYLength() {
    return yLength;
  }


  @Override
  public ShapeType getShapeType(String name) {
    int listSize = animation.size();
    if (name == null) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < listSize; i++) {
      if (name.equals(animation.get(i).getNameStart()) &&
              name.equals(animation.get(i).getNameEnd())) {
        // making the assumption shape type stays the same
        return this.animation.get(i).getShapeTypeStart();

      }
    }
    throw new IllegalArgumentException();


  }


  @Override
  public void move(String name, int startTick, int endTick, double xpos, double ypos) {
    int listSize = animation.size();
    Animation a;
    Shape s;

    if (name == null || xpos < 0 || ypos < 0 || startTick < 0 || endTick < 0) {
      throw new IllegalArgumentException();
    } else {
      Animation animationShape = shapeAnimation(name);
      int reverseAnimation = animation.size() - 1;


      for (int x = reverseAnimation; x > -1; x--) {
        Animation reverse = animation.get(x);
        if (name.equals(reverse.getNameStart()) && reverse.getEndTick()
                < startTick
                && reverse.getAnimationType() != AnimationType.appear) {
          if (animationShape.getShapeTypeStart() == ShapeType.Rectangle) {
            s = new Rectangle(animationShape.getNameStart(), reverse.getLayer(),
                    new Color(reverse.getEndShape().getColor().getRed(),
                            reverse.getEndShape().getColor().getGreen(),
                            reverse.getEndShape().getColor().getBlue()),
                    new Dimensions((reverse.getEndShape().getXLength()),
                            (reverse.getEndShape().getYLength())),
                    new Position2D(xpos, ypos));

          } else {
            s = new Oval(animationShape.getNameStart(), reverse.getLayer(),
                    new Color(reverse.getEndShape().getColor().getRed(),
                            reverse.getEndShape().getColor().getGreen(),
                            reverse.getEndShape().getColor().getBlue()),
                    new Dimensions((reverse.getEndShape().getXLength()),
                            (reverse.getEndShape().getYLength())),
                    new Position2D(xpos, ypos));
          }
          a = new Animation(AnimationType.move, startTick, endTick,
                  reverse.getEndShape(), s, reverse.getLayer());
          this.addAnimation(a);
          x = -1;
        } else if (x == 0) {
          if (animationShape.getShapeTypeStart() == ShapeType.Rectangle) {
            s = new Rectangle(animationShape.getNameStart(), animationShape.getLayer(),
                    new Color(animationShape.getStartShape().getColor().getRed(),
                            animationShape.getStartShape().getColor().getGreen(),
                            animationShape.getStartShape().getColor().getBlue()),
                    new Dimensions(animationShape.getStartShape().getXLength(),
                            animationShape.getStartShape().getYLength()),
                    new Position2D(xpos, ypos));


          } else {
            s = new Oval(animationShape.getNameStart(), animationShape.getLayer(),
                    new Color(animationShape.getStartShape().getColor().getRed(),
                            animationShape.getStartShape().getColor().getGreen(),
                            animationShape.getStartShape().getColor().getBlue()),
                    new Dimensions(animationShape.getStartShape().getXLength(),
                            animationShape.getStartShape().getYLength()),
                    new Position2D(xpos, ypos));
          }
          a = new Animation(AnimationType.move, startTick, endTick,
                  animationShape.getStartShape(), s, animationShape.getLayer());
          this.addAnimation(a);

        }
      }
    }
  }


  @Override
  public void changeColor(String name, int startTick, int endTick, Color color) {
    int listSize = animation.size();
    Animation a;
    Shape s;


    if (name == null || color == null || startTick < 0 || endTick < 0) {
      throw new IllegalArgumentException();
    } else {
      Animation animationShape = shapeAnimation(name);
      int reverseAnimation = animation.size() - 1;


      for (int x = reverseAnimation; x > -1; x--) {
        Animation reverse = animation.get(x);
        if (name.equals(reverse.getNameStart()) && reverse.getEndTick()
                < startTick
                && reverse.getAnimationType() != AnimationType.appear) {
          if (animationShape.getShapeTypeStart() == ShapeType.Rectangle) {
            s = new Rectangle(animationShape.getNameStart(), reverse.getLayer(),
                    new Color(color.getRed(), color.getGreen(), color.getBlue()),
                    new Dimensions((reverse.getEndShape().getXLength()),
                            (reverse.getEndShape().getYLength())),
                    new Position2D(reverse.getEndShape().getXPos(),
                            reverse.getEndShape().getYPos()));

          } else {
            s = new Oval(animationShape.getNameStart(), reverse.getLayer(),
                    new Color(color.getRed(), color.getGreen(), color.getBlue()),
                    new Dimensions((reverse.getEndShape().getXLength()),
                            (reverse.getEndShape().getYLength())),
                    new Position2D(reverse.getEndShape().getXPos(),
                            reverse.getEndShape().getYPos()));
          }
          a = new Animation(AnimationType.changeColor, startTick, endTick,
                  reverse.getEndShape(), s, reverse.getLayer());
          this.addAnimation(a);
          x = -1;
        } else if (x == 0) {
          if (animationShape.getShapeTypeStart() == ShapeType.Rectangle) {
            s = new Rectangle(animationShape.getNameStart(), animationShape.getLayer(),
                    new Color(color.getRed(),
                            color.getGreen(),
                            color.getBlue()),
                    new Dimensions(animationShape.getStartShape().getXLength(),
                            animationShape.getStartShape().getYLength()),
                    new Position2D(animationShape.getStartShape().getXPos(),
                            animationShape.getStartShape().getYPos()));


          } else {
            s = new Oval(animationShape.getNameStart(), animationShape.getLayer(),
                    new Color(color.getRed(),
                            color.getGreen(),
                            color.getBlue()),
                    new Dimensions(animationShape.getStartShape().getXLength(),
                            animationShape.getStartShape().getYLength()),
                    new Position2D(animationShape.getStartShape().getXPos(),
                            animationShape.getStartShape().getYPos()));
          }
          a = new Animation(AnimationType.changeColor, startTick, endTick,
                  animationShape.getStartShape(), s, animationShape.getLayer());
          this.addAnimation(a);

        }
      }
    }
  }


  @Override
  public void changeSize(String name, int startTick, int endTick, double x, double y) {
    int listSize = animation.size();
    Animation a;
    Shape s;

    if (name == null || x < 0 || y < 0 || startTick < 0 || endTick < 0) {
      throw new IllegalArgumentException();
    } else {
      Animation animationShape = shapeAnimation(name);
      int reverseAnimation = animation.size() - 1;


      for (int j = reverseAnimation; j > -1; j--) {
        Animation reverse = animation.get(j);
        if (name.equals(reverse.getNameStart()) && reverse.getEndTick()
                < startTick
                && reverse.getAnimationType() != AnimationType.appear) {
          if (animationShape.getShapeTypeStart() == ShapeType.Rectangle) {
            s = new Rectangle(animationShape.getNameStart(), reverse.getLayer(),
                    new Color(reverse.getEndShape().getColor().getRed(),
                            reverse.getEndShape().getColor().getGreen(),
                            reverse.getEndShape().getColor().getBlue()),
                    new Dimensions(x, y),
                    new Position2D(reverse.getEndShape().getXPos(),
                            reverse.getEndShape().getYPos()));

          } else {
            s = new Oval(animationShape.getNameStart(), reverse.getLayer(),
                    new Color(reverse.getEndShape().getColor().getRed(),
                            reverse.getEndShape().getColor().getGreen(),
                            reverse.getEndShape().getColor().getBlue()),
                    new Dimensions(x, y),
                    new Position2D(reverse.getEndShape().getXPos(),
                            reverse.getEndShape().getYPos()));
          }
          a = new Animation(AnimationType.changeSize, startTick, endTick,
                  reverse.getEndShape(), s, reverse.getLayer());
          this.addAnimation(a);
          j = -1;
        } else if (j == 0) {
          if (animationShape.getShapeTypeStart() == ShapeType.Rectangle) {
            s = new Rectangle(animationShape.getNameStart(), animationShape.getLayer(),
                    new Color(animationShape.getStartShape().getColor().getRed(),
                            animationShape.getStartShape().getColor().getGreen(),
                            animationShape.getStartShape().getColor().getBlue()),
                    new Dimensions(x, y),
                    new Position2D(animationShape.getStartShape().getXPos(),
                            animationShape.getStartShape().getYPos()));


          } else {
            s = new Oval(animationShape.getNameStart(), animationShape.getLayer(),
                    new Color(animationShape.getStartShape().getColor().getRed(),
                            animationShape.getStartShape().getColor().getGreen(),
                            animationShape.getStartShape().getColor().getBlue()),
                    new Dimensions(x, y),
                    new Position2D(animationShape.getStartShape().getXPos(),
                            animationShape.getStartShape().getYPos()));
          }
          a = new Animation(AnimationType.changeSize, startTick, endTick,
                  animationShape.getStartShape(), s, animationShape.getLayer());
          this.addAnimation(a);

        }
      }
    }
  }


  /**
   * adds appear shapes to a list and determines if a given shape name has an appear shape
   * and retrieves it from a list of shapes with only the appear type. this way the correct shape
   * is altered without changing any of the other animations with the same shape name.
   *
   * @param name of shape
   * @return animation object
   */
  private Animation shapeAnimation(String name) {
    ArrayList<Animation> animationAppears = new ArrayList<>();
    Animation shapeAnimate = null;
    for (int i = 0; i < animation.size(); i++) {
      if (animation.get(i).getAnimationType() == AnimationType.appear) {
        animationAppears.add(animation.get(i));
      }
    }
    for (int j = 0; j < animationAppears.size(); j++) {
      if (name.equals(animationAppears.get(j).getNameStart())) {
        shapeAnimate = animationAppears.get(j);
      }
    }
    if (shapeAnimate == null) {
      throw new IllegalArgumentException("shape does not exist");
    } else {
      return shapeAnimate;
    }
  }

  @Override
  public void addAnimation(Animation a) throws IllegalArgumentException {
    if (a == null || a.getStartShape() == null || a.getEndShape() == null || a.getLayer() <= 0) {
      throw new IllegalArgumentException();
    } else {
      for (int w = 0; w < animation.size(); w++) {
        // disallow from overlap
        if (overlap(a, animation.get(w))) {
          throw new IllegalArgumentException();
        }
      }
      this.animation.add(a);
      Collections.sort(animation);
    }
  }

  /**
   * checks to see if there is overlap between the animation trying to be added and current
   * animations.
   *
   * @param a animation to be added
   * @param b animation from animation list
   * @return boolean true if overlap, false if not
   */
  private boolean overlap(Animation a, Animation b) {
    Shape aShape = a.getStartShape();
    String aShapeName = aShape.getName();
    Shape bShape = b.getStartShape();
    String bShapeName = bShape.getName();
    AnimationType aAnimate = a.getAnimationType();
    AnimationType bAnimate = b.getAnimationType();
    int aStartTick = a.getStartTick();
    int aEndTick = a.getEndTick();
    int bStartTick = b.getStartTick();
    int bEndTick = b.getEndTick();

    if (aShapeName.equals(bShapeName) && aAnimate == bAnimate && ((aStartTick >= bStartTick &&
            aStartTick <= bEndTick) || (aEndTick >= bStartTick && aEndTick <= bEndTick))) {
      return true;
    } else {
      return false;
    }


  }


  @Override
  public void removeAnimation(Animation a) {
    for (int x = 0; x < animation.size(); x++) {
      if (a == null || a.getStartShape() == null || a.getEndShape() == null) {
        throw new IllegalArgumentException();
      } else if (this.animation.get(x).equals(a)) {
        this.animation.remove(x);
      }

    }

  }

  @Override
  public String toString() {
    String str = "";

    str += "canvas " + xLength + " " + yLength;

    for (int x = 0; x < animation.size(); x++) {
      str += "\n" + animation.get(x).toString();
    }
    return str;
  }


}
