package view;

import java.io.IOException;
import java.util.List;

/**
 * Represents the view for our image processor.
 */
public interface ImagePrView {
  /**
   * Saves the image to the user's computer.
   */
  void save(String fileLocation, String fileName, List<Integer> contents, List<String> mapList)
          throws IOException;
}
