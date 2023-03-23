package cs3500.animator.view;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.*;

import cs3500.animator.controller.AnimationController;
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
 * Swing view with buttons to make it interactive and more complex.
 */
public class InteractiveSwing extends JFrame implements View {

  ICanvas canvas;
  Appendable appendable;
  ArrayList<Animation> appearList;

  private static JButton playB;
  private JButton rewindB;
  private JButton pauseB;
  private JButton loopB;
  private JLabel speed;
  private JRadioButton slowest;
  private JRadioButton slower;
  private JRadioButton normal;
  private JRadioButton fast;
  private JRadioButton faster;
  private JRadioButton fastest;
  private ButtonGroup speedGroup;
  private JButton submit;
  private JTextField name;
  private JTextField start;
  private JTextField end;
  private JTextField width;
  private JTextField height;
  private JTextField red;
  private JTextField green;
  private JTextField blue;
  private JTextField xpos;
  private JTextField ypos;
  private JTextField layer;

  private JRadioButton oval;
  private JRadioButton rectangle;
  private ButtonGroup shapeOptions;


  private JLabel motion1Label;
  private JRadioButton move1;
  private JRadioButton color1;
  private JRadioButton size1;
  private ButtonGroup motion1;


  private JButton yes;
  private JButton no;

  private JTextField start2;
  private JTextField end2;
  private JTextField width2;
  private JTextField height2;
  private JTextField red2;
  private JTextField green2;
  private JTextField blue2;
  private JTextField xpos2;
  private JTextField ypos2;

  private ArrayList<Animation> addedAnimations;
  static JFrame jframe;
  private JLabel pDisplay;
  public JPanel mainPanel;
  public JPanel sidePanel;
  private JScrollPane mainScrollPane;
  boolean rewindTime;
  ArrayList<Animation> originalAnimations;
  private BufferedImage bufferedImage;
  private JLabel jlabel;

  private HashMap<BufferedImage, Integer> imageLayers;
  private HashMap<BufferedImage, Integer> sortedLayers;
  private boolean lastAnimation;


  /**
   * given appendable for swing view.
   * @param canvas canvas class
   * @param appendable output
   */
  public InteractiveSwing(ICanvas canvas, Appendable appendable) {
    this.canvas = canvas;
    this.appendable = appendable;
    this.appearList = new ArrayList<Animation>();
    this.rewindTime = false;
    setup();
    originalAnimations = new ArrayList<Animation>();
    imageLayers = new HashMap<BufferedImage, Integer>();
    sortedLayers = new HashMap<BufferedImage, Integer>();
    preserveStartShapes();
    lastAnimation = false;
    addedAnimations = new ArrayList<>();

  }

  /**
   * no given appendable, just canvas.
   * @param canvas canvas class
   */
  public InteractiveSwing(ICanvas canvas) {
    this.canvas = canvas;
    this.appearList = new ArrayList<Animation>();
    rewindTime = false;
    setup();
    originalAnimations = new ArrayList<Animation>();
    imageLayers = new HashMap<BufferedImage, Integer>();
    sortedLayers = new HashMap<BufferedImage, Integer>();
    preserveStartShapes();
    lastAnimation = false;
    addedAnimations = new ArrayList<>();

  }

