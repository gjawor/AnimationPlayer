package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.Animation;

/**
 * Interface to represent the different views to display shapes, animations, and canvases.
 */
public interface View {


  /**
   * renders the contents into a string format.
   *
   * @return string
   */
  String toString();

  /**
   * in the text view appends the string of text from the canvas information to the appendable.
   *
   * @throws IOException if the appendable is invalid
   */
  void renderCanvas() throws IOException;

  /**
   * used in the svg view to write the text in a svg format to an appendable.
   */
  void renderAnimation();

  /**
   * gets the current tick from the timer in main.
   *
   * @return tick timer is on
   */
  int getTicks();

  /**
   * used in the interactive swing view to communicate with the controller to pause the swing
   * animation.
   *
   * @return JRadioButton pause
   */
  JButton pause();

  /**
   * used in the interactive swing view to communicate with the controller to play the swing
   * animation.
   *
   * @return JRadioButton play
   */
  JButton play();

  /**
   * used in the interactive swing view to communicate with the controller to rewind/restart the
   * swing animation.
   *
   * @return JRadioButton rewind
   */
  JButton rewind();

  /**
   * used in the interactive swing view to communicate with the controller to loop the swing
   * animation.
   *
   * @return JRadioButton loop
   */
  JButton loop();


  /**
   * gets the last animation in the animation list and gets the end tick of said animation.
   *
   * @return int end tick
   */
  int getAnimationEndTick();

  /**
   * resets the animation list and returns it to irs original start. used when rewind button
   * is pressed.
   */
  void restoreAnimations();

  /**
   * makes a copy of all the animations to be restored when the rewind button is pressed.
   */
  void preserveStartShapes();

  /**
   * boolean indicating whether the rewind button has been pressed, stops the painting
   * of canvas in paint.
   */
  void setRewindTime();

  /**
   * used in the interactive swing view to communicate with the controller to play the swing
   * animation on the slowest speed.
   *
   * @return JRadioButton slowest
   */
  JRadioButton slowestS();

  /**
   * used in the interactive swing view to communicate with the controller to play the swing
   * animation on faster speed that slowest but slower than normal speed.
   *
   * @return JRadioButton slower
   */
  JRadioButton slowerS();

  /**
   * used in the interactive swing view to communicate with the controller to play the swing
   * animation on the normal speed.
   *
   * @return JRadioButton normal
   */
  JRadioButton normalS();

  /**
   * used in the interactive swing view to communicate with the controller to play the swing
   * animation on a faster than normal speed.
   *
   * @return JRadioButton fast
   */
  JRadioButton fastS();

  /**
   * used in the interactive swing view to communicate with the controller to play the swing
   * animation on a faster than fast speed but not the fastest speed.
   *
   * @return JRadioButton faster
   */
  JRadioButton fasterS();

  /**
   * used in the interactive swing view to communicate with the controller to play the swing
   * animation on the fastest speed.
   *
   * @return JRadioButton fastest
   */
  JRadioButton fastestS();

  /**
   * adds the necessary buttons and text boxes to receive input on new shape motions.
   */
  void motionSetup();


  /**
   * puts the animation together to form a new shape from the information collected from the user,
   * and adds the animation to an arraylist to be added to the canvas animation list.
   *
   * @return animation list
   */
  ArrayList<Animation> newAnimationsInfo();

  /**
   * used in the interactive swing to communicate with the controller to submit a new shape
   * animation specification.
   *
   * @return JButton submit
   */
  JButton submitB();

  /**
   * used in the interactive swing to communicate with the controller that more animations will
   * be added with the new shape, these are motion specifications.
   *
   * @return JButton yes
   */
  JButton yesB();

  /**
   * used in the interactive swing to communicate with the controller that no more animations
   * will be added to the new shape and to submit all of them to be added to the canvas arraylist
   * of animations.
   *
   * @return JButton no
   */
  JButton noB();

  /**
   * used in the interactive swing to reset the text boxes that take input for the adding of motions
   * to a new shape animation.
   */
  void yesMoreMotions();

  /**
   * Collects user input about specific motion for the new shape and adds it to its own array list.
   *
   * @return list of animations for the newly created shape
   */
  ArrayList<Animation> gatherInfo();

  /**
   * tweens, or finds the current position of the shape in motion between its start and end shape
   * by using a formula to determine the new params.
   *
   * @param startTick    when animation starts
   * @param endTick      when animation ends
   * @param startAnimate start param of animation
   * @param endAnimate   end param of animation
   * @return tweened double
   */
  double tweening(int startTick, int endTick, double startAnimate, double endAnimate);


}
