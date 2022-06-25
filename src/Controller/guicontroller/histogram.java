package controller.guicontroller;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;


/**
 * Represents a histogram for our program.
 */
public class Histogram extends JPanel {
  HashMap<Integer, List<Integer>> hist;

  /**
   * Constructs a histogram using a hashmap of integers to list of integers.
   * @param hist the histogram.
   */
  public Histogram(HashMap<Integer, List<Integer>> hist) {
    this.hist = hist;
    setBorder(BorderFactory.createTitledBorder("Histogram"));
    setPreferredSize(new Dimension(300, 124));
    setBackground(Color.LIGHT_GRAY);

  }

  /**
   * Updates the values of the histogram.
   * @param histo the histogram map of values.
   */
  public void updateVals(HashMap<Integer, List<Integer>> histo) {
    hist = histo;
  }

  /**
   * Paints a component with paint.
   * @param g the graphic to paint.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
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
  }
}