  /**
   * sets up the necessary background for the animation to be played on and includes buttons.
   */
  private void setup() {

    jframe = new JFrame();
    mainPanel = new JPanel();
    sidePanel = new JPanel();


    playB = new JButton("play");
    playB.setBounds(580, 100, 20, 20);
    mainPanel.add(playB);

    rewindB = new JButton("rewind");
    rewindB.setBounds(580, 150, 20, 20);
    mainPanel.add(rewindB);

    pauseB = new JButton("pause");
    pauseB.setBounds(580, 200, 20, 20);
    mainPanel.add(pauseB);

    loopB = new JButton("loop");
    loopB.setBounds(580, 250, 20, 20);
    mainPanel.add(loopB);

    speed = new JLabel("change speeds");
    mainPanel.add(speed);

    slowest = new JRadioButton(".25");
    mainPanel.add(slowest);
    slower = new JRadioButton(".50");
    mainPanel.add(slower);
    normal = new JRadioButton("1");
    mainPanel.add(normal);
    fast = new JRadioButton("1.5");
    mainPanel.add(fast);
    faster = new JRadioButton("2");
    mainPanel.add(faster);
    fastest = new JRadioButton("2.5");
    mainPanel.add(fastest);
    speedGroup = new ButtonGroup();
    speedGroup.add(slowest);
    speedGroup.add(slower);
    speedGroup.add(normal);
    speedGroup.add(fast);
    speedGroup.add(faster);
    speedGroup.add(fastest);


    // new shapes buttons
    JLabel nameLabel = new JLabel("enter name for new shape");
    name = new JTextField(10);
    nameLabel.setLabelFor(name);
    name.setHorizontalAlignment(SwingConstants.CENTER);
    mainPanel.add(nameLabel);
    mainPanel.add(name);

    oval = new JRadioButton("oval");
    mainPanel.add(oval);
    rectangle = new JRadioButton("rectangle");
    mainPanel.add(rectangle);
    shapeOptions = new ButtonGroup();
    shapeOptions.add(oval);
    shapeOptions.add(rectangle);


    JLabel startLabel = new JLabel("start time:");
    start = new JTextField(5);
    start.setHorizontalAlignment(SwingConstants.CENTER);
    startLabel.setLabelFor(start);
    mainPanel.add(startLabel);
    mainPanel.add(start);

    JLabel endLabel = new JLabel("end time:");
    end = new JTextField(5);
    endLabel.setLabelFor(end);
    mainPanel.add(endLabel);
    mainPanel.add(end);

    JLabel widthLabel = new JLabel("width:");
    width = new JTextField(5);
    widthLabel.setLabelFor(width);
    mainPanel.add(widthLabel);
    mainPanel.add(width);

    JLabel heightLabel = new JLabel("height:");
    height = new JTextField(5);
    heightLabel.setLabelFor(height);
    mainPanel.add(heightLabel);
    mainPanel.add(height);

    JLabel redLabel = new JLabel("rgb - red value:");
    red = new JTextField(5);
    redLabel.setLabelFor(red);
    mainPanel.add(redLabel);
    mainPanel.add(red);

    JLabel greenLabel = new JLabel("rgb - green value:");
    green = new JTextField(5);
    greenLabel.setLabelFor(green);
    mainPanel.add(greenLabel);
    mainPanel.add(green);

    JLabel blueLabel = new JLabel("rgb - blue value:");
    blue = new JTextField(5);
    blueLabel.setLabelFor(blue);
    mainPanel.add(blueLabel);
    mainPanel.add(blue);

    JLabel xposLabel = new JLabel("xpos:");
    xpos = new JTextField(5);
    xposLabel.setLabelFor(xpos);
    mainPanel.add(xposLabel);
    mainPanel.add(xpos);

    JLabel yposLabel = new JLabel("ypos:");
    ypos = new JTextField(5);
    yposLabel.setLabelFor(ypos);
    mainPanel.add(yposLabel);
    mainPanel.add(ypos);

    JLabel layerLabel = new JLabel("layer (optional):");
    layer = new JTextField(5);
    layerLabel.setLabelFor(layer);
    mainPanel.add(layerLabel);
    mainPanel.add(layer);


    submit = new JButton("submit");
    mainPanel.add(submit);


    JLabel motion1Label = new JLabel("select a motion to add to the new shape");

    move1 = new JRadioButton("move");
    color1 = new JRadioButton("change color");
    size1 = new JRadioButton("change size");
    motion1 = new ButtonGroup();
    motion1Label.setLabelFor(move1);
    mainPanel.add(motion1Label);
    motion1.add(move1);
    motion1.add(color1);
    motion1.add(size1);
    mainPanel.add(move1);
    mainPanel.add(size1);
    mainPanel.add(color1);
    motion1Label.setVisible(false);
    move1.setVisible(false);
    color1.setVisible(false);
    size1.setVisible(false);


    yes = new JButton("yes");
    no = new JButton("no, submit the current motion");


    JSplitPane split = new JSplitPane(SwingConstants.VERTICAL, mainPanel, sidePanel);
    split.setOrientation(SwingConstants.VERTICAL);
    jframe.add(split);
    jframe.setSize(800, 800);
    jframe.setVisible(true);


  }


