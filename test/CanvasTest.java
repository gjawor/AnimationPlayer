import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.Animation;
import cs3500.animator.model.AnimationType;
import cs3500.animator.model.Canvas;
import cs3500.animator.model.Dimensions;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Class to test the Canvas Class.
 */
public class CanvasTest {

  Canvas canvas;
  int xLength;
  int yLength;

  Oval oval1;
  Oval oval1ColorChange;
  Oval oval2;
  Oval oval3;
  Oval oval4;

  Rectangle rect1;
  Rectangle rect1Move;
  Rectangle rect2;
  Rectangle rect3;

  Animation appearOval1;
  Animation appearOval3;
  Animation appearRect1;
  Animation appearRect3;
  Animation animation1;


  @Before
  public void init() {

    xLength = 350;
    yLength = 350;

    oval1 = new Oval("oval1", new Color(200, 0, 150),
            new Dimensions(3, 6),
            new Position2D(200, 200));
    oval1ColorChange = new Oval("oval1", new Color(120, 55, 125),
            new Dimensions(3, 6),
            new Position2D(200, 200));
    oval2 = new Oval("oval1", new Color(200, 0, 150),
            new Dimensions(3, 6),
            new Position2D(220, 200));
    oval3 = new Oval("oval3", new Color(150, 0, 200), new Dimensions(8,
            4), new Position2D(0, 0));
    oval4 = new Oval("oval3", new Color(51, 255, 51), new Dimensions(8,
            4), new Position2D(0, 0));


    rect1 = new Rectangle("rect1", 2, new Color(255, 102, 102),
            new Dimensions(11, 11), new Position2D(1, 11));
    rect1Move = new Rectangle("rect1", 2, new Color(255, 102, 102),
            new Dimensions(11, 11), new Position2D(1, 20));
    rect2 = new Rectangle("rect3", 2, new Color(0, 153, 153),
            new Dimensions(9, 7), new Position2D(200, 201));
    rect3 = new Rectangle("rect3", 2, new Color(0, 153, 153),
            new Dimensions(15.1, 15.1), new Position2D(200, 201));


    appearOval1 = new Animation(AnimationType.appear, 0, 40, oval1, oval1, 1);
    appearOval3 = new Animation(AnimationType.appear, 0, 40, oval3, oval3, 1);
    appearRect1 = new Animation(AnimationType.appear, 0, 40, rect1, rect1, 2);
    appearRect3 = new Animation(AnimationType.appear, 0, 40, rect3, rect3, 2);
    animation1 = new Animation(AnimationType.move, 0, 15, rect1, rect1Move, 2);


    canvas = new Canvas(xLength, yLength);
    canvas.addAnimation(appearOval1);
    canvas.addAnimation(appearOval3);
    canvas.addAnimation(appearRect1);
    canvas.addAnimation(appearRect3);

  }

  @Test
  public void getAnimationTest() {
    assertTrue(!canvas.getAnimation().isEmpty());
  }

  @Test
  public void getXTest() {
    assertEquals(350, canvas.getXLength());
  }

  @Test
  public void getYTest() {
    assertEquals(350, canvas.getYLength());
  }


  @Test
  public void getShapeTypeTest() {
    assertEquals(ShapeType.Rectangle,
            canvas.getShapeType("rect1"));
  }


  @Test(expected = IllegalArgumentException.class)
  public void getShapeTypeBad() {
    canvas.getShapeType("swag");
  }


