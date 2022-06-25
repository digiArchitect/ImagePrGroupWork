package view.guiview;

import java.awt.image.BufferedImage;
import java.util.List;

import model.image.Pixel;


/**
 * Represents te GUi view.
 */
public class GUIViewImpl implements GUIView {
  /**
   * Constructs a GUI view.
   */
  public GUIViewImpl() {}

  /**
   * Makes an image from an image.
   * @param contents the image contents.
   * @param imageVals the image vals.
   * @return a buffered image.
   */
  public BufferedImage image(List<Integer> contents, List<Pixel> imageVals) {
    BufferedImage b = new BufferedImage(contents.get(0),
            contents.get(1), BufferedImage.TYPE_INT_RGB);
    int count = 0;
    for (int x = 0; x < contents.get(1); x++) {
      for (int y = 0; y < contents.get(0); y++) {
        b.setRGB(y, x, imageVals.get(count).hashCode());
        count++;
      }
    }
    return b;
  }
}