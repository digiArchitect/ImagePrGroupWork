package Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ImagePPM {
  public List<List<Pixel>> imageVals;
  String name;
  int width;
  int height;
  int maxValue;

  public ImagePPM(List<List<Pixel>> imageVals, String name, int width,
                  int height, int maxValue) {
    this.imageVals = imageVals;
    this.name = name;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
  }

  public List<Pixel> flatten() {
    return imageVals.stream().flatMap(Collection::stream).collect(Collectors.toList());
  }


  public void brighten(int constant) {
    List<Pixel> bright = flatten();
    bright = bright.stream().map(new mutateAll(constant)).collect(Collectors.toList());
    int count = 0;
    List<List<Pixel>> brighten = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(bright.get(count));
      }
      brighten.add(row);
    }
    imageVals = brighten;
  }


}

