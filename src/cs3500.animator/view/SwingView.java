package cs3500.animator.view;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationType;
import cs3500.animator.model.Dimensions;
import cs3500.animator.model.ICanvas;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;

/**
 *
 * Class to illustrate animations in motion using java swing.
 */
public class SwingView extends JFrame implements View {
  ICanvas canvas;
  Appendable appendable;
  public JPanel jPanel = new JPanel();
  ArrayList<Animation> appearList;


  public SwingView(ICanvas canvas) {
    this.canvas = canvas;
    this.appearList = new ArrayList<>();
  }


  public SwingView(ICanvas canvas, Appendable appendable) {
    this.canvas = canvas;
    this.appendable = appendable;
    this.appearList = new ArrayList<>();

  }

  @Override
  public int getTicks() {
    int currentTick = Main.currentTick;
    return currentTick;
  }


  @Override
  public void renderCanvas() throws IOException {


  }

  /**
   * Paints every shape for each tick of the timer, is repeated when repaint is called.
   *
   * @param graphics cast into 2D graphics to work with swing
   */
  public void paint(Graphics graphics) {

    super.paint(graphics);
    Graphics2D graphics2D = (Graphics2D) graphics;


    for (int i = 0; i < this.canvas.getAnimation().size(); i++) {

      Animation currentAnimation = canvas.getAnimation().get(i);

      Animation nextAnimation;
      if (i == this.canvas.getAnimation().size() - 1) {
        nextAnimation = null;
      } else {
        nextAnimation = canvas.getAnimation().get(i + 1);
      }


      int tick = this.getTicks();
      int start = currentAnimation.getStartTick();
      int end = currentAnimation.getEndTick();

      double xpos = this.canvas.getAnimation().get(i).getStartShape().getXPos();
      double ypos = this.canvas.getAnimation().get(i).getStartShape().getYPos();
      double w = this.canvas.getAnimation().get(i).getStartShape().getXLength();
      double h = this.canvas.getAnimation().get(i).getStartShape().getYLength();
      int red = this.canvas.getAnimation().get(i).getStartShape().getColor().getRed();
      int green = this.canvas.getAnimation().get(i).getStartShape().getColor().getGreen();
      int blue = this.canvas.getAnimation().get(i).getStartShape().getColor().getBlue();


      // when current tick is within an animation start and end time
      if (this.getTicks() >= currentAnimation.getStartTick() && this.getTicks() <=
              currentAnimation.getEndTick()) {

        /**
         * Appear animation type
         */

        if (this.canvas.getAnimation().get(i).getAnimationType() == AnimationType.appear) {

          drawShape(graphics2D, currentAnimation, (int) xpos, (int) ypos, (int) w,
                  (int) h, red, green, blue);


          /**
           * Move, change color, change size
           */
        } else {

          this.animateShape(graphics2D, currentAnimation, nextAnimation, start, end, w, h, xpos,
                  ypos, red, green, blue);

          if (nextAnimation == null) {

          } else if (currentAnimation.getNameStart().equals(nextAnimation.getNameStart())) {
            i++;
          }

          if (this.getTicks() == currentAnimation.getEndTick()) {
            this.reappearAnimation(currentAnimation);

            if (nextAnimation == null) {

            } else if (currentAnimation.getNameStart().equals(nextAnimation.getNameStart())) {
              i = i + 2;
            }
          }

        }
        // if one tick before start tick for animation that is not appear, remove it and put it in
        // appearList to access after motion is over
      } else if (tick + 1 == currentAnimation.getStartTick() && currentAnimation.getAnimationType()
              != AnimationType.appear) {

        removeAppearAnimation(currentAnimation, nextAnimation);


      }
    }
  }

  private void drawShape(Graphics2D graphics2D, Animation currentAnimation, int xpos, int ypos,
                         int w, int h, int red, int green, int blue) {
    if (currentAnimation.getShapeTypeStart() == ShapeType.Oval) {

      graphics2D.setColor(new Color(red, green, blue));
      graphics2D.fillOval(xpos, ypos, w, h);


    } else if (currentAnimation.getShapeTypeStart() == ShapeType.Rectangle) {
      graphics2D.setColor(new Color(red, green, blue));
      graphics2D.fillRect(xpos, ypos, w, h);


    }


  }

  public void renderAnimation() {


  }

  /**
   * tweens, or finds the current position of the shape in motion by using a formula to determine
   * the new params
   *
   * @param startTick    when animation starts
   * @param endTick      when animation ends
   * @param startAnimate start param of animation
   * @param endAnimate   end param of animation
   * @return tweened double
   */
  public double tweening(int startTick, int endTick, double startAnimate, double endAnimate) {
    int currentTick = this.getTicks();
    double tween = startAnimate * (((double) endTick - currentTick) / ((double) endTick -
            startTick)) + endAnimate * (((double) currentTick - startTick) / ((double) endTick
            - startTick));
    return tween;
  }

