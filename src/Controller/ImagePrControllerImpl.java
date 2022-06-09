package Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import Model.ImagePrModel;
import static java.util.Map.entry;

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
    int full = 1;
    boolean loaded = false;

    /*

     */
    Map<String, ArrayList<String>> commandsMap = Map.ofEntries(
            entry("load-image", new ArrayList<>(Arrays.asList("file-path", "img-name"))),
            entry("save-image", new ArrayList<>(Arrays.asList("file-path", "img-name"))),
            entry("red-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("blue-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("green-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("value-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("luma-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("intensity-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("horizontal-flip", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("vertical-flip", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("brighten", new ArrayList<>(Arrays.asList("int", "img-name", "img-dest"))));

    //welcomeMessage();

    while(!quit) {

      System.out.println();
      System.out.println("AWAITING COMMAND ------------------");
      System.out.println();

      while(fields.size() < full) {
        try {

          cmd = fetch.next();

          if(cmd.equalsIgnoreCase("q") || cmd.equalsIgnoreCase("quit")) {
            System.out.println("terminated!");
            System.out.println();
            return;
          }

          if (fields.size() == 0) {
            if (commandsMap.containsKey(cmd)) {
              if (loaded || cmd.equals("load-image")) {
                fields.add(cmd);
                full = commandsMap.get(cmd).size() + 1;
                System.out.println("Command: " + cmd);
                System.out.println("# of Fields: " + full);
                System.out.println("Field types: " + commandsMap.get(cmd).toString());
              } else {
                System.out.println("You need to load an image " +
                        "before processing any other commands");
              }
            } else {
              System.out.println("Initial command is not recognized. " +
                      "Please choose one from the " +
                      "list of accepted commands.");
              System.out.println();
            }
          }

          else {
            String inputType = (commandsMap.get(fields.get(0)).get(fields.size()-1));
            String parentCommand = fields.get(0);
            System.out.println("file type: " + inputType);
            System.out.println("cmd: " + cmd);

            switch (inputType) {

              case("int"):
                try {
                  Integer.parseInt(cmd);
                  System.out.println("Adding int to fields.");
                  fields.add(cmd);
                }
                catch (Exception e) {
                  throw new IllegalArgumentException("The input " + cmd + " needs to be a String!");
                }
                break;

              case("img-name"):
                if (model.hasKey(cmd) || parentCommand.equals("load-image")) {
                  System.out.println("Adding image to fields.");
                  fields.add(cmd);
                }
                else {
                  System.out.println("No image exists by this name.");
                }
                break;
              case("img-dest"):
                if(model.hasKey(cmd) && !(parentCommand.equals("load-image"))) {
                  System.out.println("Overwriting the image at " + cmd);
                }
                System.out.println("Adding destination name to fields.");
                fields.add(cmd);
                break;
              case("img-path"):
                System.out.println("IMAGE PATH" + cmd);
                File f = new File(cmd);
                if(parentCommand.equals("load-image")) {
                  if(f.isFile()) {
                    fields.add(cmd);
                    System.out.println("Adding path to fields.");
                  }
                  else {
                    System.out.println("The input" + cmd + " needs to be the route to a file!");
                  }
                }
                else if(parentCommand.equals("save-image")) {
                  if(f.isFile()) {
                    System.out.println("Overwriting the file at " + cmd);
                    System.out.println("Adding path to fields.");
                    fields.add(cmd);
                  }
                  else {
                    fields.add(cmd);
                  }
                }
                break;
            }
          }
        }

        catch(Exception e) {
          throw new IllegalStateException("nothing in the scanner!");
        }
      }

      try {
        switch(fields.get(0)) {
          case("load-image"):
            loaded = true;
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

        System.out.println("COMMAND SUCCESSFULLY EXECUTED ----------------");
      }
      catch (Exception e){
        System.out.println("FILE NAME IS NOT RECOGNIZED ----------------");
      }

      System.out.println();
      full = 1;
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







