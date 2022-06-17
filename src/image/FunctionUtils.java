package image;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Function utility class!
 */
public class FunctionUtils {
  public static String[] supported = new String[] {
    "jpg", "png", "bmp"
  };
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
  public static Pixel properRGB(int[] values) {
    StringBuilder p = new StringBuilder();
    p.append(values[0] << 16);
    p.append(values[1] << 8);
    p.append(values[2]);
    Pixel f = new Pixel(((values[0]&0x0ff)<<16)|((values[1]&0x0ff)<<8)|(values[2]&0x0ff));
    return f;
  }

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
