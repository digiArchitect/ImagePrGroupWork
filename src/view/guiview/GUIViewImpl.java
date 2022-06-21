package view.guiview;

import java.awt.*;

import javax.swing.*;

public class GUIViewImpl extends JFrame {

  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JLabel comboboxDisplay;

  public GUIViewImpl() {
    super();
    setTitle("GUI VIEW");
    setSize(600, 400);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
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
}
