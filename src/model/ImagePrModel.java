package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import image.Image;

/**
 * The model of our image processor, which performs visual operations on our images
 * through manipulating their pixels.
 */
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
   * Loads a file from a specific file location to a name in the hashamp.
   * @param fileLoc the file location.
   * @param fileName the file name.
   * @throws IOException if something goes terribly wrong.
   */
  void load(String fileLoc, String fileName) throws IOException;

  /**
   * Returns whether this model's hashmap has a key of the given string.
   * @param s the given string.
   * @return whether the hashmap has that key.
   */
  boolean hasKey(String s);

  /**
   * Returns this model's hashmap.
   * @return the hashmap.
   */
  HashMap<String, Image> getHashMap();

  /**
   * Returns the contents of the image at this value in the hashmap.
   * @param s the image name.
   * @return the contents.
   */
  List<Integer> getImageContents(String s);

  /**
   * Returns the flat list of string pixels of the image at this value in the hashmap.
   * @param s the image name.
   * @return the list.
   */
  List<String> getFlatten(String s);
}