  /**
   * checks for overlap with next animation before removing.
   *
   * @param a current animation
   * @param b next animation to be compared, can be null
   */
  private void removeAppearAnimation(Animation a, Animation b) {
    //need to check is there is overlap in the next animation to set the appear animation to
    // a new start shape that is indicative of both motions.
    if (b != null && a.getNameStart().equals(b.getNameStart())) {

      Shape newStartShapeOverlap = motionOverlapEndShape(a, b);
      removeAppearAnimationEndShape(a, newStartShapeOverlap);

    } else {
      Shape newStartShape = a.getEndShape();
      removeAppearAnimationEndShape(a, newStartShape);

    }
  }


  private void removeAppearAnimationEndShape(Animation a, Shape endShape) {
    // finds the appear animation in the list of animations and removes it, and adds it to
    // the appearList to be put back in the motion endShape spot as its startShape spot
    // when the motion is over
    for (int j = 0; j < this.canvas.getAnimation().size(); j++) {
      Animation appear = this.canvas.getAnimation().get(j);

      if (appear.getStartShape().getName().equals(a.getNameStart()) && appear.getAnimationType()
              == AnimationType.appear) {

        // remove from animation list
        this.canvas.removeAnimation(appear);
        // change the appear shape to reappear in the new spot

        int startTick = appear.getStartTick();
        int endTick = appear.getEndTick();


        appear = new Animation(AnimationType.appear, startTick, endTick, endShape,
                endShape, appear.getLayer());

        appearList.add(appear);
        j = this.canvas.getAnimation().size();
      }
    }

  }

  private Shape motionOverlapEndShape(Animation a, Animation b) {
    ShapeType shapeTypeOverlap = a.getShapeTypeStart();
    AnimationType animationTypeA = a.getAnimationType();
    AnimationType animationTypeB = b.getAnimationType();
    Shape endShape;

    String name = a.getNameStart();
    int redA = a.getEndShape().getColor().getRed();
    int greenA = a.getEndShape().getColor().getGreen();
    int blueA = a.getEndShape().getColor().getBlue();
    int redB = b.getEndShape().getColor().getRed();
    int greenB = b.getEndShape().getColor().getGreen();
    int blueB = b.getEndShape().getColor().getBlue();
    double xposA = a.getEndShape().getXPos();
    double yposA = a.getEndShape().getYPos();
    double xposB = b.getEndShape().getXPos();
    double yposB = b.getEndShape().getYPos();
    double wA = a.getEndShape().getXLength();
    double hA = a.getEndShape().getYLength();
    double wB = b.getEndShape().getXLength();
    double hB = b.getEndShape().getYLength();


    if (shapeTypeOverlap == ShapeType.Rectangle) {
      if (animationTypeA == AnimationType.move && animationTypeB == AnimationType.changeSize ||
              animationTypeA == AnimationType.changeColor
                      && animationTypeB == AnimationType.changeSize) {


        return endShape = new Rectangle(name, new Color(redA, greenA, blueA),
                new Dimensions(wB, hB),
                new Position2D(xposA, yposA));


      } else if (animationTypeA == AnimationType.move && animationTypeB == AnimationType.changeColor
              || animationTypeA == AnimationType.changeSize
              && animationTypeB == AnimationType.changeColor) {

        return endShape = new Rectangle(name, new Color(redB, greenB, blueB),
                new Dimensions(wA, hA),
                new Position2D(xposA, yposA));

      } else {
        return endShape = new Rectangle(name, new Color(redA, greenA, blueA),
                new Dimensions(wA, hA),
                new Position2D(xposB, yposB));

      }
    } else {
      if (animationTypeA == AnimationType.move && animationTypeB == AnimationType.changeSize ||
              animationTypeA == AnimationType.changeColor
                      && animationTypeB == AnimationType.changeSize) {


        return endShape = new Oval(name, new Color(redA, greenA, blueA), new Dimensions(wB, hB),
                new Position2D(xposA, yposA));


      } else if (animationTypeA == AnimationType.move && animationTypeB == AnimationType.changeColor
              || animationTypeA == AnimationType.changeSize
              && animationTypeB == AnimationType.changeColor) {

        return endShape = new Oval(name, new Color(redB, greenB, blueB), new Dimensions(wA, hA),
                new Position2D(xposA, yposA));

      } else {
        return endShape = new Oval(name, new Color(redA, greenA, blueA),
                new Dimensions(wA, hA),
                new Position2D(xposB, yposB));

      }
    }
  }


  private void reappearAnimation(Animation a) {
    for (int g = 0; g < appearList.size(); g++) {
      Animation currentAppear = appearList.get(g);
      if (currentAppear.getStartShape().getName().equals(a.getNameStart())) {
        Animation reappear = appearList.remove(g);
        this.canvas.addAnimation(reappear);
      }

    }
  }


