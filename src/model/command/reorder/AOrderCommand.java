package model.command.reorder;

import java.util.List;
import java.util.function.Function;
import model.command.ACommand;
import model.image.Image;
import model.image.ImageImpl;
import model.pixel.Pixel;

/**
 * Represents a command that is used to reorder the rows or columns of the pixels of an image.
 */
public abstract class AOrderCommand extends ACommand {
  Function<Image, List<List<Pixel>>> lambda;

  /**
   * Constructs a Command.
   */
  public AOrderCommand() {
    super();
  }

  /**
   * Reorders the rows or columns of the given image, and returns
   * a new one based on it.
   * @param image the function argument
   * @return a new image with columns or rows based on the first one.
   */
  public Image apply(Image image) {
    imageVals = image.getImageVals();
    contents = image.getContents();
    return new ImageImpl(lambda.apply(image), contents.get(0), contents.get(1), contents.get(2));
  }
}
