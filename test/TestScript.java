import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import controller.prcontroller.ImagePrController;
import controller.prcontroller.ImagePrControllerImpl;
import model.ImagePrModel;
import model.ImagePrModelImplMock;
import view.prview.ImagePrView;
import view.prview.ImagePrViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests reading from a script.
 */
public class TestScript {
  StringBuilder log;

  /**
   * Tests reading from a script.
   * @throws IOException if reading from the file goes wrong.
   */
  @Test
  public void TestScriptWithMock() throws IOException {
    String[] args = {"file", "script.txt"};
    Path filePath = Path.of(args[1]);
    String content = Files.readString(filePath);
    Readable input = new StringReader(content);
    log = new StringBuilder();
    ImagePrView view = new ImagePrViewImpl();
    ImagePrModel model = new ImagePrModelImplMock(log);
    ImagePrController controller = new ImagePrControllerImpl(input, view, model);
    controller.startProcessor();
    assertEquals("greyscale\n" +
            "greyscale\n" +
            "greyscale\n" +
            "greyscale\n" +
            "greyscale\n" +
            "greyscale\n" +
            "flipImage\n" +
            "brighten\n" +
            "brighten\n" +
            "colorTransform\n" +
            "colorTransform\n" +
            "kernelMutate\n" +
            "kernelMutate\n", log.toString());
  }
}
