package Controller;

import java.util.Scanner;

import Model.ImageModel;

/**
 * Represents an Image Processor Controller, allowing the user to give inputs to the program,
 * which it will exchange for data and computation from the model.
 */
public class ImagePrControllerImpl implements ImagePrController {
  private ImageModel model;
  private Appendable output;
  private Readable input;

  /**
   * Constructs an Image Processor Controller, given a image processor model, a view, and
   * an input source.
   */
  public ImagePrControllerImpl(Readable input,  Appendable output, ImageModel model) throws
          IllegalArgumentException {
    if(model == null || output == null | input == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.output = output;
    this.input = input;
  }

  /**
   * Opens the image processor for user input.
   */
  @Override
  public void startProcessor() throws IllegalStateException {
    Scanner fetch = new Scanner(input);
    boolean quit = false;

    String cmd;


    System.out.println("Hellom, and welcom to the image pwocessor");
    System.out.println("this pwogram currently supports six commands,");
    System.out.println();
    System.out.println("load image-path image-name: \n Load an image " +
            "from the specified path " +
            "and refer it to henceforth \n in the program by the given image name.");
    System.out.println();
    System.out.println("red-component image-name dest-image-name: \n " +
            "Create a greyscale image " +
            "with the red-component of the image with the given name, \n " +
            "and refer to it henceforth " +
            "in the program by the given destination name. " +
            "\n Similar commands for green, blue, value, luma, intensity components should be " +
            "supported.");
    System.out.println();
    System.out.println("horizontal-flip image-name dest-image-name: \n " +
            "Flip an image horizontally " +
            "to create a new image, \n referred to henceforth by the given destination name.");
    System.out.println();
    System.out.println("vertical-flip image-name dest-image-name: \n " +
            "Flip an image vertically to " +
            "create a new image, \n referred to henceforth by the given destination name.");
    System.out.println();
    System.out.println("brighten increment image-name dest-image-name: \n " +
            "brighten the image by the " +
            "given increment to create a new image, \n referred to henceforth by the given " +
            "destination name. \n The increment may be positive (brightening) or negative " +
            "(darkening)");
    System.out.println();


    while(!quit) {
      try {
        cmd = fetch.next();
        System.out.println("input command: " + cmd);
        if(cmd.equalsIgnoreCase("q") || cmd.equalsIgnoreCase("quit")) {
          quit = true;
          System.out.println("terminated!");
        }
        switch(cmd) {
          case "load":

        }
      }
      catch(Exception e) {
        throw new IllegalStateException("nothing in the scanner!");
      }
    }
  }
}







