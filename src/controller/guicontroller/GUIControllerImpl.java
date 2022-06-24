package controller.guicontroller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.*;
import model.ImagePrModel;
import view.guiview.GUIView;

public class GUIControllerImpl extends JFrame implements ActionListener {

  private JPanel rightPanel;

  private JPanel leftPanel;

  private JComboBox imagesComboBox;

  private JComboBox commandsComboBox;

  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private ImagePrModel model;

  private GUIView view;

  public GUIControllerImpl(GUIView view, ImagePrModel model) throws IOException {
    super();
    this.view = view;
    this.model = model;

    setTitle("gui view");
    setSize(1000, 800);

    //MAIN-----------------------------------------------------------------------

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //---------------------------------------------------------------------------

    //LEFT SIDE-----------------------------------------------------------------------

    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

      //IMAGE

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    JScrollPane imageScrollPane = new JScrollPane();
    imageScrollPane.setPreferredSize(new Dimension(600, 600));
    imagePanel.add(imageScrollPane);

      //MISC

    JPanel leftMisc = new JPanel();
    leftMisc.setLayout(new BoxLayout(leftMisc, BoxLayout.X_AXIS));

    JLabel imageName = new JLabel("Test");
    leftMisc.add(imageName);

      //ADDING

    leftPanel.add(imagePanel);
    leftPanel.add(leftMisc);

    //---------------------------------------------------------------------------

    //RIGHT SIDE-----------------------------------------------------------------------

    rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

      //COMBO BOXES

    JPanel comboBoxPanel = new JPanel();
    comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.Y_AXIS));

    JPanel imagesComboBoxPanel = new JPanel();
    JLabel imagesTitle = new JLabel("Images");
    String[] imageNames = {"image 1"};
    imagesComboBox = new JComboBox(imageNames);

    imagesComboBoxPanel.setLayout(new BoxLayout(imagesComboBoxPanel, BoxLayout.X_AXIS));
    imagesComboBoxPanel.add(imagesTitle);
    imagesComboBoxPanel.add(imagesComboBox);

    JPanel commandsComboBoxPanel = new JPanel();
    JLabel commandsTitle = new JLabel("Commands");
    String[] commandNames = {"Greyscale by Luma", "Greyscale by Intensity",
            "Greyscale by Component", "Greyscale by Red", "Greyscale by Blue",
            "Greyscale by Green", "Greyscale by Matrix", "Blur", "Sharpen", "Sepia", "Brighten",
            "Vertical Flip", "Horizontal Flip"};
    commandsComboBox = new JComboBox(commandNames);

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

    rightPanel.add(comboBoxPanel);

    //---------------------------------------------------------------------------


    mainPanel.add(leftPanel);
    mainPanel.add(rightPanel);

  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("ballsa");
  }
}
