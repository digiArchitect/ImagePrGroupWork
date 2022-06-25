package model.image;

import java.util.function.Function;

/**
 * Represents histogram values.
 */
public class HistogramVals extends AbstractApply implements Function<Pixel,Integer> {
  String component;

  /**
   * Constructs histogram values based on a component.
   * @param component the component.
   */
  public HistogramVals(String component) {
    this.component = component;
  }

  /**
   * Applies this method to a pixel.
   * @param pixel the function argument.
   * @return an integer.
   */
  @Override
  public Integer apply(Pixel pixel) {
    return newValue(component,pixel);
  }
}
