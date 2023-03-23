package cs3500.animator.model;

import java.util.Comparator;

/**
 * Class to compare animations by their layer to order them in the SVG view using coolections.sort.
 */
public class SortByLayer implements Comparator<Animation> {
  @Override
  public int compare(Animation o1, Animation o2) {
    if (o1.getLayer() < o2.getLayer()) {
      return -1;
    } else if (o1.getLayer() > o2.getLayer()) {
      return 1;
    } else {
      return 0;

    }
  }
}
