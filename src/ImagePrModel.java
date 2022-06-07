import java.io.File;

public class ImagePrModel implements ImageModel {

  Pixel[][] imageVals;
  String name;
  File current;

  /**
   * Constructs an image processing model using a 2d array of pixels.
   * @param imageVals 2d array of pixels which store access to r g b.
   * @throws
   */
  public ImagePrModel(Pixel[][] imageVals) {
    this.imageVals = imageVals;
  }


  @Override
  public void flipImage(String direction) {

  }

  @Override
  public void brighten(int constant) {

  }

  @Override
  public void greyscale(String component) {

  }

  @Override
  public void save(String fileLocation) {

  }
  public void create(String fileLocation) {
    current = new File(fileLocation + name);
  }

}
