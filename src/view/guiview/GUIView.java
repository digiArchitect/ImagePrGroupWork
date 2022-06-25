package view.guiview;

import java.awt.image.BufferedImage;
import java.util.List;

import model.image.Pixel;

/**
 * Represents a view for the GUI.
 */
public interface GUIView {
  /**
   * Returns a buffered image of an image, given an image's dimensions and values.
   * @param contents the contents (height, width, max val) of an image.
   * @param imageVals the pixel values of an image.
   * @return a buffered image made from it.
   */
  public BufferedImage image(List<Integer> contents, List<Pixel> imageVals);
}
