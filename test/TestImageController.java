import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.ImagePrController;
import controller.ImagePrControllerImpl;
import model.ImagePrModel;
import model.ImagePrModelImpl;
import view.ImagePrView;
import view.ImagePrViewImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the processor's controller.
 */
public class TestImageController {
  Readable input;
  Appendable out;
  ImagePrModel model;
  ImagePrView view;
  ImagePrController controller;

  /**
   * Sets up variables to be used for the tests.
   */
  @Before
  public void setup() {
    out = new StringBuilder();
    model = new ImagePrModelImpl();
    view = new ImagePrViewImpl();
  }

  /**
   * Tests that the controller constructor works.
   */
  @Test
  public void testConstructor() {
    input = new StringReader("q");
    controller = new ImagePrControllerImpl(input, view, model);
    controller.startProcessor();
    assertTrue(out != null);
  }

  /**
   * Tests with a Mock.
   */
  @Test
  public void testValidMock() {
    input = new StringReader("g g g g g g q q hello load b b 23r2dsjkafhdsk q");
    StringBuilder log = new StringBuilder();
    controller = new ImagePrControllerImplMock(input, log);
    assertTrue(log.length() == 0);
  }

  /**
   * Tests that the controller correctly takes in all kinds of user input without
   * needing to throw exceptions.
   */
  @Test
  public void testResults() {
    try {
      input = new StringReader("load res/mangoes.ppm foo red-component foo redfoo " +
              "blue-component foo bluefoo green-component foo greenfoo value-component " +
              "foo valuefoo luma-component foo lumafoo intensity-component foo intensityfoo " +
              "horizontal-flip foo horizontalfoo vertical-flip foo verticalfoo brighten 50 " +
              "foo brightfoo brighten -50 foo darkfoo save res/mangoes-red.png redfoo save " +
              "res/mangoes-blue.png bluefoo save res/mangoes-green.png greenfoo save " +
              "res/mangoes-value.png valuefoo save res/mangoes-luma.png lumafoo save " +
              "res/mangoes-intenstiy.png intensityfoo save res/mangoes-horizontal-flip.png " +
              "horizontalfoo save res/mangoes-vertical-flip.png verticalfoo save " +
              "res/mangoes-brighter.png brightfoo save res/mangoes-darker.png");
      controller = new ImagePrControllerImpl(input, view, model);
      controller.startProcessor();
      fail("no exception thrown!");
    }
    catch (Exception e) {
      // all works
    }
  }
}
