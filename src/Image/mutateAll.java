package Image;
import java.util.function.Function;

/**
 * Function class used to mutate all the Pixels within a list.
 */
public class mutateAll implements Function<Pixel,Pixel>  {
  int constant;

  /**
   * Constructs mutateAll with constant which is used to mutate all the values in the list.
   * @param constant positive or negative value which mutates the function.
   */
  public mutateAll(int constant) {
    this.constant = constant;
  }

  /**
   * Adds a constant to each color channel in a pixel.
   * @param pixel the pixel given.
   * @return a new pixel with the constant added to each of its color channels.
   */
  @Override
  public Pixel apply(Pixel pixel) {
    StringBuilder p = new StringBuilder();
    int[] values = new int[3];
    for (int x = 0; x < 3; x++) {
      int newRgb = pixel.getChannel(x) + constant;
      if (newRgb > 255) {
        newRgb = 255;
      } else if (newRgb < 0) {
        newRgb = 0;
      }
      values[x] = newRgb;
    }
    return new Pixel(values[0], values[1], values[2]);
  }
}
