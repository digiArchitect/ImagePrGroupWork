package model.image;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class HistogramVals extends AbstractApply implements Function<Pixel, List<Integer>> {
  String component;

  public HistogramVals(String component) {
    this.component = component;
  }

  @Override
  public List<Integer> apply(Pixel pixel) {
    return
            Arrays.asList(newValue("red",pixel),
                    newValue("green",pixel),
                    newValue("blue",pixel),
                    newValue("intensity",pixel));
  }
}
