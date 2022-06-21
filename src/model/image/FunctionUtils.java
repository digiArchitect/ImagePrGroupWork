package model.image;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A function utility class used for various operations such as clamping RGB values,
 * turning RGB values into 32-bit integers, and determining whether a coordinate pair is
 * a valid point in a kernel.
 */
public class FunctionUtils {
  /**
   * The file types that are supported to load into our image processor.
   */
  public static final String[] SUPPORTED = new String[] {"jpg", "png", "bmp"};

  /**
   * Returns whether a file type is supported to be loaded in.
   * @param fileType the file type that is trying to be loaded in.
   * @return whether it is supported.
   */
  public static boolean fileTypeSupported(String fileType) {
    return Arrays.asList(SUPPORTED).contains(fileType);
  }

  /**
   * Clamps a rgb's value to the max of 255.
   * @param value the r, g, or b value.
   * @return the clamped value.
   */
  public static int clamp(int value) {
    if (value > 255) {
      return 255;
    } else if (value < 0) {
      return 0;
    }
    else {
      return value;
    }
  }

  /**
   * Given three RGB integers, returns a pixel with these values as a 32-bit integer.
   * @param values the r, g, b values given.
   * @return a pixel with the color of these values.
   */
  public static int properRGB(int[] values) {
    StringBuilder p = new StringBuilder();
    p.append(values[0] << 16);
    p.append(values[1] << 8);
    p.append(values[2]);
    int f = ((values[0] & 0x0ff) << 16) | ((values[1] & 0x0ff) << 8) | (values[2] & 0x0ff);
    return f;
  }

  /**
   * Checks whether the given x, y position is valid, given the height and width of a matrix.
   * @param x the x value of the coordinate.
   * @param y the y value of the coordinate.
   * @param height the height of the matrix.
   * @param width the width of the matrix.
   * @return whether the coordinate is valid.
   */
  public static boolean validPosition(int x, int y, int height, int width) {
    return x >= 0 && y >= 0 && x < height - 1 && y < width - 1;
  }

  /**
   * Returns the flattened list of pixels of the image at a specified name.
   *
   * @param s the image name.
   * @return the list of pixels.
   */
  public static List<String> getFlatten(HashMap<String, Image> g, String s) {
    return g.get(s).flatten().stream().map(new RgbAll()).collect(Collectors.toList());
  }
}
