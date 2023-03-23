package cs3500.animator.view;


import cs3500.animator.model.ICanvas;

/**
 * Factory class for view type generations.
 */
public class FactoryView {

  /**
   * static method so constructor empty.
   */
  private FactoryView() {

  }

  public static View createView(ViewType viewType, ICanvas canvas,
                                Appendable appendable) {
    if (viewType == ViewType.Text) {
      return new TextView(canvas, appendable);
    } else if (viewType == ViewType.Swing) {
      return new SwingView(canvas, appendable);
    } else if (viewType == ViewType.SVG) {
      return new SVGView(canvas, appendable);
    } else if (viewType == ViewType.Interactive) {
      return new InteractiveSwing(canvas, appendable);
    } else {
      return null;
    }
  }


}
