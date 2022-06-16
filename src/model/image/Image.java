package model.image;

import java.util.List;

import model.pixel.Pixel;

public interface Image {
  public List<List<Pixel>> getImageVals();
  public List<Integer> getContents();
  public List<Pixel> flatten();
  public Pixel getPixel(int r, int c);
}
