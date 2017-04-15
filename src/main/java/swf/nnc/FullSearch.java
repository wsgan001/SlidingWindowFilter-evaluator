package swf.nnc;

import java.util.List;
import swf.measure.Distance;

public class FullSearch<T> extends NearestNeighbourClassificator<T> {
  public FullSearch(Distance<T> distance, List<T> testData) {
    super(distance, testData);
  }

  protected void preProcess() {
  }

  /**
   * Returns the first nearest neighbour for the given query.
   */
  public T nearestNeighbour(T query) {
    double min = Double.MAX_VALUE;
    T nn = null;
    for (T curr : this.testData) {
      double dist = this.distance.distance(curr, query);
      if (dist < min) {
        min = dist;
        nn = curr;
      }
    }
    return nn;
  }
}