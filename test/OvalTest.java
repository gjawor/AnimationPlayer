import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.Dimensions;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position2D;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Oval Shape.
 */
public class OvalTest {

  Oval oval1;
  Oval oval2;
  Oval oval3;
  Oval oval4;



  @Before
  public void init() {
    oval1 = new Oval("oval1", new Color(200,0,150),new Dimensions(3,6),
            new Position2D(200,200));
    oval2 = new Oval("oval2", new Color(50,100,23),new Dimensions(7,3),
            new Position2D(100,500));
    oval3 = new Oval("oval3", 2, new Color(150, 0, 200),
            new Dimensions(8,
            4), new Position2D(0,0));
    oval4 = new Oval("oval4",3, new Color(10,200,200),
            new Dimensions(5.5,
            9.9),new Position2D(75,91));


  }

  @Test
  public void getNameTest() {
    assertEquals("oval1", oval1.getName());
  }

  @Test
  public void getLayerTest() {assertEquals(1, oval1.getLayer());}

  @Test
  public void getLayerTest2() {assertEquals(3, oval4.getLayer());}

  @Test
  public void getColorTest() {
    assertEquals(new Color (50,100,23), oval2.getColor());
  }

  @Test
  public void getXLengthTest() {
    assertEquals(3.0,oval1.getXLength(),0.1);

  }

  @Test
  public void getYLengthTest() {
    assertEquals(6.0,oval1.getYLength(),0.1);

  }

  // move
  @Test
  public void moveTest() {
    oval1.move(300,100);
    assertEquals(300.0,oval1.getXPos(), .01);
    assertEquals(100.0,oval1.getYPos(),.01);
  }

  @Test
  public void moveTestDouble() {
    oval4.move(75.1,75.6);
    assertEquals(75.1,oval4.getXPos(), .01);
    assertEquals(75.6,oval4.getYPos(),.01);

  }


  @Test (expected = IllegalArgumentException.class)
  public void moveTestBadX() {
    oval2.move(-20,50);

  }

  @Test (expected = IllegalArgumentException.class)
  public void moveTestBadY() {
    oval2.move(100,-1);
  }

  //change color
  @Test
  public void changeColorTest() {
    oval1.changeColor(new Color(255,255,51));
    assertEquals(new Color(255,255,51),oval1.getColor());
  }

  @Test (expected = IllegalArgumentException.class)
  public void changeColorBad() {
    oval3.changeColor(null);

  }

  //change size
  @Test
  public void changeSizeTest() {
    oval4.changeSize(22,4);
    assertEquals(22.0,oval4.getXLength(),0.1);
    assertEquals(4.0,oval4.getYLength(),0.1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void changeSizeBad() {
    oval3.changeSize(2,-1);

  }

  @Test
  public void toStringTest() {

    assertEquals("200.0 200.0 3.0 6.0 200 0 150",oval1.toString());

  }










}