  /**
   * gets the current tick from the timer in main.
   *
   * @return tick timer is on
   */
  public int getTicks() {
    int currentTick = AnimationController.currentTick;
    return currentTick;

  }

  /**
   * gets the last animation in the animation list and gets the end tick of said animation.
   *
   * @return int end tick
   */
  public int getAnimationEndTick() {
    int endAnimation = this.canvas.getAnimation().size() - 1;
    return this.canvas.getAnimation().get(endAnimation).getEndTick();
  }

  /**
   * resets the animation list and returns it to irs original start. used when rewind button
   * is pressed.
   */
  public void restoreAnimations() {

    this.appearList = new ArrayList<>();

    for (Iterator<Animation> a = this.canvas.getAnimation().iterator(); a.hasNext(); ) {
      Animation h = a.next();
      if (h.getAnimationType() == AnimationType.appear) {
        a.remove();
      }
    }

    for (Animation b : originalAnimations) {
      if (b.getAnimationType() == AnimationType.appear) {
        canvas.addAnimation(b);
      }

    }


  }


  /**
   * makes a copy of all the animations to be restored when the rewind button is pressed.
   */
  public void preserveStartShapes() {
    if (originalAnimations.size() == 0) {
      originalAnimations.addAll(this.canvas.getAnimation());
    }
  }


  /**
   * boolean indicating whether the rewind button has been pressed, stops the painting
   * of canvas in paint.
   */
  public void setRewindTime() {
    if (!rewindTime) {
      rewindTime = true;
    } else {
      rewindTime = false;
    }

  }


  @Override
  public JRadioButton slowestS() {
    return slowest;
  }

  @Override
  public JRadioButton slowerS() {
    return slower;
  }

  @Override
  public JRadioButton normalS() {
    return normal;
  }

  @Override
  public JRadioButton fastS() {
    return fast;
  }

  @Override
  public JRadioButton fasterS() {
    return faster;
  }

  @Override
  public JRadioButton fastestS() {
    return fastest;
  }

  @Override
  public JButton pause() {
    return pauseB;
  }

  @Override
  public JButton play() {
    return playB;
  }

  @Override
  public JButton rewind() {
    return rewindB;
  }

  @Override
  public JButton loop() {
    return loopB;
  }

  @Override
  public void renderCanvas() throws IOException {
  }

  @Override
  public JButton submitB() {
    return submit;
  }

  @Override
  public JButton yesB() {
    return yes;
  }

  @Override
  public JButton noB() {
    return no;
  }


  /**
   * puts the animation together to form a new shape from the information collected from the user,
   * and adds the animation to an arraylist to be added to the canvas animation list.
   *
   * @return animation list
   */
  public ArrayList<Animation> newAnimationsInfo() {


    if (oval.isSelected()) {
      Oval ovalStart = new Oval(name.getText(), determineLayer(),
              new Color(Integer.parseInt(red.getText()),
              Integer.parseInt(green.getText()), Integer.parseInt(blue.getText())),
              new Dimensions(Integer.parseInt(width.getText()), Integer.parseInt(height.getText())),
              new Position2D(Integer.parseInt(xpos.getText()), Integer.parseInt(ypos.getText())));

      Animation newA = new Animation(AnimationType.appear, Integer.parseInt(start.getText()),
              Integer.parseInt(end.getText()), ovalStart, ovalStart, ovalStart.getLayer());
      addedAnimations.add(newA);

    } else {
      Rectangle rectStart = new Rectangle((name.getText()), determineLayer(),
              new Color(Integer.parseInt(red.getText()),
              Integer.parseInt(green.getText()), Integer.parseInt(blue.getText())),
              new Dimensions(Integer.parseInt(width.getText()), Integer.parseInt(height.getText())),
              new Position2D(Integer.parseInt(xpos.getText()), Integer.parseInt(ypos.getText())));

      Animation newA = new Animation(AnimationType.appear, Integer.parseInt(start.getText()),
              Integer.parseInt(end.getText()), rectStart, rectStart, rectStart.getLayer());
      addedAnimations.add(newA);

    }

    return addedAnimations;
  }


