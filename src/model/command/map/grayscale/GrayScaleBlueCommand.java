package model.command.map.grayscale;


/**
 * Represents a Grayscale command that bases its mutation on the blue channel of each pixel.
 */
public class GrayScaleBlueCommand extends AGrayScaleChannelCommand {
  /**
   * Constructs a Grayscale Channel Command that focuses on the blue channel.
   */
  public GrayScaleBlueCommand() {
    super(2);
  }
}