package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import Image.ImagePPM;
import Image.Pixel;
import Image.ReverseAll;
import Image.greyScale;
import Image.mutateAll;
import Model.ImagePrModel;

public class ImagePrModelImpl implements ImagePrModel {

  private HashMap<String, ImagePPM> images;

  /**
   * Constructs an image processing model using a 2d array of pixels.
   */
  public ImagePrModelImpl() {
    this.images = new HashMap<>();
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param fileLoc the path of the file.
   */
  public void load(String fileLoc, String fileName) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(fileLoc));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+fileLoc+ " not found!");
      throw new IllegalArgumentException();
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
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
        row.add(new Pixel(r,g,b));
      }
      imageVals.add(row);
    }
    newEntry(fileName, new ImagePPM(imageVals, width, height, maxValue));


  }


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
    images.put(newName, new ImagePPM(newVals,contents.get(0),contents.get(1), contents.get(2)));
  }

  @Override
  public void brighten(int constant, String filename, String newName) {
    ImagePPM newImage = applyChanges(new mutateAll(constant),images.get(filename));
    images.put(newName, newImage);
  }

  @Override
  public void greyscale(String component, String filename, String newName) {
    ImagePPM newImage = applyChanges(new greyScale(component),images.get(filename));
    images.put(newName, newImage);
  }

  /**
   * Hello.
   * @param fileLocation new path.
   * @param fileName current name within our hashmap.
   * @throws IOException boobs.
   */
  @Override
  public void save(String fileLocation, String fileName) throws IOException {
   images.get(fileName).makeFile(fileLocation);
  }

  /**
   * Returns whether the model has an image under a given key.
   * @param s the given key.
   * @return whether our model has this image.
   */
  public boolean hasKey(String s) {
    for (String key : images.keySet() ) {
      if(s.equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether the model has any images loaded.
   * @return whether the size of the imagegs hashmap is greater than zero.
   */
  public boolean hasEntries() {
    return images.size() > 0;
  }

  @Override
  public HashMap<String, ImagePPM> getHashMap() {
    return new HashMap<String,ImagePPM>(images);
  }

  /**
   * Checks if a key is already in the hashmap if so overwrite it if not just add it.
   * Used to deal with overwriting.
   * @param fileName the name of the file.
   * @param image the image to be appended
   */

  /*

  going to update thie method to be part of controller, checks whether there is alraedy something
  there at file first and say are you sure you want to overrite this?

   */
  private void newEntry(String fileName, ImagePPM image) {
    //Checks if the filename is already in the key if so remove it
    if (images.get(fileName) != null) {
      images.remove(fileName);
    }
    images.put(fileName, image);
  }

  /**
   * Given a one dimensional array, change it until it becomes a 2d array suitable to be imageVals.
   * @param flatlist The 1d arraylist whos data will be extracted.
   */
  private List<List<Pixel>> updateImageVals(List<Pixel> flatlist,int height,int width) {
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
   * Given a function<T,T> apply it to the pixels within imageVals.
   * @param applyFunc The function that is used to change the pixel values.
   */
  private ImagePPM applyChanges(Function<Pixel, Pixel> applyFunc, ImagePPM p) {
    List<Pixel> mapList = p.flatten();
    mapList = mapList.stream().map(applyFunc).collect(Collectors.toList());
    return newImage(updateImageVals(mapList,p.getContents().get(1),p.getContents().get(0)),p);
  }

  private ImagePPM newImage(List<List<Pixel>> newVals, ImagePPM r) {
    return new ImagePPM(newVals, r.getContents().get(0), r.getContents().get(1),
            r.getContents().get(2));
  }


}
