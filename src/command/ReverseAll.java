package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import pixel.PixelImpl;


//needs a better name

/**
 * Function class utilized to reverse a list of lists.
 */
public class ReverseAll implements Function<List<PixelImpl>, List<PixelImpl>> {
  @Override
  public List<PixelImpl> apply(List<PixelImpl> pixels) {
    List<PixelImpl> copiedPixel = new ArrayList<>(pixels);
    Collections.reverse(copiedPixel);
    return copiedPixel;
  }
}