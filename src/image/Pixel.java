package image;

public interface Pixel {
  /**
   * Returns the pixel's specified channel value.
   * @param c the channel.
   * @return the number for the channel.
   * @throws IllegalArgumentException if a channel besides r,g,b is requested.
   */
  int getChannel(int c);

  /**
   * Returns the pixel's rgb
   * @return the rgb.
   */
  int hashCode();

  /**
   * Tests whether a pixel is equal to another pixel by seeing if all of their
   * channels are the same.
   * @param e the other pixel.
   * @return whether the pixels are equal.
   */
  boolean equals(Pixel e);
}
