import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.animator.io.ModelBuilder;


/**
 * Class to test the model builder class functionality.
 */
public class ModelBuilderTest {

  ModelBuilder modelBuilder;

  @Before
  public void init() {

    modelBuilder = new ModelBuilder();


  }

  @Test
  public void setBoundsTest() {

    assertEquals(modelBuilder, modelBuilder.setBounds(300, 300));

  }

  @Test
  public void addOvalTest() {
    modelBuilder.setBounds(300, 300);
    assertEquals(modelBuilder, modelBuilder.addOval(2, "oval1", 100, 120, 4,
            6, 150, 13, 150, 1, 16));
  }

  @Test
  public void addRectangleTest() {
    modelBuilder.setBounds(300, 300);
    assertEquals(modelBuilder, modelBuilder.addRectangle(3, "rect1", 100, 120, 4,
            6, 150, 13, 150, 1, 16));

  }

  @Test
  public void addMoveTest() {
    modelBuilder.setBounds(150, 150);
    modelBuilder.addRectangle(2, "rect1", 100, 120, 4,
            6, 150, 13, 150, 1, 16);
    assertEquals(modelBuilder, modelBuilder.addMove("rect1", 100, 120,
            150, 7, 17, 20));
  }

  @Test
  public void changeColorTest() {
    modelBuilder.setBounds(150, 150);
    modelBuilder.addRectangle(3, "rect1", 100, 120, 4,
            6, 150, 13, 150, 1, 16);
    assertEquals(modelBuilder, modelBuilder.addColorChange("rect1", 150, 13, 150,
            255, 0, 255, 14, 20));

  }

  @Test
  public void changeSizeTest() {
    modelBuilder.setBounds(150, 150);
    modelBuilder.addRectangle(2, "rect1", 100, 120, 4,
            6, 150, 13, 150, 1, 16);
    assertEquals(modelBuilder, modelBuilder.addScaleToChange("rect1", 4, 6,
            11, 11, 9, 25));

  }

  @Test
  public void buildTest() {
    modelBuilder.setBounds(150, 150);
    modelBuilder.addRectangle(1, "rect1", 100, 120, 4,
            6, 150, 13, 150, 1, 16);
    modelBuilder.addScaleToChange("rect1", 4, 6,
            11, 11, 9, 25);
    assertEquals(modelBuilder.getCanvas(), modelBuilder.build());

  }


}