  private void animateShape(Graphics2D graphics2D, Animation currentAnimation,
                            Animation nextAnimation, int start, int end,
                            double w, double h, double xpos, double ypos, int red,
                            int green, int blue) {

    double wend = currentAnimation.getEndShape().getXLength();
    double hend = currentAnimation.getEndShape().getYLength();
    double wtween = tweening(start, end, w, wend);
    double htween = tweening(start, end, h, hend);

    double xend = currentAnimation.getEndShape().getXPos();
    double yend = currentAnimation.getEndShape().getYPos();
    double xtween = tweening(start, end, xpos, xend);
    double ytween = tweening(start, end, ypos, yend);

    int redend = currentAnimation.getEndShape().getColor().getRed();
    int greenend = currentAnimation.getEndShape().getColor().getGreen();
    int blueend = currentAnimation.getEndShape().getColor().getBlue();
    double redtween = tweening(start, end, red, redend);
    double greentween = tweening(start, end, green, greenend);
    double bluetween = tweening(start, end, blue, blueend);


    if (nextAnimation != null
            && currentAnimation.getNameStart().equals(nextAnimation.getNameStart())) {
      AnimationType aType = currentAnimation.getAnimationType();
      AnimationType bType = nextAnimation.getAnimationType();
      int startB = currentAnimation.getStartTick();
      int endB = currentAnimation.getEndTick();

      double xposB = nextAnimation.getStartShape().getXPos();
      double yposB = nextAnimation.getStartShape().getYPos();
      double wB = nextAnimation.getStartShape().getXLength();
      double hB = nextAnimation.getStartShape().getYLength();
      int redB = nextAnimation.getStartShape().getColor().getRed();
      int greenB = nextAnimation.getStartShape().getColor().getGreen();
      int blueB = nextAnimation.getStartShape().getColor().getBlue();

      double wendB = nextAnimation.getEndShape().getXLength();
      double hendB = nextAnimation.getEndShape().getYLength();

      double wtweenB = tweening(startB, endB, wB, wendB);
      double htweenB = tweening(startB, endB, hB, hendB);

      double xendB = nextAnimation.getEndShape().getXPos();
      double yendB = nextAnimation.getEndShape().getYPos();
      double xtweenB = tweening(startB, endB, xposB, xendB);
      double ytweenB = tweening(startB, endB, yposB, yendB);

      int redendB = nextAnimation.getEndShape().getColor().getRed();
      int greenendB = nextAnimation.getEndShape().getColor().getGreen();
      int blueendB = nextAnimation.getEndShape().getColor().getBlue();
      double redtweenB = tweening(startB, endB, redB, redendB);
      double greentweenB = tweening(startB, endB, greenB, greenendB);
      double bluetweenB = tweening(startB, endB, blueB, blueendB);


      if (aType == AnimationType.move && bType == AnimationType.changeSize ||
              aType == AnimationType.changeColor && bType == AnimationType.changeSize) {

        graphics2D.setColor(new Color((int) redtween, (int) greentween, (int) bluetween));
        drawShape(graphics2D, currentAnimation, (int) xtween, (int) ytween, (int) wtweenB,
                (int) htweenB, (int) redtween, (int) greentween, (int) bluetween);

      } else if (aType == AnimationType.move && bType == AnimationType.changeColor ||
              aType == AnimationType.changeSize && bType == AnimationType.changeColor) {
        graphics2D.setColor(new Color((int) redtweenB, (int) greentweenB, (int) bluetweenB));
        drawShape(graphics2D, currentAnimation, (int) xtween, (int) ytween, (int) wtween,
                (int) htween, (int) redtweenB, (int) greentweenB, (int) bluetweenB);

      } else {
        graphics2D.setColor(new Color((int) redtween, (int) greentween, (int) bluetween));
        drawShape(graphics2D, currentAnimation, (int) xtweenB, (int) ytweenB, (int) wtween,
                (int) htween, (int) redtween, (int) greentween, (int) bluetween);
      }


    } else {


      graphics2D.setColor(new Color((int) redtween, (int) greentween, (int) bluetween));
      drawShape(graphics2D, currentAnimation, (int) xtween, (int) ytween, (int) wtween,
              (int) htween, (int) redtween, (int) greentween, (int) bluetween);


    }
  }


  @Override
  public JButton pause() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JButton play() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JButton rewind() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JButton loop() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getAnimationEndTick() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void restoreAnimations() {

  }

  @Override
  public void preserveStartShapes() {

  }

  @Override
  public void setRewindTime() {

  }

  @Override
  public JRadioButton slowestS() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JRadioButton slowerS() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JRadioButton normalS() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JRadioButton fastS() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JRadioButton fasterS() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JRadioButton fastestS() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void motionSetup() {

  }

  @Override
  public ArrayList<Animation> newAnimationsInfo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JButton submitB() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JButton yesB() {
    throw new UnsupportedOperationException();
  }

  @Override
  public JButton noB() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void yesMoreMotions() {

  }

  @Override
  public ArrayList<Animation> gatherInfo() {
    throw new UnsupportedOperationException();
  }


}



