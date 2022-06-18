package model.image;

import java.util.List;

public interface Image {
  List<Pixel> flatten();

  List<Integer> getContents();

  List<List<Pixel>> getImageVals();
}
