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
   * @param component Component can either be 
   * @throws IllegalArgumentException if provided constant is null
   */
  void greyscale(String component);
}
