package model.command.reorder;

import java.util.Collections;
import java.util.List;
import model.pixel.Pixel;

/**
 * Represents a Vertical Flip Command, which flips all
 * the pixels in an image across the x-axis.
 */
public class VerticalFlipCommand extends AOrderCommand {

  /**
   * Constructs an Order command with a vertical flip lambda.
   */
  public VerticalFlipCommand() {
    this.lambda = (n) -> {
      List<List<Pixel>> newVals;
      newVals = imageVals;
      Collections.reverse(newVals);
      return newVals;
    };
  }
}