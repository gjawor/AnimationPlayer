import org.junit.Test;

import cs3500.animator.model.Canvas;
import cs3500.animator.model.ICanvas;
import cs3500.animator.view.FactoryView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.SwingView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.ViewType;

import static org.junit.Assert.assertEquals;

public class FactoryViewTest {
  FactoryView factoryView;
  Appendable appendable = System.out;
  ICanvas canvas = new Canvas(300,300);



  @Test
  public void nullViewTest() {
    assertEquals(null, factoryView.createView(null,canvas , appendable));
  }


  @Test
  public void textViewTest() {
    assertEquals(TextView.class, factoryView.createView(ViewType.Text, canvas, appendable).getClass());
  }

  @Test
  public void swingViewTest() {
    assertEquals(SwingView.class, factoryView.createView(ViewType.Swing, canvas, appendable).getClass());
  }

  @Test
  public void svgViewTest() {
    assertEquals(SVGView.class, factoryView.createView(ViewType.SVG, canvas,appendable).getClass());
  }



}
