package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import image.Image;
import image.MatrixMultiplication;
import image.Pixel;
import image.ReverseAll;
import image.GreyScale;
import image.MutateAll;
import image.RgbAll;

/**
 * An image processor's model, which performs operations on images stored within its HashMap of
 * images.
 */

/**
 * An image processor's model, which performs operations on images stored within its HashMap of
 * images.
 */
public class ImagePrModelImpl implements ImagePrModel {

  private HashMap<String, Image> images;

  /**
   * Constructs an image processing model with an empty HashMap of images.
   */
  public ImagePrModelImpl() {
    this.images = new HashMap<>();
  }

  /**
   * Read an image file in the PPM format and stores it in this model's hashmap.
   *
   * @param fileLoc the path of the file.
   */
  public void load(String fileLoc, String fileName) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(fileLoc));
    } catch (FileNotFoundException e) {
      System.out.println("File " + fileLoc + " not found!");
      throw new IllegalArgumentException();
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    List<List<Pixel>> imageVals = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        row.add(new Pixel(r, g, b));
      }
      imageVals.add(row);
    }
    newEntry(fileName, new Image(imageVals, width, height, maxValue));
  }


  /**
   * Flips this image either vertically or horizontally, depending on the user input.
   *
   * @param direction The direction in which the user would like to flip the input.
   * @param filename  The name of the file in which the user would like to be flipped.
   * @param newName   the new name of the flipped image.
   */
  @Override
  public void flipImage(String direction, String filename, String newName) {
    List<List<Pixel>> newVals;
    List<List<Pixel>> oldVals = images.get(filename).getImageVals();
    List<Integer> contents = images.get(filename).getContents();
    if (direction.equals("horizontal")) {
      newVals = oldVals.stream().map(new ReverseAll()).collect(Collectors.toList());
    } else if (direction.equals("vertical")) {
      newVals = oldVals;
      Collections.reverse(newVals);
    } else {
      //Catch and throw an illegal state in the controller.
      throw new IllegalArgumentException();
    }
    images.put(newName, new Image(newVals, contents.get(0), contents.get(1), contents.get(2)));
  }

  /**
   * Brightens the image at the given file name by a certain integer constant amount.
   * Can be negative or positive.
   *
   * @param constant the val which the user would like to see the image brightened. can be + || -
   * @param filename The name of the file in which the user would like to be flipped
   * @param newName  the name of the transformed file.
   */
  @Override
  public void brighten(int constant, String filename, String newName) {
    Image newImage = applyChanges(new MutateAll(constant), images.get(filename));
    images.put(newName, newImage);
  }

  /**
   * Greyscales the image at the given file name by a certain channel, depending on the
   * user's input. Can be either the image's red, blue, green, luma, intensity, or value.
   *
   * @param component Component used to define the value in which the image is greyscaled.
   * @param filename  The name of the file in which the user would like to be flipped.
   * @param newName   the name of the transformed file.
   */
  @Override
  public void greyscale(String component, String filename, String newName) {
    Image newImage = applyChanges(new GreyScale(component), images.get(filename));
    images.put(newName, newImage);
  }

  /**
   * Returns whether the model has an image under a given key.
   *
   * @param s the given key.
   * @return whether our model has this image.
   */
  public boolean hasKey(String s) {
    for (String key : images.keySet()) {
      if (s.equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns this model's hashmap.
   *
   * @return the hashmap.
   */
  @Override
  public HashMap<String, Image> getHashMap() {
    return new HashMap<>(images);
  }

  /**
   * Returns the contents of an image at a specified name.
   *
   * @param s the image name.
   * @return the contents.
   */
  public List<Integer> getImageContents(String s) {
    return images.get(s).getContents();
  }

  /**
   * Returns the flattened list of pixels of the image at a specified name.
   *
   * @param s the image name.
   * @return the list of pixels.
   */
  public List<String> getFlatten(String s) {
    return images.get(s).flatten().stream().map(new RgbAll()).collect(Collectors.toList());
  }

  @Override
  public void kernelMutate(String component, String filename, String newName) {
    Double[][] kernelValues;
    Image g;

    if (component.equals("sharpen")) {
      kernelValues = new Double[][]{
              new Double[]{
                      -0.125, -0.125, -0.125, -0.125, -0.125},
              new Double[]{
                      -0.125, 0.25, 0.25, 0.25, -0.125},
              new Double[]{
                      0.125, 0.25, 1.00, 0.25, -0.125},
              new Double[]{
                      -0.125, 0.25, 0.25, 0.25, -0.125},
              new Double[]{
                      -0.125, -0.125, -0.125, -0.125, -0.125}
      };


    } else if (component.equals("blur")) {
      kernelValues = new Double[][]{
              new Double[]{
                      0.0625, 0.125, 0.0625},
              new Double[]{
                      0.125, 0.25, 0.125},
              new Double[]{
                      0.0625, 0.125, 0.0625}

      };
    } else {
      throw new IllegalArgumentException();
    }
    g = kernelHelper(kernelValues, images.get(filename));
    images.put(newName, g);


  }

  @Override
  public void colorTransform(String component, String fileLoc, String fileName) {
    Double[][] matrix;
    if(component.equals("sepia")) {
      matrix = new Double[][]{
              new Double[]{
                      0.393, 0.769, 0.189},
              new Double[]{
                      0.349, 0.686, 0.168},
              new Double[]{
                      0.272, 0.534, 0.131}};


    }
    else if (component.equals("greyscale")) {
      matrix = new Double[][]{
              new Double[]{
                      0.2126, 0.7152, 0.0722},
              new Double[]{
                      0.2126, 0.7152, 0.0722},
              new Double[]{
                      0.2126, 0.7152, 0.0722}};
    }
    else {
      throw new IllegalArgumentException();
    }
    images.put(fileName,applyChanges(new MatrixMultiplication(matrix),images.get(fileLoc)));

  }

  /**
   * Checks if a key is already in the hashmap if so overwrite it if not just add it.
   * Used to deal with overwriting.
   *
   * @param fileName the name of the file.
   * @param image    the image to be appended.
   */
  private void newEntry(String fileName, Image image) {
    //Checks if the filename is already in the key if so remove it
    if (images.get(fileName) != null) {
      images.remove(fileName);
    }
    images.put(fileName, image);
  }

  /**
   * Given a one dimensional array, change it until it becomes a 2d array suitable to be imageVals.
   *
   * @param flatlist The 1d arraylist whose data will be extracted.
   */
  private List<List<Pixel>> updateImageVals(List<Pixel> flatlist, int height, int width) {
    List<List<Pixel>> newList = new ArrayList<>();
    int count = 0;
    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(flatlist.get(count));
        count++;
      }
      newList.add(row);
    }
    return newList;
  }

  /**
   * Given a function, apply it to the pixels within imageVals.
   *
   * @param applyFunc The function that is used to change the pixel values.
   */
  private Image applyChanges(Function<Pixel, Pixel> applyFunc, Image p) {
    List<Pixel> mapList = p.flatten();
    mapList = mapList.stream().map(applyFunc).collect(Collectors.toList());
    return newImage(updateImageVals(mapList, p.getContents().get(1), p.getContents().get(0)), p);
  }

  private Image kernelHelper(Double[][] imageValues, Image p) {
    List<List<Pixel>> imageVals = p.getImageVals();
    int width = p.getContents().get(0);
    int height = p.getContents().get(1);
    List<List<Pixel>> newVals = new ArrayList<>();
    int centerX = (int) (imageValues[0].length * 0.5 - 0.5);
    int centerY = (int) (imageValues[0].length * 0.5 - 0.5);
    for (int x = 0; x < height; x++) {
      List<Pixel> row = new ArrayList<>();
      for (int y = 0; y < width; y++) {
        HashMap<Pixel, Double> neighborVals = new HashMap<>();
        for (int a = centerX * -1; a <= centerX; a++) {
          for (int b = centerY * -1; b <= centerY; b++) {
            if (validPosition(x + a, y + b, height, width)) {
              neighborVals.put(imageVals.get(x + a).get(y + b), imageValues[centerX + a][centerY + b]);
            }
          }
        }
        row.add(calcNeighbors(neighborVals));
      }
      newVals.add(row);
    }
    return newImage(newVals, p);
  }

  private boolean validPosition(int x, int y, int height, int width) {
    return x >= 0 && y >= 0 && x < height - 1 && y < width - 1;
  }


  private Pixel calcNeighbors(HashMap<Pixel, Double> neighborVals) {
    int[] newPixel = new int[3];
    for (int x = 0; x < 3; x++) {
      for (Pixel e : neighborVals.keySet()) {
        int newVal = (int)(e.getChannel(x) * neighborVals.get(e));
        newPixel[x] += newVal;
        if (newPixel[x] > 255) {
          newPixel[x] = 255;
        } else if (newPixel[x] < 0) {
          newPixel[x] = 0;
        }
      }
    }

    return new Pixel(newPixel[0], newPixel[1], newPixel[2]);
  }


  /**
   * Returns a new image with the given pixels, and the given image's contents.
   *
   * @param newVals the new pixels of the image.
   * @param r       the original image being fed into it for contents.
   * @return a new image with the same size and height (contents) as the given one.
   */
  private Image newImage(List<List<Pixel>> newVals, Image r) {
    return new Image(newVals, r.getContents().get(0), r.getContents().get(1),
            r.getContents().get(2));
  }


}