package model.image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a PPM file, a text-based image format which represents images as the RGB values of
 * every pixel of each row.
 */
public class ImageImpl implements Image {
  private final List<List<Pixel>> imageVals;
  private final int width;
  private final int height;
  private final int maxValue;

  /**
   * Constructs a PPM image object.
   *
   * @param imageVals the row and column grid of the pixels of the image.
   * @param width     the width of the image iin pixels.
   * @param height    the height of the image in pixels.
   * @param maxValue  the max rgb value of the image.
   * @throws IllegalArgumentException if negative/zero values in the width and height
   * @throws IllegalArgumentException for null imageValues.
   * @throws IllegalArgumentException if maxValue < 0
   */
  public ImageImpl(List<List<Pixel>> imageVals, int width,
                   int height, int maxValue) {
    //Should never happen but as a precuation.
    if (imageVals == null || width < 1 || height < 1 || maxValue < 0) {
      throw new IllegalArgumentException();
    }
    this.imageVals = imageVals;
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
   * Returns important data points of this image, the width, height, and max value of its rgbs,
   * as an arraylist.
   * @return an ArrayList of the image's contents.
   */
  public List<Integer> getContents() {
    return Arrays.asList(width, height, maxValue);
  }

  /**
   * Returns this image's values.
   * @return an arraylist of this image's pixels.
   */
  public List<List<Pixel>> getImageVals() {
    return new ArrayList<>(imageVals);
  }
}
