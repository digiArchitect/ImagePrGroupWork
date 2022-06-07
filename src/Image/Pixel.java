package Image;

import java.util.function.Function;

/**
 Represents a Image.Pixel of a PPM image.
 */
public class Pixel {
  int rgb;

  public Pixel(int rgb) {
    this.rgb = rgb;
  }

  //example:
  //199353211

  public int getChannel(int c) throws IllegalArgumentException {
    switch(c) {
      case 0:
        return rgb%1000; // works
      case 1:
        return ((rgb%1000000) - rgb%1000) / 1000;
      case 2:
        return (rgb - (rgb%1000000))/1000000;
      default:
        throw new IllegalArgumentException("Choose a valid color between 0, 1, and 2");
    }
  }
  @Override
  public String toString() {
    return rgb + "";
  }



}


