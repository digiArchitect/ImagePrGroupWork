package model.image;

import java.util.function.Function;

public class HistogramVals extends AbstractApply implements Function<Pixel,Integer> {
  String component;

  public HistogramVals(String component) {
    this.component = component;
  }

  @Override
  public Integer apply(Pixel pixel) {
     return newValue(component,pixel);
  }
}
