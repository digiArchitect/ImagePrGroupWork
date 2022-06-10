import java.io.InputStreamReader;

import Controller.ImagePrController;
import Controller.ImagePrControllerImpl;
import Controller.ImagePrControllerImplMock;
import Model.ImagePrModel;
import Model.ImagePrModelImpl;

/**
 * Runs the program, allowing the user to upload PPM images and edit them using the
 * image processor, and then save them.
 */
public class ImagePrMain {
  /**
   * Runs the program.
   * @param args the command line arguments.
   */
  public static void main(String args[]) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    ImagePrModel model = new ImagePrModelImpl();
    ImagePrController controller = new ImagePrControllerImpl(input, output, model);
    controller.startProcessor();
  }
}
