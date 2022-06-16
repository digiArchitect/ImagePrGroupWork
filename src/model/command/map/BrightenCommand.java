package model.command.map;

import model.pixel.PixelImpl;

/**
 * Represents a Brighten Command, which increases or decreases every pixel in an image by
 * a constant integer amount, determined by the fields entered in by the model.
 */
public class BrightenCommand extends AMapCommand {

  /**
   * Constructs a Map Command with a Brighten lambda.
   */
  public BrightenCommand() {
    super();
    this.lambda = (n) -> {
      int[] values = new int[3];
      for (int x = 0; x < 3; x++) {
        int newRgb = n.getChannel(x) + Integer.parseInt(fields.get(0));
        if (newRgb > 255) {
          newRgb = 255;
        } else if (newRgb < 0) {
          newRgb = 0;
        }
        values[x] = newRgb;
      }
      return new PixelImpl(values[0], values[1], values[2]);
    };
  }
}
