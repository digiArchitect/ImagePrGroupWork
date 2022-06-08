import java.io.InputStreamReader;

import Controller.ImagePrController;
import Controller.ImagePrControllerImpl;
import Model.ImageModel;

/**
 * Runs the program baby!!!
 */
public class ImagePrMain {
  public static void main(String args[]) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    ImageModel model = new ImagePrModel();
    ImagePrController controller = new ImagePrControllerImpl(input, output, model);
    controller.startProcessor();
  }
}
