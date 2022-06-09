package Image;


/**
 Represents a Image.Pixel of a PPM image.
 */
public class Pixel {
  int r;
  int g;
  int b;


  public Pixel(int r, int g, int b) {
    if(r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0) {
      throw new IllegalArgumentException();
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }


  public int getChannel(int c) throws IllegalArgumentException {
    switch(c) {
      case 0:
        return r;
      case 1:
        return g;
      case 2:
        return b;
      default:
        throw new IllegalArgumentException("Choose a valid color between 0, 1, and 2");
    }
  }
  @Override
  public String toString() {
    return "";
  }
}