  @Test
  public void moveShapeTest() {
    canvas.move("oval1", 3, 7, 10.0, 13.0);
    int lastAdded = canvas.getAnimation().size() - 1;
    Animation moveProof = canvas.getAnimation().get(lastAdded);
    assertEquals(10.0, moveProof.getEndShape().getXPos(), 0.1);
    assertEquals(13.0, moveProof.getEndShape().getYPos(), 0.1);

  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeBadX() {
    canvas.move("rect2", 4, 9,
            -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeBadY() {
    canvas.move("rect1", 10, 19,
            17, -9.2);
  }


  @Test(expected = IllegalArgumentException.class)
  public void moveShapeBadName() {
    canvas.move(null, 1, 6,
            11, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShapeBadTick() {
    canvas.move("oval1", -9, 6,
            11, 0);
  }


  @Test
  public void changeSizeTest() {
    canvas.changeSize("rect1", 7, 12, 33.3, 16);
    int lastAdded = canvas.getAnimation().size() - 1;
    Animation sizeProof = canvas.getAnimation().get(lastAdded);
    assertEquals(33.3, sizeProof.getEndShape().getXLength(), 0.1);
    assertEquals(16.0, sizeProof.getEndShape().getYLength(), 0.1);


  }


  @Test(expected = IllegalArgumentException.class)
  public void changeSizeBadRect() {
    canvas.changeSize("rect2", 3, 9,
            -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeSizeBadCircle() {
    canvas.changeSize("oval2", 2, 4,
            4, -0.5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeSizeNull() {
    canvas.changeSize(null, 2, 5, 5, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeSizeBadTick() {
    canvas.changeSize("oval2", 2, -1, 5, 7);

  }


  @Test
  public void changeColorTest() {
    canvas.changeColor("oval3", 3, 5, new Color(0, 153, 153));
    int lastAdded = canvas.getAnimation().size() - 1;
    Animation colorProof = canvas.getAnimation().get(lastAdded);
    assertEquals(0, colorProof.getEndShape().getColor().getRed());
    assertEquals(153, colorProof.getEndShape().getColor().getGreen());
    assertEquals(153, colorProof.getEndShape().getColor().getBlue());
  }


  @Test(expected = IllegalArgumentException.class)
  public void changeColorBad() {
    canvas.changeColor("rect1", 6,
            9, null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void changeColorBadName() {
    canvas.changeColor(null, 4, 7,
            new Color(255, 102, 102));
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorBadTick() {
    canvas.changeColor("rect1", -2, 7,
            new Color(255, 102, 102));
  }

  @Test
  public void addAnimationTest() {
    assertEquals(4, canvas.getAnimation().size());
    canvas.addAnimation(animation1);
    assertEquals(5, canvas.getAnimation().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAnimationOverlap() {
    canvas.addAnimation(appearRect1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAnimationNull() {
    canvas.addAnimation(null);
  }

  @Test(expected = NullPointerException.class)
  public void addAnimationBadStart() {
    canvas.addAnimation(new Animation(AnimationType.appear, 0, 10, null,
            rect2, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAnimationBadEnd() {
    canvas.addAnimation(new Animation(AnimationType.appear, 0, 10, rect2,
            null, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAnimationBadLayer() {
    canvas.addAnimation(new Animation(AnimationType.appear, 0, 10, rect2,
            rect2, -1));
  }

  @Test
  public void removeAnimationTest() {
    assertEquals(4, canvas.getAnimation().size());
    canvas.removeAnimation(appearOval1);
    assertEquals(3, canvas.getAnimation().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeAnimationBadAnimation() {
    canvas.removeAnimation(null);
  }

  @Test(expected = NullPointerException.class)
  public void removeAnimationBadStart() {
    canvas.removeAnimation(new Animation(AnimationType.move, 5, 7, null,
            oval3, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeAnimationBadEnd() {
    canvas.removeAnimation(new Animation(AnimationType.move, 5, 7, oval3,
            null, 2));
  }

  @Test
  public void toStringTest() {
    assertEquals("canvas 350 350\n" +
            "motion appear oval1 0 200.0 200.0 3.0 6.0 200 0 150     40 200.0 200.0 3.0 6.0 200" +
            " 0 150 1\n" +
            "motion appear oval3 0 0.0 0.0 8.0 4.0 150 0 200     40 0.0 0.0 8.0 4.0 150 0 200 1\n" +
            "motion appear rect1 0 1.0 11.0 11.0 11.0 255 102 102     40 1.0 11.0 11.0 11.0 255 " +
            "102 102 2\n" +
            "motion appear rect3 0 200.0 201.0 15.1 15.1 0 153 153     40 200.0 201.0 15.1 15.1 0" +
            " 153 153 2", canvas.toString());
  }


}
