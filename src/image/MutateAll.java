package image;

import java.util.function.Function;


//needs a better name

/**
 * Function class used to mutate all the Pixels within a list.
 */
public class MutateAll extends AbstractApply implements  Function<Pixel,Pixel>  {
  int constant;

  /**
   * Constructs MutateAll with constant which is used to mutate all the values in the list.
   * @param constant positive or negative value which mutates the function.
   */
  public MutateAll(int constant) {
    this.constant = constant;
  }

  /**
   * Adds a constant to each color channel in a pixel.
   * @param pixel the pixel given.
   * @return a new pixel with the constant added to each of its color channels.
   */
  @Override
  public Pixel apply(Pixel pixel) {

    int[] values = new int[3];
    for (int x = 0; x < 3; x++) {
      int newRgb = pixel.getChannel(x) + constant;
      values[x] = clamp(newRgb);

    }
    return properRGB(values);



  }

}
