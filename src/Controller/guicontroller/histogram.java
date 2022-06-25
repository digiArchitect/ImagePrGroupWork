package Controller.guicontroller;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

public class histogram extends JPanel {
  HashMap<Integer, List<Integer>> hist;

  public histogram(HashMap<Integer, List<Integer>> hist) {
    this.hist = hist;

    setBorder(BorderFactory.createTitledBorder("Histogram"));
    //histogram.setLayout(new BoxLayout(histogram,BoxLayout.Y_AXIS));
    setPreferredSize(new Dimension(300, 124));
    setBackground(Color.LIGHT_GRAY);

  }
  public void updateVals(HashMap<Integer, List<Integer>> histo) {
    hist = histo;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    /*
    g.drawRect(10, 10, 240, 240);
    //filled Rectangle with rounded corners.
    g.fillRoundRect(50, 50, 100, 100, 80, 80);
     */
    int height = this.getHeight();
    List<Color> col = Arrays.asList(new Color(255, 0, 0, 25),
            new Color(144, 238, 144, 50),
            new Color(30, 144, 255, 75),
            new Color(255, 165, 0, 100));
    int max = 0;
    for (Integer e : hist.keySet()) {
      max = Math.max(max, Collections.max(hist.get(e)));
    }

    for (Integer e : hist.keySet()) {
      List<Integer> f = hist.get(e);
      for (int x = 0; x < 4; x++) {
        g.setColor(col.get(x));
        g.drawLine(x + e, height, x + e,
                f.get(x));
      }
    }
    /*
    g.setColor(Color.black);
    g.drawLine(25,height-20,286,height-20);
    g.drawString("Value",50,height-5);
    g.drawLine(25,height-20,25,height-125);
    String f =  "Frequency";
    int freqHeight = height - 100;
    for (int x = 0; x < f.length(); x++ ) {
      String s  = Character.toString(f.charAt(x));
      g.drawString(s,15,freqHeight + x * 10);
    }
     */
  }
}


