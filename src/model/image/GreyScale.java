package model.image;

import java.util.function.Function;

/**
 * Function class used to make a Pixel grey.
 */
public class GreyScale extends AbstractApply implements Function<Pixel, Pixel> {
  private String component;

  /**
   * Constructs greyScale class utilizing the calculation for which the pixel will be turned gray.
   * @param component The calculating value instruction to make the pixel grey.
   */
  public GreyScale(String component) {
    this.component = component;
  }

  /**
   * Greys out the given pixel depending on the channel requested.
   * @param pixel the pixel.
   * @return a new gray pixel.
   */
  public Pixel apply(Pixel pixel) {

    int newVal = newValue(component,pixel);


    return new PixelImpl(new int[] {newVal,newVal,newVal});
  }
}
