import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImagePrController;
import controller.ImagePrControllerImpl;
import model.ImagePrModel;
import model.ImagePrModelImpl;
import view.ImagePrView;
import view.ImagePrViewImpl;

/**
 * Runs the program, allowing the user to upload PPM images and edit them using the
 * image processor, and then save them.
 */
public class ImagePrMain {
  /**
   * Runs the program.
   * @param args the command line arguments.
   */
  public static void main(String[] args) {
    System.out.println(args);
    Readable input;
    if (args.length > 0) {
      StringBuilder sb = new StringBuilder();
      for (String s : args) {
        sb.append(s + " ");
      }
      sb.append("q");
      System.out.println(sb);
      input = new StringReader(sb.toString());
    } else {
      input = new InputStreamReader(System.in);
    }
    ImagePrView view = new ImagePrViewImpl();
    ImagePrModel model = new ImagePrModelImpl();
    ImagePrController controller = new ImagePrControllerImpl(input, view, model);
    controller.startProcessor();
  }
}