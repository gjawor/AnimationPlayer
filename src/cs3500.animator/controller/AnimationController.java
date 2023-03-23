package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import cs3500.animator.io.AnimationFileReader;
import cs3500.animator.io.ModelBuilder;
import cs3500.animator.io.TweenModelBuilder;
import cs3500.animator.model.Animation;
import cs3500.animator.model.ICanvas;
import cs3500.animator.view.FactoryView;
import cs3500.animator.view.InteractiveSwing;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.SwingView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.View;
import cs3500.animator.view.ViewType;

/**
 * Controller class to create the user's desired view and handle button controls.
 */
public class AnimationController {
  public static int currentTick = 0;
  static int speed = 1000;
  static ICanvas canvas;
  static Appendable appendable;
  static SwingView swingView;
  static InteractiveSwing interactiveSwing;
  public static Timer timer;
  public static boolean loopOn = false;
  private View view;


  public AnimationController(View view) {
    this.view = view;

  }


  /**
   * contains an action listener to speed up the timer to be extremely fast and thus the animation
   * motion sequences.
   */
  public static void fastestButtonListener() {
    interactiveSwing.fastestS().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.setDelay(250);
        timer.restart();
      }
    });
  }

  /**
   * contains an action listener to speed up the timer to be faster and thus the animation
   * motion sequences.
   */
  public static void fasterButtonListener() {
    interactiveSwing.fasterS().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.setDelay(500);
        timer.restart();

      }
    });
  }


  /**
   * contains an action listener to speed up the timer and thus the animation motion sequences.
   */
  public static void fastButtonListener() {
    interactiveSwing.fastS().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.setDelay(750);
        timer.restart();

      }
    });
  }


  /**
   * contains an action listener to set the timer to a normal speed of 1 tick per second
   * for the animation motion sequences.
   */
  public static void normalButtonListener() {
    interactiveSwing.normalS().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.setDelay(1000);
        timer.restart();


      }
    });
  }

  /**
   * contains an action listener to slow down the timer slightly and thus slow the animation
   * motion sequences.
   */
  public static void slowerButtonListener() {
    interactiveSwing.slowerS().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.setDelay(2000);
        timer.restart();

      }
    });
  }


  /**
   * contains an action listener to majorly slow down the timer and thus slow the animation
   * motion sequences.
   */
  public static void slowestButtonListener() {
    interactiveSwing.slowestS().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.setDelay(2500);
        timer.restart();

      }
    });
  }


  /**
   * contains an action listener when the pause button is pressed to pause the animation motion
   * sequences.
   */
  public static void pauseButtonListener() {
    interactiveSwing.pause().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.stop();


      }
    });
  }

  /**
   * contains an action listener, when the play button is pressed the animation sequences play.
   */
  public static void playButtonListener() {
    interactiveSwing.play().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.start();
      }
    });

  }

  /**
   * contains an action listener, when the rewind button is pressed restarts the animation motion
   * sequences from the beginning.
   */
  public static void rewindButtonListener() {
    interactiveSwing.rewind().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {


        timer.stop();
        interactiveSwing.setRewindTime();
        interactiveSwing.restoreAnimations();
        currentTick = 0;

        timer.start();
        interactiveSwing.setRewindTime();


      }
    });
  }

  /**
   * contains an action listener, when the loop button is pressed the animation sequences will
   * start over again from the beginning once it ends.
   */
  public static void loopButtonListener() {
    interactiveSwing.loop().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        if (!loopOn) {
          loopOn = true;
        } else {
          loopOn = false;
        }

      }
    });

  }

  /**
   * when loop button is activated, this method waits for the last tick then restores the animation
   * sequences and restarts the timer.
   */
  public static void loopAnimation() {
    int lastTick = interactiveSwing.getAnimationEndTick();
    if (loopOn && currentTick == lastTick) {
      interactiveSwing.restoreAnimations();
      currentTick = 0;

    }

  }


  /**
   * contains an action listener, when submit button is pressed for adding a new shape animation
   * the info is stored and more buttons and text boxes are revealed to add motion to the new
   * shape animation.
   */
  public static void submitButtonListener() {
    interactiveSwing.submitB().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // store info on new shape to arraylist
        interactiveSwing.newAnimationsInfo();

        // setups up the next buttons and text boxes for inserting motions
        interactiveSwing.motionSetup();


      }
    });
  }


  /**
   * contains an action listener, when yes button is pressed gathers user input on motion and
   * resets the text boxes and buttons to add another new motion.
   */
  public static void yesButtonListener() {
    interactiveSwing.yesB().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        interactiveSwing.gatherInfo();
        interactiveSwing.yesMoreMotions();

      }
    });
  }

  /**
   * contains an action listener, when no button is pressed gathers the info from the user input on
   * motion and adds all the inputted motions to the canvas animation list.
   */
  public static void noButtonListener() {
    interactiveSwing.noB().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ArrayList<Animation> newMotions = interactiveSwing.gatherInfo();

        for (Animation newAnimation : newMotions) {
          canvas.addAnimation(newAnimation);
        }
      }
    });
  }


  /**
   * main that uses a scanner to sort through input and set up the desired view, speed, and
   * output as specified.
   *
   * @param args in (file), view, speed (optional), output (optional)
   */
  public static void main(String[] args) {
    AnimationFileReader animationFileReader = new AnimationFileReader();
    appendable = System.out;
    TweenModelBuilder<ICanvas> tweenModelBuilder = new ModelBuilder();
    StringBuilder stringBuilder = new StringBuilder();


    HashMap<String, String> hashMap = new HashMap<>();

    for (int i = 0; i < args.length; i += 2) {
      hashMap.put(args[i], args[i + 1]);
    }


    hashMap.putIfAbsent("-speed", "1000");

    int speed = Integer.parseInt(hashMap.get("-speed"));

    if (hashMap.containsKey("-out")) {
      appendable = stringBuilder;

    } else {
      appendable = System.out;
    }


    try {
      animationFileReader.readFile(hashMap.get("-in"), tweenModelBuilder);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    if (hashMap.containsValue("text")) {

      TextView textView = (TextView) FactoryView.createView(ViewType.Text,
              (ICanvas) tweenModelBuilder.build(), appendable);
      try {
        textView.renderCanvas();
      } catch (IOException e) {
        e.printStackTrace();
      }


    } else if (hashMap.containsValue("svg")) {
      SVGView svgView = (SVGView) FactoryView.createView(ViewType.SVG,
              (ICanvas) tweenModelBuilder.build(), appendable);
      svgView.renderAnimation();


      // swing
    } else if (hashMap.containsValue("visual")) {

      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          canvas = (ICanvas) tweenModelBuilder.build();
          int x = canvas.getXLength();
          int y = canvas.getYLength();
          swingView = (SwingView) FactoryView.createView(ViewType.Swing,
                  canvas, appendable);


          swingView.setDefaultCloseOperation(swingView.EXIT_ON_CLOSE);
          swingView.setSize(x, y);


          swingView.getContentPane().add(swingView.jPanel);
          swingView.setVisible(true);


        }
      });
      int finalSpeed = speed;
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          Timer timer = new Timer(finalSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              currentTick++;
              swingView.repaint();


            }
          });


          timer.start();

        }
      });


      /// interactive swing
    } else if (hashMap.containsValue("interactive")) {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          canvas = (ICanvas) tweenModelBuilder.build();
          int x = canvas.getXLength();
          int y = canvas.getYLength();
          interactiveSwing = (InteractiveSwing) FactoryView.createView(ViewType.Interactive,
                  canvas, appendable);


          interactiveSwing.setDefaultCloseOperation(interactiveSwing.EXIT_ON_CLOSE);
          interactiveSwing.setSize(x, y);

          interactiveSwing.setVisible(true);
          playButtonListener();
          pauseButtonListener();
          rewindButtonListener();
          loopButtonListener();
          slowestButtonListener();
          slowerButtonListener();
          normalButtonListener();
          fastButtonListener();
          fasterButtonListener();
          fastestButtonListener();
          submitButtonListener();
          yesButtonListener();
          noButtonListener();


        }
      });

      int finalSpeed = speed;
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          timer = new Timer(finalSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              currentTick++;
              interactiveSwing.repaint();
              loopAnimation();


            }
          });


        }
      });

    }

    if (hashMap.containsKey("-out")) {
      try {
        FileWriter myWriter = new FileWriter(hashMap.get("-out"));
        myWriter.write(stringBuilder.toString());
        myWriter.close();
        System.out.println("Successfully wrote to the file.");

      } catch (IOException e) {
        e.printStackTrace();
      }
    }


  }
}






