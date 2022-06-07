package Image;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ReverseAll implements Function<List<Pixel>, List<Pixel>> {

  @Override
  public List<Pixel> apply(List<Pixel> pixels) {
    Collections.reverse(pixels);
    return pixels;

  }
}