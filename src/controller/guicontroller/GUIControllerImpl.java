package controller.guicontroller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.prcontroller.ImagePrController;
import model.ImagePrModel;

public class GUIControllerImpl extends JFrame implements ActionListener, EventListener {

  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JLabel comboboxDisplay;

  private ImagePrModel model;

  private ImagePrController controller;

  public GUIControllerImpl(ImagePrController controller, ImagePrModel model) throws IOException {
    super();
    this.controller = controller;
    setTitle("GUI VIEW");
    setSize(600, 400);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));

    JLabel imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane();


    this.controller.load("res/mangoes.ppm", "balls");
    System.out.println(this.model.getImageContents("balls"));

    BufferedImage myPicture = this.model.getImage("balls");

    imageLabel.setIcon(new ImageIcon(myPicture));


    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane);
    mainPanel.add(imagePanel);

    //combo boxes

    JPanel comboboxPanel = new JPanel();
    comboboxPanel.setLayout(new BoxLayout(comboboxPanel, BoxLayout.PAGE_AXIS));
    comboboxDisplay = new JLabel("Cold Stone Creamery: Which size do you "
            + "want?");
    String[] options = {"Like it", "Love it", "Gotta have it"};
    JComboBox<String> combobox = new JComboBox<String>(options);

    comboboxPanel.add(comboboxDisplay);
    comboboxPanel.add(combobox);
    mainPanel.add(comboboxPanel);
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

  }

  public void refresh() {

  }
}
