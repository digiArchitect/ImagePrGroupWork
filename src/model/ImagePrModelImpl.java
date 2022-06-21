package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.image.FunctionUtils;
import model.image.Image;
import model.image.ImageImpl;
import model.image.MatrixMultiplication;
import model.image.Pixel;
import model.image.PixelImpl;
import model.image.ReverseAll;
import model.image.GreyScale;
import model.image.MutateAll;

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

  //GENERAL STUFF

  /**
   * Checks if a key is already in the hashmap if so overwrite it if not just add it.
   * Used to deal with overwriting.
   *
   * @param imageName the name of the image.
   * @param image     the image to be appended.
   */
  public void newEntry(String imageName, Image image) {
    if (images.get(imageName) != null) {
      images.remove(imageName);
    }
    images.put(imageName, image);
  }

  /**
   * Returns whether the model has an image under a given key.
   *
   * @param s the given key.
   * @return whether our model has this image.
   */
  @Override
  public boolean hasKey(String s) {
    for (String key : images.keySet()) {
      if (s.equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a new image with the given pixels, and the given image's contents.
   *
   * @param newVals the new pixels of the image.
   * @param r       the original image being fed into it for contents.
   * @return a new image with the same size and height (contents) as the given one.
   */
  private Image newImage(List<List<Pixel>> newVals, Image r) {
    return new ImageImpl(newVals, r.getContents().get(0), r.getContents().get(1),
            r.getContents().get(2));
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
      List<Pixel> row = new ArrayList<>();
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

  /**
   * Returns this model's hashmap.
   *
   * @return the hashmap.
   */
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
   * Returns a buffered image from a given image class.
   *
   * @param i the image name.
   * @return the image.
   */
  public BufferedImage getImage(String i) {
    return images.get(i).getBufferedImage();
  }

  //COMMANDS

  /**
   * Flips this image either vertically or horizontally, depending on the user input.
   *
   * @param direction The direction in which the user would like to flip the input.
   * @param imageName The name of the image in which the user would like to be flipped.
   * @param newName   the new name of the flipped image.
   */
  @Override
  public void flipImage(String direction, String imageName, String newName)
          throws IllegalArgumentException {
    List<List<Pixel>> newVals;
    List<List<Pixel>> oldVals = images.get(imageName).getImageVals();
    List<Integer> contents = images.get(imageName).getContents();
    if (direction.equals("horizontal")) {
      newVals = oldVals.stream().map(new ReverseAll()).collect(Collectors.toList());
    } else if (direction.equals("vertical")) {
      newVals = oldVals;
      Collections.reverse(newVals);
    } else {
      throw new IllegalArgumentException();
    }
    images.put(newName, new ImageImpl(newVals, contents.get(0), contents.get(1), contents.get(2)));
  }

  /**
   * Brightens the image at the given file name by a certain integer constant amount.
   * Can be negative or positive.
   *
   * @param constant  the val which the user would like to see the image brightened. can be + || -
   * @param imageName The name of the image in which the user would like to be flipped
   * @param newName   the name of the transformed imaeg.
   */
  @Override
  public void brighten(int constant, String imageName, String newName) {
    Image newImage = applyChanges(new MutateAll(constant), images.get(imageName));
    images.put(newName, newImage);
  }

  /**
   * Greyscales the image at the given file name by a certain channel, depending on the
   * user's input. Can be either the image's red, blue, green, luma, intensity, or value.
   *
   * @param component Component used to define the value in which the image is greyscaled.
   * @param imageName The name of the image in which the user would like to be flipped.
   * @param newName   the name of the transformed image.
   */
  @Override
  public void greyscale(String component, String imageName, String newName) {
    Image newImage = applyChanges(new GreyScale(component), images.get(imageName));
    images.put(newName, newImage);
  }

  /**
   * Applies a filter on an image using kernels and saves the new image in the hashmap
   * at the new image name location.
   *
   * @param component which type of kernel mutation one would like to do.
   * @param imageName the image location.
   * @param newName   the new image name.
   */
  @Override
  public void colorTransform(String component, String imageName, String newName) {
    Double[][] matrix;
    if (component.equals("sepia")) {
      matrix = new Double[][]{
        new Double[]{0.393, 0.769, 0.189},
        new Double[]{ 0.349, 0.686, 0.168},
        new Double[]{0.272, 0.534, 0.131}
      };
    } else if (component.equals("greyscale")) {
      matrix = new Double[][]{
        new Double[]{0.2126, 0.7152, 0.0722},
        new Double[]{0.2126, 0.7152, 0.0722},
        new Double[]{0.2126, 0.7152, 0.0722}
      };
    } else {
      throw new IllegalArgumentException();
    }

    images.put(newName, applyChanges(new MatrixMultiplication(matrix), images.get(imageName)));
  }

  //KERNEL STUFF

  /**
   * Mutates an image using a kernel and puts that version of it in a specified location in the
   * HashMap of images.
   *
   * @param component which type of kernel mutation one would like to do.
   * @param imageName the image location.
   * @param newName   the image name.
   */
  @Override
  public void kernelMutate(String component, String imageName, String newName) {
    Double[][] kernelValues;
    Image g;
    if (component.equals("sharpen")) {
      kernelValues = new Double[][]{
        new Double[]{-0.125, -0.125, -0.125, -0.125, -0.125},
        new Double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
        new Double[]{0.125, 0.25, 1.00, 0.25, -0.125},
        new Double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
        new Double[]{-0.125, -0.125, -0.125, -0.125, -0.125}
      };
    } else if (component.equals("blur")) {
      kernelValues = new Double[][]{
        new Double[]{0.0625, 0.125, 0.0625},
        new Double[]{0.125, 0.25, 0.125},
        new Double[]{0.0625, 0.125, 0.0625}
      };
    } else {
      throw new IllegalArgumentException();
    }
    g = kernelHelper(kernelValues, images.get(imageName));
    images.put(newName, g);
  }

  /**
   * Applies the kernel matrix upon an image to mutate its values.
   *
   * @param imageValues the kernel mutation matrix being applied.
   * @param p           the image.
   * @return a new mutated image.
   */
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
            if (FunctionUtils.validPosition(x + a, y + b, height, width)) {
              neighborVals.put(imageVals.get(x + a).get(y + b),
                      imageValues[centerX + a][centerY + b]);
            }
          }
        }
        row.add(calcNeighbors(neighborVals));
      }
      newVals.add(row);
    }
    return newImage(newVals, p);
  }

  /**
   * Returns a new pixel based on the values of the pixels surrounding the given pixel.
   *
   * @param neighborVals the HashMap of pixels to their values.
   * @return a new pixel.
   */
  private Pixel calcNeighbors(HashMap<Pixel, Double> neighborVals) {
    int[] newPixel = new int[3];
    for (int x = 0; x < 3; x++) {
      for (Pixel e : neighborVals.keySet()) {
        int newVal = (int) (e.getChannel(x) * neighborVals.get(e));
        newPixel[x] += newVal;
        newPixel[x] = FunctionUtils.clamp(newPixel[x]);
      }
    }
    return new PixelImpl(newPixel);
  }
}