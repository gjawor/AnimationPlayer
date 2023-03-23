import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationType;
import cs3500.animator.model.Dimensions;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class to test the Animation Class.
 */
public class AnimationTest {

  Oval oval1;
  Oval oval1ColorChange;
  Oval oval2;
  Oval oval3;
  Oval oval4;


  Rectangle rect1;
  Rectangle rect1Move;
  Rectangle rect2;
  Rectangle rect3;

  Animation animation1;
  Animation animation2;
  Animation animation3;
  Animation animation4;
  Animation animation5;
  Animation animation6;



  @Before
  public void init() {
    oval1 = new Oval("oval1", new Color(200,0,150),new Dimensions(3,6),
            new Position2D(200,200));
    oval1ColorChange = new Oval("oval1", new Color(120,55,125),new Dimensions(3,6),
            new Position2D(200,200));
    oval2 = new Oval("oval1", new Color(200,0,150),new Dimensions(3,6),
            new Position2D(220,200));
    oval3 = new Oval("oval3", new Color(150, 0, 200), new Dimensions(8,
            4), new Position2D(0,0));
    oval4 = new Oval("oval3", new Color(51, 255, 51), new Dimensions(8,
            4), new Position2D(0,0));


    rect1 = new Rectangle("rect1",2, new Color(255,102,102),
            new Dimensions(11,11), new Position2D(1,11));
    rect1Move = new Rectangle("rect1",2, new Color(255,102,102),
            new Dimensions(11,11), new Position2D(1,20));
    rect2 = new Rectangle("rect2",2, new Color(0,153,153),
            new Dimensions(9,7), new Position2D(200,201));
    rect3 = new Rectangle("rect3",2, new Color(0,153,153),
            new Dimensions(15.1,15.1), new Position2D(200,201));

    animation1 = new Animation(AnimationType.move,0,15,rect1,rect1Move,2);
    animation2 = new Animation(AnimationType.changeColor, 7,20,oval1,oval1ColorChange,1);
    animation3 = new Animation(AnimationType.changeSize,16,35,rect2,rect3,1);
    animation4 = new Animation(AnimationType.move,18,27,oval1,oval2,2);
    animation5 = new Animation(AnimationType.changeColor,30,32,oval3,oval4,1);
    animation6 = new Animation(AnimationType.move,0,15,rect1,rect1Move,2);

  }

  @Test
  public void getNameStartTest() {
    assertEquals("rect1", animation1.getNameStart());

  }

  @Test
  public void getNameEndTest(){
    assertEquals("rect1", animation1.getNameEnd());
  }

  @Test
  public void getAnimationTest() {
    assertEquals(AnimationType.changeColor, animation2.getAnimationType());

  }

  @Test
  public void getStartTickTest() {
    assertEquals(16, animation3.getStartTick());

  }

  @Test
  public void getEndTickTest() {
    assertEquals(27, animation4.getEndTick());

  }

  @Test
  public void getLayerTest() {
    assertEquals(2,animation1.getLayer());

  }

  @Test
  public void getShapeTypeStartTest() {
    assertEquals(ShapeType.Rectangle,animation1.getShapeTypeStart());

  }

  @Test
  public void getShapeTypeEndTest() {
    assertEquals(ShapeType.Rectangle,animation1.getShapeTypeEnd());

  }

  @Test
  public void equalsTest() {
    assertEquals(true,animation1.equals(animation1));
  }

  @Test
  public void equalsTestFalse() {
    assertEquals(false,animation4.equals(animation2));
  }


  @Test
  public void hashCodeTest() {
    assertTrue(animation1.equals(animation6));
    assertTrue(animation1.hashCode() == animation6.hashCode());
  }

  @Test
  public void compareToTest() {
    assertEquals(-1,animation1.compareTo(animation2));
  }


  @Test
  public void compareToTest2() {
    assertEquals(1,animation3.compareTo(animation2));
  }


  @Test
  public void compareToTest3() {
    assertEquals(0,animation1.compareTo(animation1));
  }


  @Test
  public void toStringTest() {
    assertEquals("motion changeSize rect2 16 200.0 201.0 9.0 7.0 0 153 153     " +
            "35 200.0 201.0 15.1 15.1 0 153 153 2", animation3.toString());
  }


}
