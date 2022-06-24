package controller.prcontroller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import model.ImagePrModel;
import view.prview.ImagePrView;

import static model.image.FunctionUtils.fileTypeSupported;
import static java.util.Map.entry;

/**
 * Represents an Image Processor Controller, allowing the user to give inputs to the program,
 * which it will exchange for data and computation from the model.
 */
public class ImagePrControllerImpl implements ImagePrController {
  private final ImagePrModel model;
  private final ImagePrView view;
  private final Readable input;

  /**
   * Constructs an Image Processor Controller, given an image processor model, a view, and
   * an input source.
   */
  public ImagePrControllerImpl(Readable input, ImagePrView view, ImagePrModel model) throws
          IllegalArgumentException {
    if (model == null || view == null | input == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.view = view;
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
            entry("brighten", new ArrayList<>(Arrays.asList("int", "img-name", "img-dest"))),
            entry("blur", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("sharpen", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("greyscale", new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
            entry("downscale", new ArrayList<>(Arrays.asList("int","int",
                    "img-name", "img-dest"))),
            entry("sepia", new ArrayList<>(Arrays.asList("img-name", "img-dest"))));

    welcomeMessage();

    while (true) {

      System.out.println();
      System.out.println("~ ~ ~ ~ ~ awaiting command! ~ ~ ~ ~ ~");
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
            switch (inputType) {
              case ("int"):
                try {
                  Integer.parseInt(arf);
                  fields.add(arf);
                } catch (Exception e) {
                  System.out.println("The input " + arf + " needs to be a String!");
                }
                break;

              case ("img-name"):
                if (model.hasKey(arf) || parentCommand.equals("load")) {
                  fields.add(arf);
                } else {
                  System.out.println("No image exists by this name.");
                }
                break;
              case ("img-dest"):
                if (model.hasKey(arf) && !(parentCommand.equals("load"))) {
                  System.out.println("Overwriting the image at " + arf);
                }
                fields.add(arf);
                break;
              case ("file-path"):
                File f = new File(arf);
                if (parentCommand.equals("load")) {
                  if (f.isFile()) {
                    fields.add(arf);
                  } else {
                    System.out.println("The input" + arf + " needs to be the route to a file!");
                  }
                } else if (parentCommand.equals("save")) {
                  if (f.isFile()) {
                    System.out.println("Overwriting the file at " + arf);
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
          throw new IllegalStateException("There's nothing in the scanner!");
        }
      }

      try {
        switch (fields.get(0)) {
          case ("load"):
            load(fields.get(1), fields.get(2));
            break;
          case ("save"):
            this.view.save(fields.get(1), fields.get(2),
                    this.model.getImageContents(fields.get(2)),
                    this.model.getHashMap());
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
          case ("sharpen"):
            this.model.kernelMutate("sharpen",
                    fields.get(1), fields.get(2));
            break;
          case ("blur"):
            this.model.kernelMutate("blur",
                    fields.get(1), fields.get(2));
            break;
          case ("sepia"):
            this.model.colorTransform("sepia",
                    fields.get(1), fields.get(2));
            break;
          case ("greyscale"):
            this.model.colorTransform("greyscale",
                    fields.get(1), fields.get(2));
            break;
          case ("downscale"):
            this.model.imageDownscale(Integer.parseInt(fields.get(1)),
                    Integer.parseInt(fields.get(2)),
                    fields.get(3), fields.get(4));
            break;
          default:
            throw new IllegalStateException("No command executed!");
        }
        System.out.println("COMMAND SUCCESSFULLY EXECUTED ----------------");
        if (fields.get(0).equals("load")) {
          loaded = true;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("IMAGE NAME IS NOT RECOGNIZED ----------------");
      }

      System.out.println();
      full = 1;
      fields.clear();
    }
  }

  /**
   * Sends a welcome message to the user.
   */
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
    System.out.println("Hello, and WELCOME to Jylah and Archie's image processor!");
    System.out.println("this program currently supports eleven commands, which are \n" +
            "listed below. If at any point the user submits invalid input, the scanner will \n" +
                    "continue attempting to parse for valid values until it completes a \n" +
            "method. Enter q or Q at any time to quit. \n Make sure to load an image " +
            "before attempting to perform any image manipulations!");

    System.out.println("COMMANDS:");
    System.out.println("------------------------------------");
    System.out.println("LOAD");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("load image-path image-name");
    System.out.println("Field Types:");
    System.out.println("file path, string");
    System.out.println("Description:");
    System.out.println("Loads a file from your computer into the processor.");
    System.out.println("----------");
    System.out.println("SAVE");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("save image-path image-name");
    System.out.println("Field Types:");
    System.out.println("file path, string");
    System.out.println("Description:");
    System.out.println("Saves a file from your processor to your computer.");
    System.out.println("----------");
    System.out.println("GREYSCALE");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("______-channel image-name image-dest");
    System.out.println("Field Types:");
    System.out.println("string, string");
    System.out.println("Description:");
    System.out.println("Creates a greyscale image from the image at the given image-name" +
            "\n via one of six channels: \n" +
            "red \n" +
            "blue \n" +
            "green \n" +
            "luma \n" +
            "intensity \n" +
            "value \n " +
            "Replace the ______ in the format with any of these listed strings to " +
            "\n call the command.");
    System.out.println("----------");
    System.out.println("BRIGHTEN");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("brighten increment img-name img-dest");
    System.out.println("Field Types:");
    System.out.println("integer, string, string");
    System.out.println("Description:");
    System.out.println("Brightens or darkens an image, depending on the \n" +
            "positive or negative integer value you enter..");
    System.out.println("----------");
    System.out.println("FLIP");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("____-flip image-path image-name");
    System.out.println("Field Types:");
    System.out.println("string, string");
    System.out.println("Description:");
    System.out.println("Flips your image, either horizontally or vertically. \n" +
            "Choose between by replacing the _____ in the format with either \n" +
            "horizontal or vertical.");
    System.out.println("");
    System.out.println("----------");
    System.out.println("BLUR");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("blur image-path image-name");
    System.out.println("Field Types:");
    System.out.println("string, string");
    System.out.println("Description:");
    System.out.println("Blurs your image using kernel matrices.");
    System.out.println("");
    System.out.println("----------");
    System.out.println("SHARPEN");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("sharpen image-path image-name");
    System.out.println("Field Types:");
    System.out.println("string, string");
    System.out.println("Description:");
    System.out.println("Sharpens your image using kernel matrices.");
    System.out.println("");
    System.out.println("----------");
    System.out.println("GREYSCALE");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("blur image-path image-name");
    System.out.println("Field Types:");
    System.out.println("string, string");
    System.out.println("Description:");
    System.out.println("Greyscales your image using kernel matrices.");
    System.out.println("");
    System.out.println("----------");
    System.out.println("SEPIA");
    System.out.println("----------");
    System.out.println("Command format:");
    System.out.println("sepia image-path image-name");
    System.out.println("Field Types:");
    System.out.println("string, string");
    System.out.println("Description:");
    System.out.println("Applies a sepia filter on your image.");
    System.out.println("");
  }


  /**
   * Read an image file in the PPM format and stores it in this model's hashmap.
   *
   * @param fileLoc the path of the file.
   */
  public void load(String fileLoc, String fileName) throws IOException {
    String fileType = fileLoc.split("[.]")[1];
    if (fileTypeSupported(fileType)) {
      loadSupported(fileLoc, fileName);
    } else if (fileType.equals("ppm")) {
      loadPPM(fileLoc, fileName);
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Loads a ppm image into the model's hashmap.
   * @param fileLoc the file source of the image to be loaded.
   * @param imageName the name of the image to be put in the HashMap.
   */
  public void loadPPM(String fileLoc, String imageName) {
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
        int[] vals = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
        row.add(new PixelImpl(vals));
      }

      imageVals.add(row);
    }
    this.model.newEntry(imageName, new ImageImpl(imageVals, width, height, maxValue));
  }

  /**
   * Loads an image file into the model's hashmap.
   * @param fileLoc the file source of the image to be loaded.
   * @param imageName the name for the image to be put into the hashmap under.
   * @throws IOException if unable to read the file input stream of the fileLoc.
   */
  public void loadSupported(String fileLoc, String imageName) throws IOException {
    BufferedImage t = ImageIO.read(new FileInputStream(fileLoc));
    int width = t.getWidth();
    int height = t.getHeight();
    int maxValue = 255;
    List<List<Pixel>> imageVals = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int rgb = t.getRGB(j, i);
        Pixel p = new PixelImpl(rgb);
        row.add(p);
      }
      imageVals.add(row);
    }
    this.model.newEntry(imageName, new ImageImpl(imageVals, width, height, maxValue));
  }
}







