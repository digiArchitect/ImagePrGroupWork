package controller.prcontroller;

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

  /**
   * Loads a ppm image into the model's hashmap.
   * @param fileLoc the file source of the image to be loaded.
   * @param imageName the name of the image to be put in the HashMap.
   */
  void loadPPM(String fileLoc, String imageName) throws IOException;

  /**
   * Loads an image file into the model's hashmap.
   * @param fileLoc the file source of the image to be loaded.
   * @param imageName the name for the image to be put into the hashmap under.
   * @throws IOException if unable to read the file input stream of the fileLoc.
   */
  void loadSupported(String fileLoc, String imageName) throws IOException;
}
