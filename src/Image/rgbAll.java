package Image;

import java.util.function.Function;
/**
 * Function class used to properly return the rgb values.
 */
public class rgbAll implements Function<Pixel,String> {
  @Override
  public String apply(Pixel pixel) {
    StringBuilder p = new StringBuilder();
    for(int x = 0; x < 3; x++) {
      int newRgb = pixel.getChannel(x);
      p.append(newRgb);
      if (x < 2) {
        p.append(" ");
      }
    }
    p.append("\n");
    return p.toString();
  }

}
