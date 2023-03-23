import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Dimensions;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the Rectangle Shape.
 */
public class RectangleTest {


  Rectangle rect1;
  Rectangle rect2;
  Rectangle rect3;

  @Before
  public void init() {
    rect1 = new Rectangle("rect1", new Color(74, 232, 116),
            new Dimensions(44,33), new Position2D(0, 100));

    rect2 = new Rectangle("rect2", 3, new Color(232, 214, 74),
            new Dimensions(21,3), new Position2D(40, 90));

    rect3 = new Rectangle("rect3", 4, new Color(227, 74, 232),
            new Dimensions(31.5,20.7), new Position2D(60, 71.3));
  }

  @Test
  public void getNameTest() {
    assertEquals("rect3", rect3.getName());
  }

  @Test
  public void getLayerTest() {assertEquals(1, rect1.getLayer());}

  @Test
  public void getLayerTest2() {assertEquals(3, rect2.getLayer());}

  @Test
  public void getColorTest() {
    assertEquals(new Color (227,74,232), rect3.getColor());
  }

  @Test
  public void getXLengthTest() {
    assertEquals(31.5,rect3.getXLength(),0.1);

  }

  @Test
  public void getYLengthTest() {
    assertEquals(3.0,rect2.getYLength(),0.1);

  }

  // move
  @Test
  public void moveTest() {
    rect1.move(300,100);
    assertEquals(300.0,rect1.getXPos(), .01);
    assertEquals(100.0,rect1.getYPos(),.01);
  }

  @Test
  public void moveTestDouble() {
    rect2.move(75.1,75.6);
    assertEquals(75.1,rect2.getXPos(), .01);
    assertEquals(75.6,rect2.getYPos(),.01);

  }


  @Test (expected = IllegalArgumentException.class)
  public void moveTestBadX() {
    rect3.move(-20,50);

  }

  @Test (expected = IllegalArgumentException.class)
  public void moveTestBadY() {
    rect2.move(100,-1);
  }

  //change color
  @Test
  public void changeColorTest() {
    rect3.changeColor(new Color(255,255,51));
    assertEquals(new Color(255,255,51),rect3.getColor());
  }

  @Test (expected = IllegalArgumentException.class)
  public void changeColorBad() {
    rect2.changeColor(null);

  }

  //change size
  @Test
  public void changeSizeTest() {
    rect1.changeSize(22,4);
    assertEquals(22.0,rect1.getXLength(),0.1);
    assertEquals(4.0,rect1.getYLength(),0.1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void changeSizeBad() {
    rect2.changeSize(2,-1);

  }

  @Test
  public void toStringTest() {

    assertEquals("40.0 90.0 21.0 3.0 232 214 74",rect2.toString());

  }
}

