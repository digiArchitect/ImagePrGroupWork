package model.image;

import java.util.function.Function;

/**
 * Function class used to properly return the rgb values.
 */
public class RgbAll implements Function<Pixel,String> {

  /**
   * Returns a String of this pixel's channels.
   * @param pixel the function argument
   * @return a string of this pixel's channels.
   */
  @Override
  public String apply(Pixel pixel) {
    StringBuilder p = new StringBuilder();
    for (int x = 0; x < 3; x++) {
      int newRgb = pixel.getChannel(x);
      p.append(newRgb);
      p.append(" ");
    }
    return p.toString();
  }
}