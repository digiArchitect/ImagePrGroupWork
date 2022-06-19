import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import controller.ImagePrController;
import controller.ImagePrControllerImpl;
import model.image.FunctionUtils;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import model.ImagePrModel;
import model.ImagePrModelImpl;
import view.ImagePrView;
import view.ImagePrViewImpl;

import static org.junit.Assert.fail;


/**
 * Tester class for image Model.
 */
public class TestView {
  ImagePrModel impOne = new ImagePrModelImpl();
  ImagePrModel impTwo = new ImagePrModelImpl();
  ImagePrView viewImp = new ImagePrViewImpl();
  ImagePrController controlImpOne = new ImagePrControllerImpl(new StringReader("q"), viewImp,
          impOne);
  Image m;


  List<List<Pixel>> lol;


  Pixel one;
  Pixel two;
  Pixel three;
  Pixel four;

  FunctionUtils fu;
  HashMap<String, Image> s = new HashMap<>();


  /**
   * Variables to be loaded before each test.
   */
  @Before
  public void setUp() {
    fu = new FunctionUtils();
    one = new PixelImpl(new int[]{111, 111, 111});
    two = new PixelImpl(new int[]{222, 222, 222});
    three = new PixelImpl(new int[]{0, 0, 0});
    four = new PixelImpl(new int[]{50, 25, 12});
    lol = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one, two))),
            new ArrayList<>(Arrays.asList(three, four))));
    m = new ImageImpl(lol, 2, 2, 255);

    s.put("lol", m);

  }

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Test
  public void savePPM() throws Exception {
    try {
      controlImpOne.load("lolol.ppm", "lol");
      fail("This shouldn't be possible LMAO");
    } catch (StringIndexOutOfBoundsException e) {
      //awesome
    }
    viewImp.save("lolol.ppm", "lol", Arrays.asList(2, 2, 255), s);
    controlImpOne.load("lolol.ppm", "lol");

  }

  @Test
  public void saveSupported() throws IOException {
    try {
      controlImpOne.load("lolol.png", "lol");
      fail("This shouldn't be possible LMAO");
    } catch (IllegalArgumentException e) {
      //awesome
    }
    viewImp.save("lolol.png", "lol", Arrays.asList(2, 2, 255), s);
    controlImpOne.load("lolol.png", "lol");
    try {
      controlImpOne.load("wow.jpg", "jeez");
      fail("This shouldn't be possible LMAO");
    } catch (IllegalArgumentException e) {
      //awesome
    }
    viewImp.save("wow.jpg", "jeez", Arrays.asList(2, 2, 255), s);
    controlImpOne.load("wow.jpg", "jeez");
    try {
      controlImpOne.load("ol.bmp", "lol");
      fail("This shouldn't be possible LMAO");
    } catch (IllegalArgumentException e) {
      //awesome
    }
    viewImp.save("ol.bmp", "ol", Arrays.asList(2, 2, 255), s);
    controlImpOne.load("ol.bmp", "ol");

  }
}