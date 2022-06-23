package view.guiview;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

public class histogram extends JPanel {
  HashMap<Integer, List<Integer>> hist;
  histogram(HashMap<Integer, List<Integer>> hist) {
    this.hist = hist;

    setBorder(BorderFactory.createTitledBorder("Histogram"));
    //histogram.setLayout(new BoxLayout(histogram,BoxLayout.Y_AXIS));
    setPreferredSize(new Dimension(300,150));
    setBackground(Color.LIGHT_GRAY);
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
    int width = this.getWidth();
    List<Color> col = Arrays.asList(Color.red,Color.green,Color.blue, Color.ORANGE);
    int max = 0;
    for (Integer e: hist.keySet()) {
      max = Math.max(max, Collections.max(hist.get(e)));
    }

    for (Integer e: hist.keySet()) {
      List<Integer> f = hist.get(e);
      for (int x = 0; x < 4; x++) {
        //hist.put(x, x+5);
        g.drawLine(x + e*2 + 30, height - 25, x + e*2 + 30,
      ((height - f.get(x))*max)/max - 25);
        g.setColor(col.get(x));


      }
    }
    g.setColor(Color.black);
    g.drawLine(25,height-20,width,height-20);
    g.drawString("Value",50,height-5);
    g.drawLine(25,height-20,25,height-125);
    String f =  "Frequency";
    int freqHeight = height - 100;
    for (int x = 0; x < f.length(); x++ ) {
      String s  = Character.toString(f.charAt(x));
      g.drawString(s,15,freqHeight + x * 10);
    }




    //g.fillRect(startingX,startingY,5,endingY);




    //your code to the draw the board should go here.
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell

  }


}
