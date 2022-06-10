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
  private final ImagePrModel model;
  private final Appendable output;
  private final Readable input;

  /**
   * Constructs an Image Processor Controller, given an image processor model, a view, and
   * an input source.
   */
  public ImagePrControllerImpl(Readable input, Appendable output, ImagePrModel model) throws
          IllegalArgumentException {
    if (model == null || output == null | input == null) {
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
    String arf;
    ArrayList<String> fields = new ArrayList<>();
    int full = 1;
    boolean loaded = false;

    /*

     */
    Map<String, ArrayList<String>> commandsMap = Map.ofEntries(
            entry("load", new ArrayList<>(Arrays.asList("file-path", "img-name"))),
            entry("save", new ArrayList<>(Arrays.asList("file-path", "img-name"))),
            entry("red-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("blue-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("green-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("value-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("luma-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("intensity-component", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("horizontal-flip", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("vertical-flip", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("brighten", new ArrayList<>(Arrays.asList("int", "img-name", "img-dest"))));

    welcomeMessage();

    while (true) {

      System.out.println();
      System.out.println("AWAITING COMMAND ------------------");
      System.out.println();

      while (fields.size() < full) {
        try {

          arf = fetch.next();

          if (arf.equalsIgnoreCase("q") || arf.equalsIgnoreCase("quit")) {
            System.out.println("terminated!");
            System.out.println();
            fetch.close();
            return;
          }

          if (fields.size() == 0) {
            if (commandsMap.containsKey(arf)) {
              if (loaded || arf.equals("load")) {
                fields.add(arf);
                full = commandsMap.get(arf).size() + 1;
                System.out.println("Command: " + arf);
                System.out.println("# of Fields: " + (full - 1));
                System.out.println("Field types: " + commandsMap.get(arf).toString());
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
          } else {
            String parentCommand = fields.get(0);
            String inputType = (commandsMap.get(fields.get(0)).get(fields.size() - 1));
            System.out.println("input type: " + inputType);
            System.out.println("inputted command: " + arf);
            switch (inputType) {
              case ("int"):
                try {
                  System.out.println("attempting to parse int for string.");
                  Integer.parseInt(arf);
                  System.out.println("Adding int to fields.");
                  fields.add(arf);
                } catch (Exception e) {
                  System.out.println("The input " + arf + " needs to be a String!");
                }
                break;
              case ("img-name"):
                if (model.hasKey(arf) || parentCommand.equals("load")) {
                  System.out.println("arf: " + arf);
                  System.out.println("model has key: " + model.hasKey(arf));
                  System.out.println("parent command:" + parentCommand);
                  System.out.println("or command is load: " + parentCommand.equals("load"));
                  System.out.println("Adding img-name to fields.");
                  fields.add(arf);
                } else {
                  System.out.println("No image exists by this name.");
                }
                break;
              case ("img-dest"):
                if (model.hasKey(arf) && !(parentCommand.equals("load"))) {
                  System.out.println("Overwriting the image at " + arf);
                }
                System.out.println("Adding img-dest to fields.");
                fields.add(arf);
                break;
              case ("file-path"):
                File f = new File(arf);
                if (parentCommand.equals("load")) {
                  if (f.isFile()) {
                    fields.add(arf);
                    System.out.println("Adding path to fields.");
                  } else {
                    System.out.println("The input" + arf + " needs to be the route to a file!");
                  }
                } else if (parentCommand.equals("save")) {
                  if (f.isFile()) {
                    System.out.println("Overwriting the file at " + arf);
                    System.out.println("Adding path to fields.");
                    fields.add(arf);
                  } else {
                    fields.add(arf);
                  }
                }
                break;
              default:
                System.out.println("Input type not recognized.");
            }
          }
        } catch (Exception e) {
          throw new IllegalStateException("there's nothing in the scanner you moron");
        }
      }

      try {
        switch (fields.get(0)) {
          case ("load"):
            this.model.load(fields.get(1), fields.get(2));
            break;
          case ("save"):
            this.model.save(fields.get(1), fields.get(2));
            break;
          case ("red-component"):
            this.model.greyscale("red",
                    fields.get(1), fields.get(2));
            break;
          case ("blue-component"):
            this.model.greyscale("blue",
                    fields.get(1), fields.get(2));
            break;
          case ("green-component"):
            this.model.greyscale("green",
                    fields.get(1), fields.get(2));
            break;
          case ("value-component"):
            this.model.greyscale("value",
                    fields.get(1), fields.get(2));
            break;
          case ("luma-component"):
            this.model.greyscale("luma",
                    fields.get(1), fields.get(2));
            break;
          case ("intensity-component"):
            this.model.greyscale("intensity", fields.get(1), fields.get(2));
            break;
          case ("horizontal-flip"):
            this.model.flipImage("horizontal", fields.get(1), fields.get(2));
            break;
          case ("vertical-flip"):
            this.model.flipImage("vertical",
                    fields.get(1), fields.get(2));
            break;
          case ("brighten"):
            this.model.brighten(Integer.parseInt(fields.get(1)),
                    fields.get(2), fields.get(3));
            break;
        }
        System.out.println("COMMAND SUCCESSFULLY EXECUTED ----------------");
        if (fields.get(0).equals("load")) {
          loaded = true;
        }
      } catch (Exception e) {
        System.out.println("FILE NAME IS NOT RECOGNIZED ----------------");
      }

      System.out.println();
      full = 1;
      fields.clear();
    }
  }

  private void welcomeMessage() {

    System.out.println(" ________________________\n" +
            "|.----------------------.|\n" +
            "||                      ||\n" +
            "||                      ||\n" +
            "||     .-\"````\"-.       ||\n" +
            "||    /  _.._    `\\     ||\n" +
            "||   / /`    `-.   ; . .||\n" +
            "||   | |__  __  \\   |   ||\n" +
            "||.-.| | e`/e`  |   |   ||\n" +
            "||   | |  |     |   |'--||\n" +
            "||   | |  '-    |   |   ||\n" +
            "||   |  \\ --'  /|   |   ||\n" +
            "||   |   `;---'\\|   |   ||\n" +
            "||   |    |     |   |   ||\n" +
            "||   |  .-'     |   |   ||\n" +
            "||'--|/`        |   |--.||\n" +
            "||   ;    .     ;  _.\\  ||\n" +
            "||    `-.;_    /.-'     ||\n" +
            "||         ````         ||\n" +
            "||jgs___________________||\n" +
            "'------------------------'");
    System.out.println("Hello MARY HILMER, and welcom to the image pwocessor");
    System.out.println("this pwogram currently supports six commands,");
    System.out.println();

    System.out.println("load image-path image-name: \n Load an image " +
            "from the specified path " +
            "and refer it to henceforth \n in the program by the given image name.");
    System.out.println();

    System.out.println("_____-component image-name dest-image-name: \n " +
            "Create a greyscale image " +
            "with the ___-component of the image with the given name, \n " +
            "and refer to it henceforth " +
            "in the program by the given destination name. " +
            "\n replace the ___ with red, green, blue, value, luma, or intensity.");
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







