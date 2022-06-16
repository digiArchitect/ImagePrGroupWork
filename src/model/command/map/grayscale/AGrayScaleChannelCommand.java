package model.command.map.grayscale;

import model.command.map.AMapCommand;
import model.pixel.PixelImpl;

/**
 * Represents a Grayscale command that bases its mutation on a specific color channel of each pixel.
 */
public abstract class AGrayScaleChannelCommand extends AMapCommand {
  /**
   * Creates a Map command that maps a function that keys all pixels of an image to a specific
   * color channel of each pixel.
   * @param channel the color channel that all pixels will be mutated depending on.
   */
  public AGrayScaleChannelCommand(int channel) {
    super(2);
    this.lambda = (n) -> {
      int newVal;
      StringBuilder p = new StringBuilder();
      int[] values = new int[3];
      for (int x = 0; x < 3; x++) {
        values[x] = n.getChannel(x);
      }
      newVal = values[channel];
      p.append(newVal);
      return new PixelImpl(newVal, newVal, newVal);
    };
  }
}