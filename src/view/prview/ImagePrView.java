package view.prview;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import model.image.Image;

/**
 * Represents the view for our image processor.
 */
public interface ImagePrView {
  /**
   * Saves the image to the user's computer.
   */
  void save(String fileLoc, String fileName,List<Integer> contents, HashMap<String, Image> images)
          throws IOException;
}
