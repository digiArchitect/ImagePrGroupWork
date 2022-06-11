import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import image.Image;
import image.Pixel;
import model.ImagePrModelImpl;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tester class for image Model.
 */
public class TestImageModel {
  ImagePrModelImpl impOne = new ImagePrModelImpl();
  ImagePrModelImpl impTwo = new ImagePrModelImpl();

  Image m;
  Image a;
  Image r;
  Image i;
  Image o;


  List<List<Pixel>> lol;
  List<List<Pixel>> peter;
  List<List<Pixel>> griffin;
  List<List<Pixel>> family;
  List<List<Pixel>> guy;


  Pixel one;
  Pixel two;
  Pixel three;
  Pixel four;
  Pixel five;
  Pixel six;
  Pixel seven;
  Pixel eight;
  List<Pixel> allBlack;
  List<Pixel> allWhite;

  @Before
  public void setUp() {

    one = new Pixel(111, 111, 111);
    two = new Pixel(222, 222, 222);
    three = new Pixel(0, 0, 0);
    four = new Pixel(50, 25, 12);
    five = new Pixel(255, 255, 255);
    six = new Pixel(202, 101, 10);
    seven = new Pixel(18, 50, 240);
    eight = new Pixel(1, 2, 3);
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
    m = new Image(lol, 2, 2, 255);
    a = new Image(peter, 2, 1, 255);
    r = new Image(griffin, 1, 1, 100);
    i = new Image(family, 2, 4, 0);
    o = new Image(guy, 4, 2, 30);
  }

  @Test
  public void testLoad() {
    assertFalse(impOne.hasKey("res"));
    assertFalse(impOne.hasKey("rizz"));
    impOne.load("res/lol.ppm", "res");
    impTwo.load("res/lmao.ppm", "rizz");
    assertTrue(impOne.hasKey("res"));
    assertFalse(impOne.hasKey("rizz"));

    assertTrue(arrayEquals(impOne.getHashMap().get("res").flatten(), m.flatten()));
    assertTrue(arrayEquals(impTwo.getHashMap().get("rizz").flatten(), r.flatten()));
    try {
      impOne.load("", "lol");
      fail("this should never work silly");
    } catch (IllegalArgumentException e) {
      //There are no values here at all.
    }
  }

  private boolean arrayEquals(List<Pixel> one, List<Pixel> two) {
    boolean b = true;
    for (int x = 0; x < one.size(); x++) {
      b &= one.get(x).checkEquality(two.get(x));
    }
    return b;
  }

  @Test
  public void testFlipImage() {
    impOne.load("res/lol.ppm", "res");
    impTwo.load("res/lmao.ppm", "rizz");
    impOne.flipImage("horizontal", "res", "newRes");

    assertTrue(arrayEquals(impOne.getHashMap().get("newRes").flatten(),
            (Arrays.asList(two, one, four, three))));
    impOne.flipImage("vertical", "res", "newVert");
    assertTrue(arrayEquals(impOne.getHashMap().get("newVert").flatten(),
            (Arrays.asList(one, two))));
  }

  @Test
  public void testBrighten() {
    impOne.load("res/lol.ppm", "res");
    impTwo.load("res/lmao.ppm", "rizz");
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
            List.of(new Pixel(166, 166, 166))));
  }

  @Test
  public void testGreyscale() {
    impOne.load("res/lol.ppm", "res");
    impTwo.load("res/lmao.ppm", "rizz");
    impOne.greyscale("luma", "res", "luma");
    //checking overwrite here too lolz

    assertTrue(impOne.hasKey("luma"));
    assertTrue(arrayEquals(impOne.getHashMap().get("luma").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, five))));
    impOne.greyscale("red", "res", "red");
    assertTrue(impOne.hasKey("red"));
    assertTrue(arrayEquals(impOne.getHashMap().get("red").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new Pixel(50, 50, 50)))));
    impOne.greyscale("green", "res", "green");
    assertTrue(impOne.hasKey("green"));
    assertTrue(arrayEquals(impOne.getHashMap().get("green").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new Pixel(25, 25, 25)))));
    impOne.greyscale("blue", "res", "blue");
    assertTrue(impOne.hasKey("blue"));
    assertTrue(arrayEquals(impOne.getHashMap().get("blue").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new Pixel(12, 12, 12)))));
    impOne.greyscale("intensity", "res", "intensity");
    assertTrue(impOne.hasKey("intensity"));
    assertTrue(arrayEquals(impOne.getHashMap().get("intensity").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new Pixel(29, 29, 29)))));
    assertFalse(impOne.hasKey("value"));
    impOne.greyscale("value", "res", "value");
    assertTrue(impOne.hasKey("value"));
    assertTrue(arrayEquals(impOne.getHashMap().get("intensity").flatten(),
            new ArrayList<>(Arrays.asList(one, two, three, new Pixel(50, 50, 50)))));

  }

  @Test
  public void testHasKey() {
    assertFalse(impOne.hasKey("res"));
    impOne.load("res/lol.ppm", "res");
    assertTrue(impOne.hasKey("res"));
    assertFalse(impTwo.hasKey("rizz"));
    impTwo.load("res/lmao.ppm", "rizz");
    assertTrue(impTwo.hasKey("rizz"));
  }
}