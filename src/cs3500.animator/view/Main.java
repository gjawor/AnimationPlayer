package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;

import cs3500.animator.io.AnimationFileReader;
import cs3500.animator.io.ModelBuilder;
import cs3500.animator.io.TweenModelBuilder;
import cs3500.animator.model.ICanvas;

/**
 * main class used as a defacto controller.
 */
public class Main {
  static int currentTick = 0;
  static int speed = 1000;
  static ICanvas canvas;
  static Appendable appendable;
  static SwingView swingView;


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


//        int finalSpeed = speed;
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
