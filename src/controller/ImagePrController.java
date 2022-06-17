package controller;

import java.io.IOException;

/**
 * Represents an image processor.
 */
public interface ImagePrController {
  /**
   * Opens the image processor for user input.
   */
  void startProcessor() throws IllegalStateException;

  /**
   * Loads a file into the controller's model's hashmap.
   * @param fileLoc the source location of the file on the user's computer.
   * @param imageName the name of the image to be put into te hashmap.
   * @throws IOException if the file cannot be read.
   */
  void load(String fileLoc, String imageName) throws IOException;
}
