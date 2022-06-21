package model.image;

/**
 * Represents a pixel of an image.
 */
public interface Pixel {
  /**
   * Returns the pixel's specified channel value.
   * @param c the channel.
   * @return the number for the channel.
   * @throws IllegalArgumentException if a channel besides r,g,b is requested.
   */
  int getChannel(int c);

  /**
   * Returns the pixel's rgb.
   * @return the rgb.
   */
  int hashCode();
}
