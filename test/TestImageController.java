import java.io.StringReader;

import controller.ImagePrController;
import controller.ImagePrControllerImpl;
import model.ImagePrModel;
import model.ImagePrModelImpl;
import view.ImagePrView;
import view.ImagePrViewImpl;

/**
 * Tests the processor's controller
 */
public class TestImageController {
  Readable input;
  Appendable out;
  ImagePrModel model;
  ImagePrView view;
  ImagePrController controller;
  String[] lines;

  public void setup() {
    out = new StringBuilder();
    model = new ImagePrModelImpl();
    view = new ImagePrViewImpl();
  }

  public void testConstructor() {
    input = new StringReader("q");
    controller = new ImagePrControllerImpl(model, view);
    controller.playGame();
    assertTrue(out != null);
  }

  public void testConstructorFails() {

  }

  public void testQuitRandomNums() {

  }

  public void testInvalidMock() {

  }

  public void testValidMock() {

  }

  public void testResults() {

  }
}
