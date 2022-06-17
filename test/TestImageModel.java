import org.junit.Before;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.ImagePrController;
import controller.ImagePrControllerImpl;
import image.FunctionUtils;
import image.ImageImpl;
import image.PixelImpl;
import model.ImagePrModel;
import model.ImagePrModelImpl;
import view.ImagePrView;
import view.ImagePrViewImpl;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tester class for image Model.
 */
public class TestImageModel {
  ImagePrModel impOne = new ImagePrModelImpl();
  ImagePrModel impTwo = new ImagePrModelImpl();
  ImagePrView viewImp = new ImagePrViewImpl();
  ImagePrController controlImpOne = new ImagePrControllerImpl(new StringReader("q"), viewImp,
          impOne);
  ImagePrController controlImpTwo = new ImagePrControllerImpl(new StringReader("q"), viewImp,
          impTwo);
  ImageImpl m;
  ImageImpl a;
  ImageImpl r;
  ImageImpl i;
  ImageImpl o;


  List<List<PixelImpl>> lol;
  List<List<PixelImpl>> peter;
  List<List<PixelImpl>> griffin;
  List<List<PixelImpl>> family;
  List<List<PixelImpl>> guy;


  PixelImpl one;
  PixelImpl two;
  PixelImpl three;
  PixelImpl four;
  PixelImpl five;
  PixelImpl six;
  PixelImpl seven;
  PixelImpl eight;
  List<PixelImpl> allBlack;
  List<PixelImpl> allWhite;
  FunctionUtils fu;


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
    five = new PixelImpl(new int[]{255, 255, 255});
    six = new PixelImpl(new int[]{202, 101, 10});
    seven = new PixelImpl(new int[]{18, 50, 240});
    eight = new PixelImpl(new int[]{1, 2, 3});
    lol = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one, two))),
            new ArrayList<>(Arrays.asList(three, four))));
    peter = new ArrayList<>(List.of((new ArrayList<>(Arrays.asList(one, two)))));
    griffin = new ArrayList<>(List.of((new ArrayList<>(List.of(one)))));
    family = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one, two))),
            new ArrayList<>(Arrays.asList(three, four)),
            new ArrayList<>(Arrays.asList(five, six)),
            new ArrayList<>(Arrays.asList(seven, eight))));
    guy = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one, two, three, four))),
            new ArrayList<>(Arrays.asList(five, six, seven, eight))));
    allBlack = new ArrayList<>(Arrays.asList(three, three, three, three));
    allWhite = new ArrayList<>(Arrays.asList(five, five, five, five));
    m = new ImageImpl(lol, 2, 2, 255);
    a = new ImageImpl(peter, 2, 1, 255);
    r = new ImageImpl(griffin, 1, 1, 100);
    i = new ImageImpl(family, 2, 4, 0);
    o = new ImageImpl(guy, 4, 2, 30);
  }

  /**
   * Tests loading an image through the controller into the model.
   *
   * @throws IOException if the file cannot be read.
   */
  @Test
  public void testLoad() throws IOException {
    assertFalse(impOne.hasKey("res"));
    assertFalse(impOne.hasKey("rizz"));
    controlImpOne.load("res/lol.ppm", "res");
    controlImpTwo.load("res/lmao.ppm", "rizz");
    assertTrue(impOne.hasKey("res"));
    assertFalse(impOne.hasKey("rizz"));

    assertTrue(arrayEquals(impOne.getHashMap().get("res").flatten(), m.flatten()));
    assertTrue(arrayEquals(impTwo.getHashMap().get("rizz").flatten(), r.flatten()));
    try {
      controlImpOne.load("", "lol");
      fail("this should never work silly");
    } catch (ArrayIndexOutOfBoundsException e) {
      //There are no values here at all.
    }
  }

  /**
   * Returns if two arrays of pixels equal to one another.
   *
   * @param one the first array.
   * @param two the second array.
   * @return whether they are equal.
   */
  private boolean arrayEquals(List<PixelImpl> one, List<PixelImpl> two) {
    boolean b = true;
    for (int x = 0; x < one.size(); x++) {
      b &= one.get(x).equals(two.get(x));
    }
    return b;
  }

  /**
   * Tests the flipImage method.
   *
   * @throws IOException if the file cannot be read.
   */
  @Test
  public void testFlipImage() throws IOException {
    controlImpOne.load("res/lol.ppm", "res");
    controlImpTwo.load("res/lmao.ppm", "rizz");
    impOne.flipImage("horizontal", "res", "newRes");

    assertTrue(arrayEquals(impOne.getHashMap().get("newRes").flatten(),
            (Arrays.asList(two, one, four, three))));
    impOne.flipImage("vertical", "res", "newVert");
    assertTrue(arrayEquals(impOne.getHashMap().get("newVert").flatten(),
            (Arrays.asList(one, two))));
  }

  /**
   * Tests the brighten method.
   *
   * @throws IOException if the file cannot be read.
   */
  @Test
  public void testBrighten() throws IOException {
    controlImpOne.load("res/lol.ppm", "res");
    controlImpTwo.load("res/lmao.ppm", "rizz");
    impOne.brighten(255, "res", "newRes");
    assertTrue(impOne.hasKey("newRes"));
    assertTrue(arrayEquals(impOne.getHashMap().get("newRes").flatten(), allWhite));
    assertFalse(impOne.hasKey("blackOut"));
    impOne.brighten(-255, "res", "blackOut");
    assertTrue(impOne.hasKey("blackOut"));
    assertTrue(arrayEquals(impOne.getHashMap().get("blackOut").flatten(), allBlack));
    impTwo.brighten(55, "rizz", "hey");
    assertTrue(impTwo.hasKey("hey"));
    assertTrue(arrayEquals(impTwo.getHashMap().get("hey").flatten(),
            List.of(new PixelImpl(new int[]{166, 166, 166}))));
  }

  /**
   * Tests the greyScale method.
   *
   * @throws IOException if the file cannot be read.
   */
  @Test
  public void testGreyscale() throws IOException {
    controlImpOne.load("res/lol.ppm", "res");
    controlImpTwo.load("res/lmao.ppm", "rizz");
    impOne.greyscale("luma", "res", "luma");
    //checking overwrite here too lolz

    assertTrue(impOne.hasKey("luma"));
    assertTrue(arrayEquals(impOne.getHashMap().get("luma").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, five))));
    impOne.greyscale("red", "res", "red");
    assertTrue(impOne.hasKey("red"));
    assertTrue(arrayEquals(impOne.getHashMap().get("red").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new PixelImpl(new int[]{50, 50, 50})))));
    impOne.greyscale("green", "res", "green");
    assertTrue(impOne.hasKey("green"));
    assertTrue(arrayEquals(impOne.getHashMap().get("green").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new PixelImpl(new int[]{25, 25, 25})))));
    impOne.greyscale("blue", "res", "blue");
    assertTrue(impOne.hasKey("blue"));
    assertTrue(arrayEquals(impOne.getHashMap().get("blue").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new PixelImpl(new int[]{12, 12, 12})))));
    impOne.greyscale("intensity", "res", "intensity");
    assertTrue(impOne.hasKey("intensity"));
    assertTrue(arrayEquals(impOne.getHashMap().get("intensity").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new PixelImpl(new int[]{29, 29, 29})))));
    assertFalse(impOne.hasKey("value"));
    impOne.greyscale("value", "res", "value");
    assertTrue(impOne.hasKey("value"));
    assertTrue(arrayEquals(impOne.getHashMap().get("intensity").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new PixelImpl(new int[]{50, 50, 50})))));
  }

  /**
   * Tests the hasKey method.
   *
   * @throws IOException if a file cannot be read.
   */
  @Test
  public void testHasKey() throws IOException {
    assertFalse(impOne.hasKey("res"));
    controlImpOne.load("res/lol.ppm", "res");
    assertTrue(impOne.hasKey("res"));
    assertFalse(impTwo.hasKey("rizz"));
    controlImpTwo.load("res/lmao.ppm", "rizz");
    assertTrue(impTwo.hasKey("rizz"));
  }
}