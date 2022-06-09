package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Image.ImagePPM;
import Image.Pixel;
import Image.greyScale;
import Image.mutateAll;
import Model.ImagePrModel;

public class ImagePrModelImpl implements ImagePrModel {

  HashMap<String, ImagePPM> images;

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
  // 9 3 5  6 6 4  9 9 8  = 9
  //


  @Override
  public void flipImage(String direction, String filename, String newName) {
    ImagePPM newImage;
    if (direction.equals("horizontal")) {
      newImage = images.get(filename).horizontal();

    } else if (direction.equals("vertical")) {
      newImage = images.get(filename).vertical();

    } else {
      //Catch and throw an illegal state in the controller.
      throw new IllegalArgumentException();
    }
    images.put(newName, newImage);

  }

  @Override
  public void brighten(int constant, String filename, String newName) {
    ImagePPM newImage = images.get(filename).applyChanges(new mutateAll(constant));
    images.put(newName, newImage);
  }

  @Override
  public void greyscale(String component, String filename, String newName) {
    ImagePPM newImage = images.get(filename).applyChanges(new greyScale(component));
    images.put(newName, newImage);
  }

  @Override
  public void save(String fileLocation, String fileName) throws IOException {
   images.get(fileName).makeFile(fileLocation);
  }

  /**
   * Checks if a key is already in the hashmap if so overwrite it if not just add it.
   * Used to deal with overwriting.
   * @param fileName the name of the file.
   * @param image the image to be appended
   */
  private void newEntry(String fileName, ImagePPM image) {
    //Checks if the filename is already in the key if so remove it
    if (images.get(fileName) != null) {
      images.remove(fileName);
    }
    images.put(fileName, image);

  }

}
