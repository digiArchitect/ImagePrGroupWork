package controller.guicontroller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImagePrModel;
import model.image.FunctionUtils;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import view.guiview.GUIView;
import view.guiview.GUIViewImpl;

import static java.util.Map.entry;
import static model.image.FunctionUtils.fileTypeSupported;

/**
 * Represents a GUI Controller.
 */
public class GUIControllerImpl extends JFrame implements GUIController {
  private JComboBox imagesComboBox;
  private ImagePrModel model;
  private GUIView view;
  private boolean loaded;
  private String currentImage;
  private ArrayList<String> imageNames;
  private JLabel imageName;
  JPanel imagePanel;
  JLabel imageLabel;
  private Histogram hist;

  Map<String, ArrayList<String>> commandsMap = Map.ofEntries(entry("Greyscale by Red Component",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Greyscale by Blue Component",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Greyscale by Green Component",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Greyscale by Value Component",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Greyscale by Luma Component",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Greyscale by Intensity Component",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Horizontal Flip",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Vertical Flip",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Brighten",
                  new ArrayList<>(Arrays.asList("int", "img-name", "img-dest"))),
          entry("Blur",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Sharpen",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Greyscale by Matrix",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))),
          entry("Downscale",
                  new ArrayList<>(Arrays.asList("int", "int", "img-name", "img-dest"))),
          entry("Sepia",
                  new ArrayList<>(Arrays.asList("img-name", "img-dest"))));

  /**
   * Constructs a GUI Controller.
   *
   * @param view  the view.
   * @param model the model.
   * @throws IOException if the image methods go wrong.
   */
  public GUIControllerImpl(GUIView view, ImagePrModel model) throws IOException {
    super();
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.view = new GUIViewImpl();
    this.model = model;
    loaded = false;

    setTitle("gui view");
    setSize(1000, 800);

    //MAIN-----------------------------------------------------------------------

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //---------------------------------------------------------------------------

    //LEFT SIDE-----------------------------------------------------------------------

    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

    //IMAGE

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(600, 600));
    imagePanel.add(imageScrollPane);

    //MISC

    JPanel leftMisc = new JPanel();
    leftMisc.setLayout(new BoxLayout(leftMisc, BoxLayout.X_AXIS));

    imageName = new JLabel("No image loaded yet");
    leftMisc.add(imageName);

    //ADDING

    leftPanel.add(imagePanel);
    leftPanel.add(leftMisc);

    //---------------------------------------------------------------------------

    //RIGHT SIDE-----------------------------------------------------------------------

    JPanel rightPanel = new JPanel();
    hist = new Histogram(new HashMap<>());
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.add(hist);

    //BUTTONS

    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

    JPanel loadPanel = new JPanel();
    loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.X_AXIS));
    JLabel loadTitle = new JLabel("Load an Image");
    JButton loadButton = new JButton("load");
    loadPanel.add(loadTitle);
    loadPanel.add(loadButton);

    JPanel savePanel = new JPanel();
    savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.X_AXIS));
    JLabel saveTitle = new JLabel("Save this Image");
    JButton saveButton = new JButton("save");
    savePanel.add(saveTitle);
    savePanel.add(saveButton);


    loadButton.setActionCommand("load");
    loadButton.addActionListener(this);
    saveButton.setActionCommand("save");
    saveButton.addActionListener(this);
    buttonsPanel.add(loadPanel);
    buttonsPanel.add(savePanel);

    //COMBO BOXES

    JPanel comboBoxPanel = new JPanel();
    comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.Y_AXIS));

    JPanel imagesComboBoxPanel = new JPanel();
    JLabel imagesTitle = new JLabel("Images");
    imageNames = new ArrayList<>(Arrays.asList("no images yet!"));
    imagesComboBox = new JComboBox(imageNames.toArray());
    imagesComboBoxPanel.setLayout(new BoxLayout(imagesComboBoxPanel, BoxLayout.X_AXIS));


    imagesComboBoxPanel.add(imagesTitle);
    imagesComboBoxPanel.add(imagesComboBox);


    JPanel commandsComboBoxPanel = new JPanel();
    JLabel commandsTitle = new JLabel("Commands");
    String[] commandNames = new String[]{"Greyscale by Red Component",
            "Greyscale by Blue Component", "Greyscale by Green Component",
            "Greyscale by Value Component", "Greyscale by Luma Component",
            "Greyscale by Intensity Component", "Horizontal Flip", "Vertical Flip", "Brighten",
            "Blur", "Sharpen", "Greyscale", "Sepia", "Downscale"};
    JComboBox commandsComboBox = new JComboBox(commandNames);
    commandsComboBoxPanel.setLayout(new BoxLayout(commandsComboBoxPanel, BoxLayout.X_AXIS));


    commandsComboBoxPanel.add(commandsTitle);
    commandsComboBoxPanel.add(commandsComboBox);


    imagesComboBox.setActionCommand("Image options");
    imagesComboBox.addActionListener(this);
    commandsComboBox.setActionCommand("Command options");
    commandsComboBox.addActionListener(this);

    comboBoxPanel.add(imagesComboBoxPanel);
    comboBoxPanel.add(commandsComboBoxPanel);

    //ADDING

    rightPanel.add(buttonsPanel);
    rightPanel.add(comboBoxPanel);


    //---------------------------------------------------------------------------


    mainPanel.add(leftPanel);
    mainPanel.add(rightPanel);

  }

  /**
   * Updates our GUI.
   */
  private void update() {
    System.out.println(imageNames);
    imageName.setText(currentImage);
    List<Integer> contents = model.getImageContents(currentImage);
    List<Pixel> imageVals = model.getHashMap().get(currentImage).flatten();
    BufferedImage currentBufferedImage = this.view.image(contents, imageVals);
    hist.updateVals(model.histogram(currentImage));
    hist.repaint();

    imageLabel.setIcon(new ImageIcon(currentBufferedImage));
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("COMMAND: " + e.getActionCommand());
    switch (e.getActionCommand()) {

      case "load":
        String fileName;
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG, PNG, or PPM Images", "jpg", "ppm", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(GUIControllerImpl.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          System.out.println(f.getName());
          String fileLoc = f.getAbsolutePath();
          System.out.println(fileLoc);
          fileName = JOptionPane.showInputDialog("Please enter the name of this "
                  + "image within the processor.");
          try {
            this.load(fileLoc, fileName);
            imagesComboBox.addItem(fileName);

            if (!loaded) {
              imagesComboBox.removeItemAt(0);
              imageNames.clear();
              loaded = true;
            }

            imageNames.add(fileName);
            currentImage = fileName;

          } catch (IOException ex) {
            System.out.println("there's something wrong with the file you're trying to load!");
          }
        }


        break;

      case "save":
        String fileType = JOptionPane.showInputDialog("Give file name");
        try {
          save(fileType, currentImage, this.model.getImageContents(currentImage),
                  this.model.getHashMap());
        } catch (Exception f) {
          System.out.println("bad file name");
        }
        break;
      case "Command options":
        JComboBox box = (JComboBox) e.getSource();
        String c = box.getSelectedItem().toString();
        int amountOfNeededParams = commandsMap.get(c).size();
        int[] params = new int[amountOfNeededParams - 2];
        if (amountOfNeededParams > 2) {
          for (int i = 0; i < amountOfNeededParams - 2; i++) {
            params[i] = Integer.parseInt(JOptionPane.showInputDialog("param needed!"));
          }
        }

        System.out.println("-------------");
        System.out.println();
        System.out.println("switch test");
        System.out.println();
        System.out.println("c: " + c);
        switch (c) {
          case "Greyscale by Red Component":
            System.out.println("greyscaled by red");
            this.model.greyscale("red", currentImage, currentImage);
            break;
          case "Greyscale by Blue Component":
            System.out.println("greyscaled by blue");
            this.model.greyscale("blue", currentImage, currentImage);
            break;
          case "Greyscale by Green Component":
            System.out.println("greyscaled by green");
            this.model.greyscale("green", currentImage, currentImage);
            break;
          case "Greyscale by Luma Component":
            this.model.greyscale("luma", currentImage, currentImage);
            break;
          case "Greyscale by Intensity Component":
            this.model.greyscale("intensity", currentImage, currentImage);
            break;
          case "Greyscale by Value Component":
            this.model.greyscale("value", currentImage, currentImage);
            break;
          case "Horizontal Flip":
            this.model.flipImage("horizontal", currentImage, currentImage);
            break;
          case "Vertical Flip":
            this.model.flipImage("vertical", currentImage, currentImage);
            break;
          case "Brighten":
            this.model.brighten(params[0], currentImage, currentImage);
            break;
          case "Blur":
            this.model.kernelMutate("blur", currentImage, currentImage);
            break;
          case "Sharpen":
            this.model.kernelMutate("sharpen", currentImage, currentImage);
            break;
          case "Greyscale":
            this.model.colorTransform("greyscale", currentImage, currentImage);
            break;
          case "Sepia":
            this.model.colorTransform("sepia", currentImage, currentImage);
            break;
          case "Downscale":
            this.model.imageDownscale(params[0], params[1], currentImage, currentImage);
            break;
          default:
            System.out.println("command not recognized");
            break;
        }
        break;
      case "Image options":
        box = (JComboBox) e.getSource();
        c = box.getSelectedItem().toString();
        currentImage = c;
        break;
      default:
        System.out.println("nothing");
    }
    update();
  }


  /**
   * Read an image file in the PPM format and stores it in this model's hashmap.
   *
   * @param fileLoc the path of the file.
   */
  private void load(String fileLoc, String fileName) throws IOException {

    String rev = new StringBuilder(fileLoc).reverse().toString();
    int dot = rev.indexOf(".");
    String fileType;
    fileType = rev.substring(0, dot);
    fileType = new StringBuilder(fileType).reverse().toString();
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
   *
   * @param fileLoc   the file source of the image to be loaded.
   * @param imageName the name of the image to be put in the HashMap.
   */
  private void loadPPM(String fileLoc, String imageName) {
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
    java.util.List<java.util.List<Pixel>> imageVals = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      java.util.List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int[] vals = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()};
        row.add(new PixelImpl(vals));
      }

      imageVals.add(row);
    }
    this.model.newEntry(imageName, new ImageImpl(imageVals, width, height, maxValue));
    System.out.println(imageName + "successfully loaded!");
  }

  /**
   * Loads an image file into the model's hashmap.
   *
   * @param fileLoc   the file source of the image to be loaded.
   * @param imageName the name for the image to be put into the hashmap under.
   * @throws IOException if unable to read the file input stream of the fileLoc.
   */
  private void loadSupported(String fileLoc, String imageName) throws IOException {
    BufferedImage t = ImageIO.read(new FileInputStream(fileLoc));
    int width = t.getWidth();
    int height = t.getHeight();
    int maxValue = 255;
    java.util.List<java.util.List<Pixel>> imageVals = new ArrayList<>();
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
    System.out.println(imageName + "successfully loaded!");
  }

  private void save(String fileLoc, String fileName, List<Integer> contents,
                    HashMap<String, Image> images) throws IOException {
    String fileType = fileLoc.split("[.]")[1];
    if (fileTypeSupported(fileType)) {
      saveSupported(fileLoc, contents, images.get(fileName).flatten(), fileType);
    } else if (fileType.equals("ppm")) {
      savePPM(fileLoc, contents, FunctionUtils.getFlatten(images, fileName));
    } else {
      throw new IllegalArgumentException();
    }
  }

  private void saveSupported(String fileLocation, List<Integer> contents, List<Pixel> imageVals,
                             String fileType) throws IOException {
    BufferedImage b = new BufferedImage(contents.get(0), contents.get(1),
            BufferedImage.TYPE_INT_RGB);
    int count = 0;
    for (int x = 0; x < contents.get(1); x++) {
      for (int y = 0; y < contents.get(0); y++) {
        b.setRGB(y, x, imageVals.get(count).hashCode());
        count++;
      }
    }
    ImageIO.write(b, fileType, new File(fileLocation));
  }

  private void savePPM(String fileLocation, List<Integer> contents, List<String> mapList)
          throws IOException {
    File newFile = new File(fileLocation);
    FileWriter w = new FileWriter(newFile);
    StringBuilder s = new StringBuilder();
    s.append("P3\n");
    s.append(contents.get(0));
    s.append(" ");
    s.append(contents.get(1));
    s.append("\n");
    s.append(contents.get(2));
    s.append("\n");
    int count = 0;
    for (int x = 0; x < contents.get(1); x++) {
      for (int y = 0; y < contents.get(0); y++) {
        s.append(mapList.get(count));
        count++;
      }
      s.append("\n");
    }
    s.append("\n");
    w.write(s.toString());
    w.close();
  }

}
