package command;

import java.util.function.Function;

import pixel.PixelImpl;

/**
 * Function class used to properly return the rgb values.
 */
public class RgbAll implements Function<PixelImpl,String> {

  /**
   * Returns a String of this pixel's channels.
   * @param pixel the function argument
   * @return a string of this pixel's channels.
   */
  @Override
  public String apply(PixelImpl pixel) {
    StringBuilder p = new StringBuilder();
    for (int x = 0; x < 3; x++) {
      int newRgb = pixel.getChannel(x);
      p.append(newRgb);
      p.append(" ");
    }
    return p.toString();
  }
}