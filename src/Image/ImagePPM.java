package Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Represents a PPM file, a text-based image format which reprsents images as the RGB values of
 * every pixel of each row.
 */
public class ImagePPM {

  /*
  image fields should be private, right?
   */
  private List<List<Pixel>> imageVals;
  private String name;
  private int width;
  private int height;
  private int maxValue;

  /**
   * Constructs a PPM image object.
   * @param imageVals the row and column grid of the pixels of the image.
   * @param name the file name of the image.
   * @param width the width of the image iin pixels.
   * @param height the height of the image in pixels.
   * @param maxValue the max rgb value of the image.
   */
  public ImagePPM(List<List<Pixel>> imageVals, String name, int width,
                  int height, int maxValue) {
    this.imageVals = imageVals;
    this.name = name;
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
  }

  /**
   * Returns our 2D pixel grid as one List of pixels.
   * @return a List of pixels.
   */
  public List<Pixel> flatten() {
    return imageVals.stream().flatMap(Collection::stream).collect(Collectors.toList());
  }

  public void vertical() {
    imageVals = imageVals.stream().map(new ReverseAll()).collect(Collectors.toList());
  }

  public void horizontal() {
    List<List<Pixel>> newVals = new ArrayList<>();
    for (int x = imageVals.size() -1;  x > 0; x--) {
      newVals.add(imageVals.get(x));
    }
    imageVals = newVals;
  }

  /**
   * Given a function<T,T> apply it to the pixels within imageVals
   * @param applyFunc The function that is used to change the pixel values.
   */
  public void applyChanges (Function<Pixel,Pixel> applyFunc) {
    List<Pixel> mapList = flatten();
    mapList = mapList.stream().map(applyFunc).collect(Collectors.toList());
    updateImageVals(mapList);
  }

  /**
   * Given a one dimensional array, change it until it becomes a 2d array suitable to be imageVals
   * @param flatlist The 1d arraylist whos data will be extracted.
   */
  public void updateImageVals(List<Pixel> flatlist) {
    List<List<Pixel>> newList = new ArrayList<>();

    int count = 0;
    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(flatlist.get(count));
        count++;
      }
      newList.add(row);
    }
    imageVals = newList;
  }
}

