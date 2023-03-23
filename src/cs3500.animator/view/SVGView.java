package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationType;
import cs3500.animator.model.ICanvas;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.SortByLayer;

/**
 * Class to illustrate shape animations in SVG form.
 */
public class SVGView implements View {
  ICanvas canvas;
  Appendable appendable;

  public SVGView(ICanvas canvas, Appendable appendable) {
    this.canvas = canvas;
    this.appendable = appendable;
  }

  public SVGView(ICanvas canvas) {
    this.canvas = canvas;
  }


  @Override
  public void renderCanvas() throws IOException {

  }

  @Override
  public void renderAnimation() {
    try {
      String svg = "";
      svg += "<?xml version=\"1.0\" standalone=\"no\"?>\n" +
              "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \n" +
              "  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">" +
              "\n<svg width=\"" + this.canvas.getXLength() + "\" height=\"" +
              this.canvas.getYLength() +
              "\" viewBox=\"" + 0 + " " + 0 + " " + this.canvas.getXLength() + " " +
              this.canvas.getYLength() + "\" xmlns=\"http://www.w3.org/2000/svg\""
              + " xmlns:xlink= \"http://www.w3.org/1999/xlink\">" + "\n";

      sortLayers();

      for (int i = 0; i < this.canvas.getAnimation().size(); i++) {
        Animation currentAnimation = this.canvas.getAnimation().get(i);

        if (currentAnimation.getAnimationType() == AnimationType.appear) {

          if (currentAnimation.getShapeTypeStart() == ShapeType.Rectangle) {
            svg += "<rect id=\"" + currentAnimation.getNameStart() + "\" x=\"" +
                    currentAnimation.getStartShape().getXPos() + "\" y=\"" +
                    currentAnimation.getStartShape().getYPos() + "\" width=\"" +
                    currentAnimation.getStartShape().getXLength() + "\" height=\"" +
                    currentAnimation.getStartShape().getYLength() + "\" fill=\"rgb(" +
                    currentAnimation.getStartShape().getColor().getRed() + ","
                    + currentAnimation.getStartShape().getColor().getGreen() + ","
                    + currentAnimation.getStartShape().getColor().getBlue()
                    + ")\"" + " visibility=\"";

            svg = setVisibility(svg, i, currentAnimation);


            svg += "\n" + "</rect>\n";

          } else {
            svg += "<ellipse id=\"" + currentAnimation.getNameStart() + "\" cx=\"" +
                    currentAnimation.getStartShape().getXPos() + "\" cy=\"" +
                    currentAnimation.getStartShape().getYPos() + "\" rx=\"" +
                    currentAnimation.getStartShape().getXLength() + "\" ry=\"" +
                    currentAnimation.getStartShape().getYLength() + "\" fill=\"rgb(" +
                    currentAnimation.getStartShape().getColor().getRed() + ","
                    + currentAnimation.getStartShape().getColor().getGreen() + ","
                    + currentAnimation.getStartShape().getColor().getBlue() + ")\"" +
                    " visibility=\"";
            svg = setVisibility(svg, i, currentAnimation);


            svg += "\n" + "</ellipse>\n";


          }
        }

      }
      svg += "\n</svg>";
      appendable.append(svg);
    } catch (IOException e) {


    }


  }

  @Override
  public int getTicks() {
    throw new UnsupportedOperationException();
  }

  /**
   * sorts the animations by layer so that they can be generated correctly in svg view.
   */
  private void sortLayers() {

    Collections.sort(this.canvas.getAnimation(), new SortByLayer());

  }


  private String setVisibility(String svg, int i, Animation currentAnimation) {
    if (currentAnimation.getStartTick() > 1) {
      int dur = currentAnimation.getEndTick() - currentAnimation.getStartTick();
      svg += "hidden\"  >\n\t" + "<animate attributeName=\"visibility\" " +
              "attributeType=\"XML\"" + "\n\t\t" +
              "begin=\"" + currentAnimation.getStartTick() + "s\" dur=\"" + dur +
              "\" fill=\"freeze\" from=\"hidden\" to=\"visible" + "\"  />";

    } else {
      svg += "visible\"  >";

    }


    svg = this.animateShapes(svg, currentAnimation, i);
    return svg;
  }


