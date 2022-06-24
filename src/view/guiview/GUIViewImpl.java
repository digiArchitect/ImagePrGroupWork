package view.guiview;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import model.image.FunctionUtils;
import model.image.Image;
import model.image.Pixel;

import static model.image.FunctionUtils.fileTypeSupported;

public class GUIViewImpl implements GUIView {
  public GUIViewImpl() {}

  public BufferedImage image(List<Integer> contents, List<Pixel> imageVals) {
    BufferedImage b = new BufferedImage(contents.get(0),
            contents.get(1), BufferedImage.TYPE_INT_RGB);
    int count = 0;
    for (int x = 0; x < contents.get(1); x++) {
      for (int y = 0; y < contents.get(0); y++) {
        b.setRGB(y, x, imageVals.get(count).hashCode());
        count++;
      }
    }
    return b;
  }
}