  /**
   * adds the necessary buttons and text boxes to receive input on new shape motions.
   */
  public void motionSetup() {

    move1.setVisible(true);
    size1.setVisible(true);
    color1.setVisible(true);


    JLabel startLabel = new JLabel("start time:");
    start2 = new JTextField(5);
    startLabel.setLabelFor(start2);
    mainPanel.add(startLabel);
    mainPanel.add(start2);

    JLabel endLabel = new JLabel("end time:");
    end2 = new JTextField(5);
    endLabel.setLabelFor(end2);
    mainPanel.add(endLabel);
    mainPanel.add(end2);

    xpos2 = new JTextField(5);
    JLabel xposLabel = new JLabel("new xpos:");
    xposLabel.setLabelFor(xpos2);
    mainPanel.add(xposLabel);
    mainPanel.add(xpos2);

    ypos2 = new JTextField(5);
    JLabel yposLabel = new JLabel("new ypos:");
    yposLabel.setLabelFor(ypos2);
    mainPanel.add(yposLabel);
    mainPanel.add(ypos2);

    red2 = new JTextField(5);
    JLabel redLabel = new JLabel("new rgb - red value:");
    redLabel.setLabelFor(red2);
    mainPanel.add(redLabel);
    mainPanel.add(red2);
    red2.setVisible(true);

    green2 = new JTextField(5);
    JLabel greenLabel = new JLabel("new rgb - green value:");
    greenLabel.setLabelFor(green2);
    mainPanel.add(greenLabel);
    mainPanel.add(green2);
    green2.setVisible(true);

    blue2 = new JTextField(5);
    JLabel blueLabel = new JLabel(" new rgb - blue value:");
    blueLabel.setLabelFor(blue2);
    mainPanel.add(blueLabel);
    mainPanel.add(blue2);
    blue2.setVisible(true);

    width2 = new JTextField(5);
    JLabel widthLabel = new JLabel("new width:");
    widthLabel.setLabelFor(width2);
    mainPanel.add(widthLabel);
    mainPanel.add(width2);
    width2.setVisible(true);

    height2 = new JTextField(5);
    JLabel heightLabel = new JLabel("new height:");
    heightLabel.setLabelFor(height2);
    mainPanel.add(heightLabel);
    mainPanel.add(height2);
    height2.setVisible(true);

    JLabel addMore = new JLabel("do you want to add another motion ?");
    mainPanel.add(addMore);
    addMore.setVisible(true);

    mainPanel.add(yes);
    mainPanel.add(no);


  }


