package image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;


//needs a better name

/**
 * Function class utilized to reverse a list of lists.
 */
public class ReverseAll implements Function<List<Pixel>, List<Pixel>> {
  @Override
  public List<Pixel> apply(List<Pixel> pixels) {
    List<Pixel> copiedPixel = new ArrayList<>(pixels);
    Collections.reverse(copiedPixel);
    return copiedPixel;
  }
}