  /**
   * creates animations for each shape individually in time order.
   *
   * @param svg              current string
   * @param currentAnimation animation to be added, can't be null
   * @param i                for loop integer
   * @return animation to be added to shape
   */
  private String animateShapes(String svg, Animation currentAnimation, int i) {
    for (int j = i + 1; j < this.canvas.getAnimation().size(); j++) {
      Animation nextAnimation = this.canvas.getAnimation().get(j);
      int duration = nextAnimation.getEndTick() - nextAnimation.getStartTick();
      if (currentAnimation.getNameStart().equals(nextAnimation.getNameStart())) {

        if (nextAnimation.getAnimationType() == AnimationType.move
                && nextAnimation.getShapeTypeStart() == ShapeType.Rectangle) {

          svg += "\n\t" + "<animate attributeName=\"x\" attributeType=\"XML\"" + "\n\t\t" +
                  "begin=\"" + nextAnimation.getStartTick() + "s\" dur=\"" + duration +
                  "\" fill=\"freeze\" from=\"" + nextAnimation.getStartShape().getXPos() +
                  "\" to=\"" + nextAnimation.getEndShape().getXPos() + "\"  />";
          svg += "\n\t" + "<animate attributeName=\"y\" attributeType=\"XML\"" + "\n\t\t" +
                  "begin=\"" + nextAnimation.getStartTick() + "s\" dur=\"" + duration +
                  "\" fill=\"freeze\" from=\"" + nextAnimation.getStartShape().getYPos() +
                  "\" to=\"" + nextAnimation.getEndShape().getYPos() + "\"  />";

        } else if (nextAnimation.getAnimationType() == AnimationType.changeSize
                && nextAnimation.getShapeTypeStart() == ShapeType.Rectangle) {
          svg += "\n\t" + "<animate attributeName=\"width\" attributeType=\"XML\"" + "\n\t\t"
                  + "begin=\""
                  + nextAnimation.getStartTick() + "s\" dur=\"" + duration +
                  "\" fill=\"freeze\" from=\"" + nextAnimation.getStartShape().getXLength() +
                  "\" to=\"" + nextAnimation.getEndShape().getXLength() + "\"  />";
          svg += "\n\t" + "<animate attributeName=\"height\" attributeType=\"XML\"" + "\n\t\t" +
                  "begin=\"" + nextAnimation.getStartTick() + "s dur=" + duration +
                  "\" fill=\"freeze\" from=\"" + nextAnimation.getStartShape().getYLength() +
                  "\" to=\"" + nextAnimation.getEndShape().getYLength() + "\"  />";

        } else if (nextAnimation.getAnimationType() == AnimationType.move
                && nextAnimation.getShapeTypeStart() == ShapeType.Oval) {

          svg += "\n\t" + "<animate attributeName=\"cx\" attributeType=\"XML\"" + "\n\t\t" +
                  "begin=\"" + nextAnimation.getStartTick() + "s\" dur=\"" + duration +
                  "\" fill=\"freeze\" from=\"" + nextAnimation.getStartShape().getXPos() +
                  "\" to=\"" + nextAnimation.getEndShape().getXPos() + "\"  />";
          svg += "\n\t" + "<animate attributeName=\"cy\" attributeType=\"XML\"" + "\n\t\t" +
                  "begin=\"" + nextAnimation.getStartTick() + "s\" dur=\"" + duration +
                  "\" fill=\"freeze\" from=\"" + nextAnimation.getStartShape().getYPos() +
                  "\" to=\"" + nextAnimation.getEndShape().getYPos() + "\"  />";

        } else if (nextAnimation.getAnimationType() == AnimationType.changeSize
                && nextAnimation.getShapeTypeStart() == ShapeType.Oval) {
          svg += "\n\t" + "<animate attributeName=\"rx\" attributeType=\"XML\"" + "\n\t\t"
                  + "begin=\""
                  + nextAnimation.getStartTick() + "s\" dur=\"" + duration +
                  "\" fill=\"freeze\" from=\"" + nextAnimation.getStartShape().getXLength() +
                  "\" to=\"" + nextAnimation.getEndShape().getXLength() + "\"  />";
          svg += "\n\t" + "<animate attributeName=\"ry\" attributeType=\"XML\"" + "\n\t\t" +
                  "begin=\"" + nextAnimation.getStartTick() + "s dur=" + duration +
                  "\" fill=\"freeze\" from=\"" + nextAnimation.getStartShape().getYLength() +
                  "\" to=\"" + nextAnimation.getEndShape().getYLength() + "\"  />";

        } else {
          svg += "\n\t" + "<animate attributeName=\"fill\" attributeType=\"XML\"" + "\n\t\t" +
                  "begin=\"" +
                  nextAnimation.getStartTick() + "s\" dur=\"" + duration + "\" from=\"rgb(" +
                  nextAnimation.getStartShape().getColor().getRed() + ","
                  + nextAnimation.getStartShape().getColor().getGreen() + ","
                  + nextAnimation.getStartShape().getColor().getBlue() + ")\" to=\"rgb(" +
                  nextAnimation.getEndShape().getColor().getRed() + ","
                  + nextAnimation.getEndShape().getColor().getGreen() + ","
                  + nextAnimation.getEndShape().getColor().getBlue() + ")\"  />";


        }
      }
    }
    return svg;

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

  @Override
  public double tweening(int startTick, int endTick, double startAnimate, double endAnimate) {
    throw new UnsupportedOperationException();
  }

}