  /**
   * Collects user input about specific motion for the new shape and adds it to its own array list.
   *
   * @return list of animations for the newly created shape
   */
  public ArrayList<Animation> gatherInfo() {
    Animation getShape = addedAnimations.get(addedAnimations.size() - 1);
    Shape getShapeEnd = addedAnimations.get(addedAnimations.size() - 1).getEndShape();
    if (move1.isSelected()) {

      if (getShapeEnd.getShapeType().equals(ShapeType.Rectangle)) {
        Animation newMove = new Animation(AnimationType.move, Integer.parseInt(start2.getText()),
                Integer.parseInt(end2.getText()), getShape.getEndShape(),
                new Rectangle(getShape.getNameStart(), getShape.getLayer(), new Color(getShapeEnd.getColor().getRed(),
                        getShapeEnd.getColor().getGreen(), getShapeEnd.getColor().getBlue()),
                        new Dimensions(getShapeEnd.getXLength(), getShapeEnd.getYLength()),
                        new Position2D(Integer.parseInt(xpos2.getText()), Integer.parseInt(ypos2.getText()))
                ), getShape.getLayer());
        addedAnimations.add(newMove);

      } else {
        Animation newMove = new Animation(AnimationType.move, Integer.parseInt(start2.getText()),
                Integer.parseInt(end2.getText()), getShape.getEndShape(),
                new Oval(getShape.getNameStart(), getShape.getLayer(),
                        new Color(getShapeEnd.getColor().getRed(),
                                getShapeEnd.getColor().getGreen(), getShapeEnd.getColor().getBlue()),
                        new Dimensions(getShapeEnd.getXLength(), getShapeEnd.getYLength()),
                        new Position2D(Integer.parseInt(xpos2.getText()), Integer.parseInt(ypos2.getText()))
                ), getShape.getLayer());
        addedAnimations.add(newMove);
      }
    } else if (size1.isSelected()) {
      if (getShapeEnd.getShapeType().equals(ShapeType.Rectangle)) {
        Animation newMove = new Animation(AnimationType.changeSize, Integer.parseInt(start2.getText()),
                Integer.parseInt(end2.getText()), getShape.getEndShape(),
                new Rectangle(getShape.getNameStart(), getShape.getLayer(), new Color(getShapeEnd.getColor().getRed(),
                        getShapeEnd.getColor().getGreen(), getShapeEnd.getColor().getBlue()),
                        new Dimensions(Integer.parseInt(width2.getText()), Integer.parseInt(height2.getText())),
                        new Position2D(getShapeEnd.getXPos(), getShapeEnd.getYPos())
                ), getShape.getLayer());
        addedAnimations.add(newMove);

      } else {
        Animation newMove = new Animation(AnimationType.changeSize, Integer.parseInt(start2.getText()),
                Integer.parseInt(end2.getText()), getShape.getEndShape(),
                new Oval(getShape.getNameStart(), getShape.getLayer(), new Color(getShapeEnd.getColor().getRed(),
                        getShapeEnd.getColor().getGreen(), getShapeEnd.getColor().getBlue()),
                        new Dimensions(Integer.parseInt(width2.getText()), Integer.parseInt(height2.getText())),
                        new Position2D(getShapeEnd.getXPos(), getShapeEnd.getYPos())
                ), getShape.getLayer());
        addedAnimations.add(newMove);
      }

    } else {
      if (getShapeEnd.getShapeType().equals(ShapeType.Rectangle)) {
        Animation newMove = new Animation(AnimationType.changeColor, Integer.parseInt(start2.getText()),
                Integer.parseInt(end2.getText()), getShape.getEndShape(),
                new Rectangle(getShape.getNameStart(), getShape.getLayer(), new Color(Integer.parseInt(red2.getText()),
                        Integer.parseInt(green2.getText()), Integer.parseInt(blue2.getText())),
                        new Dimensions(getShapeEnd.getXLength(), getShapeEnd.getYLength()),
                        new Position2D(getShapeEnd.getXPos(), getShapeEnd.getYPos())
                ), getShape.getLayer());
        addedAnimations.add(newMove);

      } else {
        Animation newMove = new Animation(AnimationType.changeColor, Integer.parseInt(start2.getText()),
                Integer.parseInt(end2.getText()), getShape.getEndShape(),
                new Oval(getShape.getNameStart(), getShape.getLayer(), new Color(Integer.parseInt(red2.getText()),
                        Integer.parseInt(green2.getText()), Integer.parseInt(blue2.getText())),
                        new Dimensions(getShapeEnd.getXLength(), getShapeEnd.getYLength()),
                        new Position2D(getShapeEnd.getXPos(), getShapeEnd.getYPos())
                ), getShape.getLayer());
        addedAnimations.add(newMove);

      }

    }

    return addedAnimations;

  }


  @Override
  public void yesMoreMotions() {

    if (move1.isSelected()) {
      xpos2.setText(null);
      ypos2.setText(null);
    } else if (size1.isSelected()) {
      width2.setText(null);
      height2.setText(null);
    } else {
      red2.setText(null);
      green2.setText(null);
      blue2.setText(null);
    }

    motion1.clearSelection();
    start2.setText(null);
    end2.setText(null);

  }


  /**
   * produces the default layer to an animation that does not have a value (1) or produces the
   * given layer value.
   *
   * @return layer value >= 1
   */
  private int determineLayer() {
    if (layer.getText().isEmpty()) {
      return 1;

    } else {
      return Integer.parseInt(layer.getText());
    }
  }


