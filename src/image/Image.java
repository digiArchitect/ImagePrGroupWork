package image;

import java.util.List;
import java.util.function.Supplier;

public interface Image {
  List<Pixel> flatten();

  List<Integer> getContents();

  List<List<Pixel>> getImageVals();
}
