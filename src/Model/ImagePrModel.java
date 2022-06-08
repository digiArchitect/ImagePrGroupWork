package Model;

public interface ImagePrModel {
  /**
   * Flips the image depending on the direction user inputs.
   * @param direction The direction in which the user would like to flip the input.
   * @param filename The name of the file in which the user would like to be flipped.
   * @throws IllegalArgumentException if provided direction is not "horizontal" or "vertical"
   * @throws IllegalArgumentException if provided arguments are null || cannot be found.
   */
  void flipImage(String direction,String filename, String newname);
  /**
   * Brightens the image based off of a constant given to the user.
   * @param constant the val which the user would like to see the image brightened. can be + || -
   * @param filename The name of the file in which the user would like to be flipped
   * @param newname the name of the transformed file.
   * @throws IllegalArgumentException if provided arguments are null || cannot be found.
   */
  void brighten(int constant,String filename, String newname);
  /**
   * Turns the image greyscale based off of which component the user would like.
   * @param component Component used to define the value in which the image is greyscaled.
   * @param filename The name of the file in which the user would like to be flipped.
   * @param newname the name of the transformed file.
   * @throws IllegalArgumentException if provided arguments are null || cannot be found.
   * @throws IllegalArgumentException if component is not luma, value, or intensity.
   */
  void greyscale(String component,String filename, String newname);
  /**
   * saves a ppm to a specific file.
   * @param fileLocation the fileLocation where the image will be saved.
   * @param newName the name of the transformed file.
   * @throws IllegalArgumentException if provided arguments are null || cannot be found.
   */
  void save(String fileLocation, String newName);


}