  /**
   * paints the canvas with animations.
   *
   * @param graphics
   */
  public void paint(Graphics graphics) {

    super.paint(graphics);
    Graphics2D graphics2D = (Graphics2D) graphics;
    int animationSize = this.canvas.getAnimation().size();
    int i = 0;


    while (i < animationSize && !rewindTime) {


      Animation currentAnimation = canvas.getAnimation().get(i);

      Animation nextAnimation;
      if (i == this.canvas.getAnimation().size() - 1) {
        nextAnimation = null;

      } else {
        nextAnimation = canvas.getAnimation().get(i + 1);
      }


      int tick = this.getTicks();
      if (tick != 0 && nextAnimation == null) {
        lastAnimation = true;
      }
      int start = currentAnimation.getStartTick();
      int end = currentAnimation.getEndTick();

      double xpos = this.canvas.getAnimation().get(i).getStartShape().getXPos();
      double ypos = this.canvas.getAnimation().get(i).getStartShape().getYPos();
      double w = this.canvas.getAnimation().get(i).getStartShape().getXLength();
      double h = this.canvas.getAnimation().get(i).getStartShape().getYLength();
      int red = this.canvas.getAnimation().get(i).getStartShape().getColor().getRed();
      int green = this.canvas.getAnimation().get(i).getStartShape().getColor().getGreen();
      int blue = this.canvas.getAnimation().get(i).getStartShape().getColor().getBlue();
      int layer = this.canvas.getAnimation().get(i).getLayer();


      // when current tick is within an animation start and end time
      if (this.getTicks() >= currentAnimation.getStartTick() && this.getTicks() <=
              currentAnimation.getEndTick()) {

        /**
         * Appear animation type
         */

        if (this.canvas.getAnimation().get(i).getAnimationType() == AnimationType.appear) {

          drawShape(graphics2D, currentAnimation, (int) xpos, (int) ypos, (int) w,
                  (int) h, red, green, blue, layer);


          /**
           * Move, change color, change size
           */
        } else {

          this.animateShape(graphics2D, currentAnimation, nextAnimation, start, end, w, h,
                  xpos, ypos, red, green, blue);

          if (nextAnimation == null) {

          } else if (currentAnimation.getNameStart().equals(nextAnimation.getNameStart())) {
            i++;
          }

          if (this.getTicks() == currentAnimation.getEndTick()) {
            this.reappearAnimation(currentAnimation, nextAnimation);

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
        removeAppearAnimation(currentAnimation);


        if (nextAnimation != null
                && nextAnimation.getNameStart().equals(currentAnimation.getNameStart())) {

        } else {
          i--;
        }

      }

      i++;
      animationSize = this.canvas.getAnimation().size();

      if (lastAnimation) drawLayers(graphics2D);


    }
  }

  /**
   * draws shapes on the canvas in layer order.
   *
   * @param graphics2D
   */
  private void drawLayers(Graphics2D graphics2D) {
    super.paintComponents(graphics2D);
    for (Map.Entry<BufferedImage, Integer> en :
            sortedLayers.entrySet()) {

      graphics2D.drawImage(en.getKey(), 0, 0, canvas.getXLength(),
              canvas.getYLength(), null);
    }

    lastAnimation = false;
    imageLayers.clear();
    sortedLayers.entrySet().clear();

  }


  /**
   * draws the shape onto the canvas with its given specifications.
   *
   * @param graphics2D       graphics
   * @param currentAnimation animation to be drawn
   * @param xpos             xpos
   * @param ypos             ypos
   * @param w                width
   * @param h                height
   * @param red              0-255
   * @param green            0-255
   * @param blue             0-255
   */
  private void drawShape(Graphics2D graphics2D, Animation currentAnimation, int xpos, int ypos,
                         int w, int h, int red, int green, int blue, int layer) {

    bufferedImage = new BufferedImage(canvas.getXLength(), canvas.getYLength(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = bufferedImage.createGraphics();
    // sets a clear background

    g2.setColor(new Color(red, green, blue));
    if (currentAnimation.getShapeTypeStart() == ShapeType.Oval) {
      g2.fillOval(xpos, ypos, w, h);
    } else {
      g2.fillRect(xpos, ypos, w, h);
    }
    g2.setBackground(new Color(0, 0, 0, 0));
    g2.dispose();

    imageLayers.put(bufferedImage, layer);
    sortedLayers = sortLayers(imageLayers);


  }


  /**
   * sorts the layers of animations so they can be drawn in layered order.
   *
   * @param hashmap animation image and layer
   * @return sorted hashmap with image and layer
   */
  private static HashMap<BufferedImage, Integer> sortLayers(HashMap<BufferedImage, Integer> hashmap) {

    HashMap<BufferedImage, Integer> sortedMap = hashmap.entrySet().stream().sorted((int1, int2)
            -> int1.getValue().compareTo(int2.getValue())).collect(Collectors.toMap(Map.Entry::getKey,
            Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    return sortedMap;

  }

  @Override
  public void renderAnimation() {
  }

  @Override
  public double tweening(int startTick, int endTick, double startAnimate, double endAnimate) {
    int currentTick = this.getTicks();
    double tween = startAnimate * (((double) endTick - currentTick) / ((double) endTick - startTick)) + endAnimate *
            (((double) currentTick - startTick) / ((double) endTick - startTick));
    return tween;
  }

  /**
   * Removes the appear animation for a shape so it is not visible when the shape is in motion --
   * moving, changing size, changing color, or a combo of the three. Appear animation is added to
   * appearList, which holds the animation until the current motion is over.
   *
   * @param a current animation
   */
  private void removeAppearAnimation(Animation a) {
    for (int j = 0; j < this.canvas.getAnimation().size(); j++) {
      Animation appear = this.canvas.getAnimation().get(j);

      if (appear.getStartShape().getName().equals(a.getNameStart()) && appear.getAnimationType()
              == AnimationType.appear) {

        // remove from animation list
        this.canvas.removeAnimation(appear);

        // adds to appearList
        appearList.add(appear);
        j = this.canvas.getAnimation().size();
      }
    }


  }


  /**
   * Creates a new endshape to be placed as a start shape in a different animation.
   *
   * @param a current animation
   * @param b next animation overlaps
   * @return endshape to be added as a start shape in a different animation
   */
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
              animationTypeA == AnimationType.changeColor && animationTypeB
                      == AnimationType.changeSize) {


        return endShape = new Rectangle(name, new Color(redA, greenA, blueA),
                new Dimensions(wB, hB),
                new Position2D(xposA, yposA));


      } else if (animationTypeA == AnimationType.move && animationTypeB == AnimationType.changeColor
              || animationTypeA == AnimationType.changeSize && animationTypeB
              == AnimationType.changeColor) {

        return endShape = new Rectangle(name, new Color(redB, greenB, blueB),
                new Dimensions(wA, hA),
                new Position2D(xposA, yposA));

      } else {
        return endShape = new Rectangle(name, new Color(redA, greenA, blueA),
                new Dimensions(wB, hB),
                new Position2D(xposB, yposB));

      }
    } else {
      if (animationTypeA == AnimationType.move && animationTypeB == AnimationType.changeSize ||
              animationTypeA == AnimationType.changeColor && animationTypeB
                      == AnimationType.changeSize) {


        return endShape = new Oval(name, new Color(redA, greenA, blueA), new Dimensions(wB, hB),
                new Position2D(xposA, yposA));


      } else if (animationTypeA == AnimationType.move && animationTypeB == AnimationType.changeColor
              || animationTypeA == AnimationType.changeSize
              && animationTypeB == AnimationType.changeColor) {

        return endShape = new Oval(name, new Color(redB, greenB, blueB), new Dimensions(wA, hA),
                new Position2D(xposA, yposA));

      } else {
        return endShape = new Oval(name, new Color(redA, greenA, blueA),
                new Dimensions(wB, hB),
                new Position2D(xposB, yposB));

      }
    }
  }


  /**
   * removes the appear animation from the appear list and puts it back into the animation list.
   *
   * @param a animation, has same name as the appear animation.
   */
  private void reappearAnimation(Animation a, Animation b) {
    for (int g = 0; g < appearList.size(); g++) {
      Animation currentAppear = appearList.get(g);

      if (currentAppear.getStartShape().getName().equals(a.getNameStart())) {
        Animation reappear = appearList.remove(g);

        int reappearStartTick = reappear.getStartTick();
        int reappearEndTick = reappear.getEndTick();


        //need to check is there is overlap in the next animation to set the appear animation to
        // a new start shape that is indicative of both motions.
        if (b != null && a.getNameStart().equals(b.getNameStart())) {

          Shape newStartShapeOverlap = motionOverlapEndShape(a, b);
          readdAppear(a, reappearStartTick, reappearEndTick, newStartShapeOverlap);
        } else {
          Shape newStartShape = a.getEndShape();
          readdAppear(a, reappearStartTick, reappearEndTick, newStartShape);


        }

      }

    }
  }

  /**
   * adds the original appear animation back into the list of animations after its parameters have
   * been adjusted to that of the current animation
   *
   * @param a                    animation to base new params off of
   * @param reappearStartTick    int
   * @param reappearEndTick      int
   * @param newStartShapeOverlap shape parameters if 2 animations happening at the same time
   */
  private void readdAppear(Animation a, int reappearStartTick, int reappearEndTick, Shape newStartShapeOverlap) {
    Animation newAppear = new Animation(AnimationType.appear, reappearStartTick, reappearEndTick,
            newStartShapeOverlap, newStartShapeOverlap, newStartShapeOverlap.getLayer());
    this.canvas.addAnimation(newAppear);

    for (int j = 0; j < canvas.getAnimation().size(); j++) {
      Animation current = canvas.getAnimation().get(j);
      if (current.getStartTick() > a.getEndTick()
              && current.getNameStart().equals(a.getNameStart())) {

        Animation revisedAnimation = new Animation(current.getAnimationType(),
                current.getStartTick(), current.getEndTick(), newStartShapeOverlap,
                current.getEndShape(), newStartShapeOverlap.getLayer());
        canvas.removeAnimation(current);
        canvas.addAnimation(revisedAnimation);


      }

    }
  }


  /**
   * Computes where the current animation will be drawn next with what specifications. Tweens
   * the beginning and end shape or the beginning and end of the current and next animation if they
   * are happening at the same time.
   *
   * @param graphics2D       graphics needed for program
   * @param currentAnimation animation being tweened
   * @param nextAnimation    animation being tweened tooo if same shape as current
   * @param start            time for aniamtion to start
   * @param end              time for animaiton to end
   * @param w                width
   * @param h                height
   * @param xpos             x position
   * @param ypos             y position
   * @param red              0-255
   * @param green            0-255
   * @param blue             0-255
   */
  private void animateShape(Graphics2D graphics2D, Animation currentAnimation,
                            Animation nextAnimation, int start, int end,
                            double w, double h, double xpos, double ypos, int red,
                            int green, int blue) {

    int layer = currentAnimation.getLayer();
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
                (int) htweenB, (int) redtween, (int) greentween, (int) bluetween, layer);

      } else if (aType == AnimationType.move && bType == AnimationType.changeColor ||
              aType == AnimationType.changeSize && bType == AnimationType.changeColor) {
        graphics2D.setColor(new Color((int) redtweenB, (int) greentweenB, (int) bluetweenB));
        drawShape(graphics2D, currentAnimation, (int) xtween, (int) ytween, (int) wtween,
                (int) htween, (int) redtweenB, (int) greentweenB, (int) bluetweenB, layer);

      } else {
        graphics2D.setColor(new Color((int) redtween, (int) greentween, (int) bluetween));
        drawShape(graphics2D, currentAnimation, (int) xtweenB, (int) ytweenB, (int) wtween,
                (int) htween, (int) redtween, (int) greentween, (int) bluetween, layer);
      }


    } else {


      graphics2D.setColor(new Color((int) redtween, (int) greentween, (int) bluetween));
      drawShape(graphics2D, currentAnimation, (int) xtween, (int) ytween, (int) wtween,
              (int) htween, (int) redtween, (int) greentween, (int) bluetween, layer);


    }
  }


}
