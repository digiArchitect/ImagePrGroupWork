package view.guiview;

import java.awt.image.BufferedImage;
import java.util.List;

import model.image.Pixel;

public interface GUIView {
  public BufferedImage image(List<Integer> contents, List<Pixel> imageVals);
}
