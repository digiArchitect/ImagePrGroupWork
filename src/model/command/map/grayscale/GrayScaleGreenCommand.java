package model.command.map.grayscale;

/**
 * Represents a Grayscale command that bases its mutation on the green channel of each pixel.
 */
public class GrayScaleGreenCommand extends AGrayScaleChannelCommand {
  /**
   * Constructs a Grayscale Channel Command that focuses on the green channel.
   */
  public GrayScaleGreenCommand(int channel) {
    super(1);
  }
}