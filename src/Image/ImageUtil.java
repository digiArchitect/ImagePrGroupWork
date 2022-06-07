package Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import Model.ImagePrModel;


/*
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */

/**
 * Utility methods to read a PPM image from a file and print its contents.
 */
public class ImageUtil {
  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static ImagePPM readPPM(String filename) {
    Scanner sc;
    
    try {
        sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
        System.out.println("File "+filename+ " not found!");
        throw new IllegalArgumentException();
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0)!='#') {
            builder.append(s).append(System.lineSeparator());
        }
    }
    
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token; 

    token = sc.next();
    if (!token.equals("P3")) {
        System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);



    List<List<Pixel>> imageVals = new ArrayList<>();
    
    for (int i = 0; i < height; i++) {
      List<Pixel> row = new ArrayList<>();
        for (int j = 0; j < width; j++) {
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            StringBuilder s = new StringBuilder();
            s.append(r);
            s.append(g);
            s.append(b);
            //PlaceHolder pixel values.
            row.add(new Pixel(Integer.parseInt(s.toString())));
            System.out.println("Color of pixel (" + j + "," + i +"): " + r + "," + g + "," + b);
        }
        imageVals.add(row);
    }
    return new ImagePPM(imageVals,filename,width,height,maxValue);
  }

  //demo main
  public static void main(String []args) {
      String filename;
      
      if (args.length>0) {
          filename = args[0];
      }
      else {
          filename = "sample.ppm";
      }
      
      ImagePrModel m = new ImagePrModel(ImageUtil.readPPM(filename));
  }
}

