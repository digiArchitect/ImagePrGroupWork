package Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import Controller.ImagePrController;
import Model.ImagePrModel;

import static java.util.Map.entry;

/**
 * Represents an Image Processor Controller, allowing the user to give inputs to the program,
 * which it will exchange for data and computation from the model.
 */
public class ImagePrControllerImplMock implements ImagePrController {
  private final ImagePrModel model;
  private final Appendable output;
  private final Readable input;
  public final ArrayList<String> log;

  /**
   * Constructs an Image Processor Controller, given an image processor model, a view, and
   * an input source.
   */
  public ImagePrControllerImplMock(Readable input, Appendable output, ImagePrModel model) throws
          IllegalArgumentException {
    if (model == null || output == null | input == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.output = output;
    this.input = input;
    this.log = new ArrayList<>();
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
                  Integer.parseInt(arf);
                  System.out.println("Adding int to fields.");
                  fields.add(arf);
                } catch (Exception e) {
                  throw new IllegalArgumentException("The input " + arf + " needs to be a String!");
                }
                break;

              case ("img-name"):
                if (model.hasKey(arf) || parentCommand.equals("load")) {
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

      System.out.println("INPUT LOG: " + log);

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







