package Model;

import java.util.HashMap;

import Image.ImagePPM;
import Image.greyScale;
import Image.mutateAll;
import Model.ImagePrModel;

public class ImagePrModelImpl implements ImagePrModel {

  HashMap<String, ImagePPM> images;

  /**
   * Constructs an image processing model using a 2d array of pixels.
   */
  public ImagePrModelImpl() {
    this.images = new HashMap<String, ImagePPM>();
  }

  public void load(String s, ImagePPM i) {
    images.put(s, i);
  }


  @Override
  public void flipImage(String direction) {
    if (direction.equals("horizontal")) {
      images.get(0).horizontal();

    } else if (direction.equals("vertical")) {
      images.get(0).vertical();

    } else {
      //Catch and throw an illegal state in the controller.
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void brighten(int constant) {
    images.get(0).applyChanges(new mutateAll(constant));
  }

  @Override
  public void greyscale(String component) {
    images.get(0).applyChanges(new greyScale(component));
  }

  @Override
  public void save(String fileLocation) {

  }

}
