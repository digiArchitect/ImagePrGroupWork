package model.command.map.grayscale;

/**
 * Represents a Grayscale command that bases its mutation on the red channel of each pixel.
 */
public class GrayScaleRedCommand extends AGrayScaleChannelCommand {
  /**
   * Constructs a Grayscale Channel Command that focuses on the red channel.
   */
  public GrayScaleRedCommand(int channel) {
    super(0);
  }
}