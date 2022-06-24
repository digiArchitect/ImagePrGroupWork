package model;

import java.util.HashMap;
import java.util.List;

import model.image.Image;

/**
 * The model of our image processor, which performs visual operations on our images
 * through manipulating their pixels.
 */
public interface ImagePrModel {

  /**
   * Loads a new image into the hashmap.
   *
   * @param imageName the name of the image.
   * @param image     the image.
   */
  void newEntry(String imageName, Image image);

  /**
   * Returns whether this model's hashmap has a key of the given string.
   *
   * @param s the given string.
   * @return whether the hashmap has that key.
   */
  boolean hasKey(String s);

  /**
   * Returns this model's hashmap.
   *
   * @return the hashmap.
   */
  HashMap<String, Image> getHashMap();

  /**
   * Returns the contents of an image at a specified name.
   *
   * @param s the image name.
   * @return the contents.
   */
  List<Integer> getImageContents(String s);

  /**
   * Flips the image depending on the direction user inputs.
   *
   * @param direction The direction in which the user would like to flip the input.
   * @param filename  The name of the file in which the user would like to be flipped.
   * @throws IllegalArgumentException if provided direction is not "horizontal" or "vertical"
   * @throws IllegalArgumentException if provided arguments are null || cannot be found.
   */
  void flipImage(String direction, String filename, String newname);

  /**
   * Brightens the image based off of a constant given to the user.
   *
   * @param constant the val which the user would like to see the image brightened. can be + || -
   * @param filename The name of the file in which the user would like to be flipped
   * @param newname  the name of the transformed file.
   * @throws IllegalArgumentException if provided arguments are null || cannot be found.
   */
  void brighten(int constant, String filename, String newname);

  /**
   * Turns the image greyscale based off of which component the user would like.
   *
   * @param component Component used to define the value in which the image is greyscaled.
   * @param fileName  The name of the file in which the user would like to be flipped.
   * @param newName   the name of the transformed file.
   * @throws IllegalArgumentException if provided arguments are null || cannot be found.
   * @throws IllegalArgumentException if component is not luma, value, or intensity.
   */
  void greyscale(String component, String fileName, String newName);

  /**
   * Mutates the image based off a kernel.
   *
   * @param component which type of kenel mutation one would like to do.
   * @param fileLoc   the file location.
   * @param fileName  the file name.
   */
  void kernelMutate(String component, String fileLoc, String fileName);

  /**
   * Mutates the image based off a kernel.
   *
   * @param component which type of kernel mutation one would like to do.
   * @param fileLoc   the file location.
   * @param fileName  the file name.
   */
  void colorTransform(String component, String fileLoc, String fileName);
  /**
   * Generates a histogram data for an image.
   * @param fileLoc the name of the file
   * @return the histogram for it.
   */
  HashMap<Integer,List<Integer>> histogram(String fileLoc);

  /**
   * Downscales an image!
   * @param newWidth the new width
   * @param newHeight the new Height
   * @param fileLoc   the file location.
   * @param fileName  the file name.
   * @throws IllegalArgumentException if newWidth > width || newHeight > height
   */
  void imageDownscale(int newWidth, int newHeight,String fileLoc, String fileName);
}
