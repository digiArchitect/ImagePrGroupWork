package model.image;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Represents an image in our image processor.
 */
public interface Image {
  /**
   * Gets the Pixels of this image as a 1-dimensional list.
   * @return a list of Pixels.
   */
  List<Pixel> flatten();

  /**
   * Gets the height, width, and max value of this image.
   * @return the contents of this image.
   */
  List<Integer> getContents();

  /**
   * Gets the Pixels of this image as a 2-d list.
   * @return a 2-d list of Pixels.
   */
  List<List<Pixel>> getImageVals();

  /**
   * Returns buffered image of image.
   * @return a buffered image.
   */
  BufferedImage getBufferedImage();
}
