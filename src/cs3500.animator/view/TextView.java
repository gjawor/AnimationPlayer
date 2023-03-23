package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Animation;
import cs3500.animator.model.ICanvas;

/**
 * Class to illustrate shape animations in text form.
 */
public class TextView implements View {
  ICanvas canvas;
  Appendable appendable;


  public TextView(ICanvas canvas, Appendable appendable) {
    this.canvas = canvas;
    this.appendable = appendable;

  }


  @Override
  public void renderCanvas() throws IOException {

    try {
      appendable.append(this.toString());
    } catch (IOException e) {
      throw new IOException("bad appendable");
    }


  }

  @Override
  public void renderAnimation() {

  }

  @Override
  public int getTicks() {
    return 0;
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
    return 0;
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
    return null;
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
    return null;
  }

  @Override
  public double tweening(int startTick, int endTick, double startAnimate, double endAnimate) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {

    return canvas.toString();
  }


}
