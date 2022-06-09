package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Model.ImagePrModel;

/**
 * Represents an Image Processor Controller, allowing the user to give inputs to the program,
 * which it will exchange for data and computation from the model.
 */
public class ImagePrControllerImpl implements ImagePrController {
  private ImagePrModel model;
  private Appendable output;
  private Readable input;

  /**
   * Constructs an Image Processor Controller, given a image processor model, a view, and
   * an input source.
   */
  public ImagePrControllerImpl(Readable input,  Appendable output, ImagePrModel model) throws
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
    ArrayList<String> fields = new ArrayList<>();
    int full = 3;
    boolean brighten = false;
    String[] commandsArray = {"load-image", "save-image", "red-component", "blue-component",
    "green-component", "value-component", "luma-component", "intensity-component",
            "horizontal-flip", "vertical-flip", "brighten"};
    ArrayList<String> commandsList = new ArrayList<>(Arrays.asList(commandsArray));
    welcomeMessage();

    /*

    possible commands:

    THREE PARAMS

    load-image image-path image-name
    save-image image-path image-name
    red-component image-name dest-image-name
    blue-component image-name dest-image-name
    green-component image-name dest-image-name
    value-component image-name dest-image-name
    luma-component image-name dest-image-name
    intensity-component image-name dest-image-name
    horizontal-flip image-name dest-image-name
    vertical-flip image-name dest-image-name

    FOUR PARAMS
    brighten increment image-name dest-image-name

    params :
    all have two string params except for brightness, which takes an int and then two strings
     */

    while(!quit) {
      System.out.println("awaiting command:");
      System.out.println();

      while(fields.size() < full) {
        System.out.println("Size of fields taken in so far: " + fields.size());
        System.out.println();
        System.out.println("Fields:" + fields.toString());
        System.out.println();
        System.out.println("Amount of fields for this command: " + full);
        System.out.println();



        //try to read scanner
        try {
          cmd = fetch.next();

          //quits if q
          if(cmd.equalsIgnoreCase("q") || cmd.equalsIgnoreCase("quit")) {
            System.out.println("terminated!");
            System.out.println();
            return;
          }

          //checks brighten
          if(brighten && fields.size() == 1){
            try {
              Integer.parseInt(cmd);
              System.out.println("Adding the brightness increment to the array.");
              fields.add(cmd);
            }
            catch (Exception e) {
              System.out.println("What follows the 'brighten' command keyword must be an " +
                      "integer.");
              System.out.println();

            }
          }

          //checks to see if it's a command
          else if(fields.size() == 0) {
            if(commandsList.contains(cmd)) {
              fields.add(cmd);
              if(cmd.equals("brighten")) {
                full = 4;
                brighten = true;
              }
            }
            else {
              System.out.println("Initial command is not recognized. Please choose one from the " +
                      "list of accepted commands.");
              System.out.println();

            }
          }

          else {
            fields.add(cmd);
            System.out.println("Added the command to the fields array.");
            System.out.println();
          }
        }

        //nothing in scanner
        catch(Exception e) {
          throw new IllegalStateException("nothing in the scanner!");
        }
      }

      try {
        switch(fields.get(0)) {
          case("load-image"):
            this.model.load(fields.get(1), fields.get(2));
            break;
          case("save-image"):
            this.model.save(fields.get(1), fields.get(2));
            break;
          case("red-component"):
            this.model.greyscale("red", fields.get(1), fields.get(2));
            break;
          case("blue-component"):
            this.model.greyscale("blue", fields.get(1), fields.get(2));
            break;
          case("green-component"):
            this.model.greyscale("green", fields.get(1), fields.get(2));
            break;
          case("value-component"):
            this.model.greyscale("value", fields.get(1), fields.get(2));
            break;
          case("luma-component"):
            this.model.greyscale("luma", fields.get(1), fields.get(2));
            break;
          case("intensity-component"):
            this.model.greyscale("intenstiy", fields.get(1), fields.get(2));
            break;
          case("horizontal-flip"):
            this.model.flipImage("horizontal", fields.get(1), fields.get(2));
            break;
          case("vertical-flip"):
            this.model.flipImage("vertical", fields.get(1), fields.get(2));
            break;
          case("brighten"):
            this.model.brighten(Integer.parseInt(fields.get(1)), fields.get(2), fields.get(3));
            break;
        }
        System.out.println("command executed. ready for the next.");
      }
      catch (Exception e){
        System.out.println("File name is not recognized. please try again.");
      }
      full = 3;
      brighten = false;
      fields.clear();
    }
  }

  private void welcomeMessage() {

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
  }
}







