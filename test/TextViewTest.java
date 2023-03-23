import org.junit.Before;
import org.junit.Test;


import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import cs3500.animator.io.AnimationFileReader;
import cs3500.animator.io.ModelBuilder;
import cs3500.animator.io.TweenModelBuilder;
import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationType;
import cs3500.animator.model.Canvas;
import cs3500.animator.model.Dimensions;
import cs3500.animator.model.ICanvas;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Rectangle;
import cs3500.animator.view.FactoryView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TextViewTest {

 TweenModelBuilder<ICanvas> tweenModelBuilder;
 AnimationFileReader animationFileReader;
 TextView textView;
 ICanvas canvas;
 Appendable appendable;


 @Before
 public void init() {

  tweenModelBuilder = new ModelBuilder();
  animationFileReader = new AnimationFileReader();
  //canvas = new Canvas(300,300);
  appendable = System.out;
 // textView = new TextView(canvas,appendable);

 }

  @Test
  public void toStringTest() {

  try {
   StringBuilder stringBuilder = new StringBuilder();
   StringWriter stringWriter = new StringWriter();
   ICanvas canvas = animationFileReader.readFile("smalldemo.txt",tweenModelBuilder);
   textView = new TextView(canvas,stringWriter);
   assertEquals("canvas 400 400\n" +
           "motion appear R 1 200.0 200.0 50.0 100.0 1 0 0     30 200.0 200.0 50.0 100.0 1 0 0 2\n" +
           "motion appear C 1 100.0 100.0 60.0 30.0 7 230 53     30 100.0 100.0 60.0 30.0 7 230 53 1\n" +
           "motion move C 2 100.0 100.0 60.0 30.0 7 230 53     5 40.0 150.0 60.0 30.0 7 230 53 1\n" +
           "motion changeSize R 3 200.0 200.0 50.0 100.0 1 0 0     7 200.0 200.0 70.0 70.0 1 0 0 2\n" +
           "motion changeSize C 7 40.0 150.0 60.0 30.0 7 230 53     11 40.0 150.0 100.0 100.0 7 230 53 1\n" +
           "motion move R 10 200.0 200.0 70.0 70.0 1 0 0     14 300.0 300.0 70.0 70.0 1 0 0 2\n" +
           "motion changeColor C 13 40.0 150.0 100.0 100.0 7 230 53     19 40.0 150.0 100.0 100.0 66 152 245 1\n" +
           "motion move R 17 300.0 300.0 70.0 70.0 1 0 0     20 200.0 80.0 70.0 70.0 1 0 0 2", this.textView.toString() );
  } catch (IOException e) {
   fail();
  }


  }

  @Test
  public void renderCanvasTest() {



  try {

   StringBuilder stringBuilder = new StringBuilder();
   StringWriter stringWriter = new StringWriter();
   ICanvas canvas = animationFileReader.readFile("smalldemo.txt",tweenModelBuilder);
   textView = new TextView(canvas,stringWriter);
   textView.renderCanvas();

   assertEquals(stringWriter.toString(),"canvas 400 400\n" +
           "motion appear R 1 200.0 200.0 50.0 100.0 1 0 0     30 200.0 200.0 50.0 100.0 1 0 0 2\n" +
           "motion appear C 1 100.0 100.0 60.0 30.0 7 230 53     30 100.0 100.0 60.0 30.0 7 230 53 1\n" +
           "motion move C 2 100.0 100.0 60.0 30.0 7 230 53     5 40.0 150.0 60.0 30.0 7 230 53 1\n" +
           "motion changeSize R 3 200.0 200.0 50.0 100.0 1 0 0     7 200.0 200.0 70.0 70.0 1 0 0 2\n" +
           "motion changeSize C 7 40.0 150.0 60.0 30.0 7 230 53     11 40.0 150.0 100.0 100.0 7 230 53 1\n" +
           "motion move R 10 200.0 200.0 70.0 70.0 1 0 0     14 300.0 300.0 70.0 70.0 1 0 0 2\n" +
           "motion changeColor C 13 40.0 150.0 100.0 100.0 7 230 53     19 40.0 150.0 100.0 100.0 66 152 245 1\n" +
           "motion move R 17 300.0 300.0 70.0 70.0 1 0 0     20 200.0 80.0 70.0 70.0 1 0 0 2");


  } catch (IOException e) {
   fail();

  }


  }


}
