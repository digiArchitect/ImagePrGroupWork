package Model;

import java.awt.*;

public interface ImageModel {
  /**
   * Flips the image depending on the direction user inputs.
   * @param direction The direction in which the user would like to flip the input.
   * @throws IllegalArgumentException if provided direction is not "horizontal" or "vertical"
   * @throws IllegalArgumentException if provided constant is null
   */
  void flipImage(String direction);
  /**
   * Brightens the image based off of a constant given to the user.
   * @param constant the val which the user would like to see the image brightened. can be + || -
   * @throws IllegalArgumentException if provided constant is null
   */
  void brighten(int constant);
  /**
   * Turns the image greyscale based off of which component the user would like.
   * @param component Component used to define the value in which the image is greyscaled.
   * @throws IllegalArgumentException if provided constant is null
   * @throws IllegalArgumentException if component is not luma, value, or intensity.
   */
  void greyscale(String component);
  /**
   * saves a ppm to a specific file.
   * @param fileLocation the fileLocation where the image will be saved.
   */
  void save(String fileLocation);

  /**
   * Creates an image based off of the imagevalues.
   */

}
