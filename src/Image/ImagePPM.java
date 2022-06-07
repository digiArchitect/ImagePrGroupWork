package Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

  /**
   * Brightens or Darkens an image by a given increment to be added or subtracted from the
   * image's pixels' RGB values.
   * @param constant the amount to brighten (positive int) or darken (negative int) by.
   */

  //doesn't increment count?
  public void brighten(int constant) {
    List<Pixel> bright = flatten();
    bright = bright.stream().map(new mutateAll(constant)).collect(Collectors.toList());
    int count = 0;
    List<List<Pixel>> brighten = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(bright.get(count));
        //count++;
      }
      brighten.add(row);
    }
    imageVals = brighten;
  }


}

