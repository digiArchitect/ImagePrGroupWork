package pixel;

/**
 Represents a Pixel of an ImageImpl.
 */
public class Pixel {
  int r;
  int g;
  int b;


  /**
   * Constructs a pixel given a red, blue, and green value.
   * @param r red value.
   * @param g blue value.
   * @param b green value.
   * @throws IllegalArgumentException if any value is below zero or above 255.
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r > 255 || r < 0 || g > 255 || g < 0 || b > 255 || b < 0) {
      throw new IllegalArgumentException();
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the pixel's specified channel value.
   * @param c the channel.
   * @return the number for the channel.
   * @throws IllegalArgumentException if a channel besides r,g,b is requested.
   */
  public int getChannel(int c) throws IllegalArgumentException {
    switch (c) {
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

  /**
   * Tests whether a pixel is equal to another pixel by seeing if all of their
   * channels are the same.
   * @param e the other pixel.
   * @return whether the pixels are equal.
   */
  public boolean checkEquality(Pixel e) {
    return this.r == e.r && this.g == e.g && this.b == e.b;
  }

}