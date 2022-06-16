package model.command.map;

import java.util.ArrayList;
import java.util.List;
import model.command.ACommand;
import model.command.PixelFunc;
import model.image.Image;
import model.image.ImageImpl;
import model.pixel.Pixel;

/**
 * Represents a command that processes an image by
 * mapping a mutating function over its pixels.
 */
public abstract class AMapCommand extends ACommand {

  protected PixelFunc lambda;

  /**
   * Constructs a Command with Map Command characteristics.
   */
  public AMapCommand(int params) {
    super(params);
  }

  /**
   * Maps this command's pixel mutation function over all the given image's pixels
   * and then returns the new image created as a result.
   * @param image the given image.
   * @return a new image made of the mutated pixels.
   */
  @Override
  public Image apply(Image image) {
    return mapImage(image);
  }

  /**
   * Iterates over all the pixels in an image and applies a mutating function to each of them.
   * @param i the image.
   * @return a new image made of mutated pixels of the original image.
   */
  public Image mapImage(Image i) {
    contents = i.getContents();
    newImageVals = new ArrayList<>();
    for(int row = 0; row < contents.get(1); row++) {
      List<Pixel> currentRow = new ArrayList<>();
      for(int col = 0; col < contents.get(0); col++) {
        currentRow.add(lambda.apply(i.getPixel(row, col)));
      }
      newImageVals.add(currentRow);
    }
    return new ImageImpl(newImageVals, contents.get(0),
            contents.get(1), contents.get(2));
  }
}