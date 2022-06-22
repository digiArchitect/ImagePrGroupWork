package view.guiview;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

public class GUIViewImpl extends JFrame {

  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  public GUIViewImpl() {
    super();
    HashMap<Integer,Integer> hist = new HashMap<>();
    setTitle("GUI VIEW");
    setSize(600, 400);

    mainPanel = new JPanel();

    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.setBackground(Color.white);

    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(50, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel,BorderLayout.CENTER);


    //JPanel histogram = new JPanel();
    // this.add(panel,BorderLayout.PAGE_END)
    JPanel histogram = new histogram();
    //JScrollPane histScroll = new JScrollPane(histogram);
    //add(histScroll);


    mainPanel.add(histogram,BorderLayout.LINE_END);




    }













  }

