package model.command.reorder;

import java.util.stream.Collectors;

/**
 * Represents a Horizontal Flip Command, which flips all
 * the pixels in an image across the y-axis.
 */
public class HorizontalFlipCommand extends AOrderCommand {

  /**
   * Constructs an Order command with a horizontal flip lambda.
   */
  public HorizontalFlipCommand() {
    this.lambda = (n) -> {
      return imageVals.stream().map(new ReverseAll()).collect(Collectors.toList());
    };
  }
}
