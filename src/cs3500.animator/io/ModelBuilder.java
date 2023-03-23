package cs3500.animator.io;

import java.awt.*;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationType;
import cs3500.animator.model.Canvas;
import cs3500.animator.model.Dimensions;
import cs3500.animator.model.ICanvas;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Rectangle;

/**
 * Class to build the model from the input file.
 */
public class ModelBuilder implements TweenModelBuilder {
  ICanvas canvas;


  @Override
  public ICanvas getCanvas() {
    return canvas;
  }

  @Override
  public TweenModelBuilder setBounds(int width, int height) {
    canvas = new Canvas(width, height);
    return this;
  }

  @Override
  public TweenModelBuilder addOval(int layer, String name, float cx, float cy, float xRadius,
                                   float yRadius, float red, float green, float blue,
                                   int startOfLife, int endOfLife) {

    if (red < 1 && green < 1 && blue < 1) {
      canvas.addAnimation(new Animation(AnimationType.appear, startOfLife, endOfLife, new Oval(name,
              layer, new Color((int) Math.round(red * 255), (int) Math.round(green * 255),
              (int) Math.round(blue * 255)), new Dimensions(xRadius, yRadius),
              new Position2D(cx, cy)),
              new Oval(name, layer, new Color((int) red, (int) green, (int) blue),
                      new Dimensions(xRadius, yRadius), new Position2D(cx, cy)), layer));

    } else {
      canvas.addAnimation(new Animation(AnimationType.appear, startOfLife, endOfLife, new Oval(name,
              layer, new Color((int) red, (int) green, (int) blue), new Dimensions(xRadius, yRadius),
              new Position2D(cx, cy)),
              new Oval(name, layer, new Color((int) red, (int) green, (int) blue),
                      new Dimensions(xRadius, yRadius), new Position2D(cx, cy)), layer));

    }

    return this;
  }


  @Override
  public TweenModelBuilder addRectangle(int layer, String name, float lx, float ly, float width, float height,
                                        float red, float green, float blue, int startOfLife,
                                        int endOfLife) {

    if (red < 1 && green < 1 && blue < 1) {
      canvas.addAnimation(new Animation(AnimationType.appear, startOfLife, endOfLife,
              new Rectangle(name, layer,
                      new Color((int) Math.round(red * 255), (int) Math.round(green * 255),
                              (int) Math.round(blue * 255)), new Dimensions(width, height),
                      new Position2D(lx, ly)),
              new Rectangle(name, layer, new Color((int) red, (int) green, (int) blue),
                      new Dimensions(width, height), new Position2D(lx, ly)), layer));
      return this;

    } else {
      canvas.addAnimation(new Animation(AnimationType.appear, startOfLife, endOfLife,
              new Rectangle(name, layer,
                      new Color((int) red, (int) green, (int) blue), new Dimensions(width, height),
                      new Position2D(lx, ly)),
              new Rectangle(name, layer, new Color((int) red, (int) green, (int) blue),
                      new Dimensions(width, height), new Position2D(lx, ly)), layer));
      return this;
    }
  }


  @Override
  public TweenModelBuilder addMove(String name, float moveFromX, float moveFromY, float moveToX,
                                   float moveToY, int startTime, int endTime) {
    canvas.move(name, startTime, endTime, moveToX, moveToY);
    return this;
  }

  @Override
  public TweenModelBuilder addColorChange(String name, float oldR, float oldG, float oldB,
                                          float newR, float newG, float newB, int startTime,
                                          int endTime) {

    if (newR < 1 && newG < 1 && newB < 1) {
      canvas.changeColor(name, startTime, endTime, new Color((int) Math.round(newR * 255),
              (int) Math.round(newG * 255), (int) Math.round(newB * 255)));
      return this;

    } else {
      canvas.changeColor(name, startTime, endTime, new Color((int) newR, (int) newG, (int) newB));
      return this;

    }

  }

  @Override
  public TweenModelBuilder addScaleToChange(String name, float fromSx, float fromSy,
                                            float toSx, float toSy, int startTime, int endTime) {
    canvas.changeSize(name, startTime, endTime, toSx, toSy);
    return this;
  }

  @Override
  public Object build() {
    return canvas;
  }
}
