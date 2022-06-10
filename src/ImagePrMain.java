import java.io.InputStreamReader;

import Controller.ImagePrController;
import Controller.ImagePrControllerImpl;
import Controller.ImagePrControllerImplMock;
import Model.ImagePrModel;
import Model.ImagePrModelImpl;

/**
 * Runs the program baby!!!
 */
public class ImagePrMain {
  public static void main(String args[]) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    ImagePrModel model = new ImagePrModelImpl();
    ImagePrController controller = new ImagePrControllerImplMock(input, output, model);
    controller.startProcessor();
  }
}
