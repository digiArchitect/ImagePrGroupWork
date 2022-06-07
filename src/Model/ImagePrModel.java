package Model;

import java.io.File;

import Image.ImagePPM;
import Model.ImageModel;

public class ImagePrModel implements ImageModel {

  ImagePPM image;

  /**
   * Constructs an image processing model using a 2d array of pixels.
   * @param image 2d array of pixels which store access to r g b.
   * @throws
   */
  public ImagePrModel(ImagePPM image) {
    this.image = image;
  }


  @Override
  public void flipImage(String direction) {
    if (direction.equals("horizontal")) {
      image.horizontal();

    } else if (direction.equals("vertical")) {
      image.vertical();

    } else {
      //Catch and throw an illegal state in the controller.
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void brighten(int constant) {
    image.brighten(constant);
  }

  @Override
  public void greyscale(String component) {

  }

  @Override
  public void save(String fileLocation) {

  }

}
