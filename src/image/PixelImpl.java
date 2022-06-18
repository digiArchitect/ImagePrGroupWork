package image;

/**
 Represents a PixelImpl of an ImageImpl.
 */
public class PixelImpl implements Pixel {
  private int rgb;

  /**
   * Constructs a pixel given a red, blue, and green value.
   * @param rgb the 32 bit rgb
   */
  public PixelImpl(int[] rgb) throws IllegalArgumentException {
    for(int i : rgb) {
      if(i < 0 || i > 255) {
        throw new IllegalArgumentException();
      }
    }
    this.rgb = FunctionUtils.properRGB(rgb);
  }

  public PixelImpl(int rgb) {
    this.rgb = rgb;
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
       return  (rgb & 0xff0000) >> 16;
      case 1:
        return (rgb & 0xff00) >> 8;
      case 2:
        return  rgb & 0xff;
      default:
        throw new IllegalArgumentException("Choose a valid color between 0, 1, and 2");
    }
  }

  /**
   * Returns the pixel's rgb
   * @return the rgb.
   */
    public int hashCode() {
      return getChannel(0) * getChannel(1) + getChannel(1) * getChannel(2)
              + getChannel(2) * getChannel(0);
    }

  /**
   * Tests whether a pixel is equal to another pixel by seeing if all of their
   * channels are the same.
   * @param e the other pixel.
   * @return whether the pixels are equal.
   */
  public boolean equals(Pixel e) {
    return this.hashCode() == e.hashCode();
  }
}