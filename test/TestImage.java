import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import image.FunctionUtils;
import image.ImageImpl;
import image.Pixel;
import image.PixelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the image class.
 */
public class TestImage {
  ImageImpl s;
  ImageImpl u;
  ImageImpl p;
  ImageImpl e;
  ImageImpl r;


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
  FunctionUtils fu;

  /**
   * The variables used for theses tests.
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
            new ArrayList<>(Arrays.asList(seven, eight))
    ));
    guy = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one, two, three, four))),
            new ArrayList<>(Arrays.asList(five, six, seven, eight))));
    s = new ImageImpl(lol, 2, 2, 255);
    u = new ImageImpl(peter, 2, 1, 255);
    p = new ImageImpl(griffin, 1, 1, 100);
    e = new ImageImpl(family, 2, 4, 0);
    r = new ImageImpl(guy, 4, 2, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullImageVals() {
    new ImageImpl(null, 1, 1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void smallWidth() {
    new ImageImpl(lol, 0, 1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void smallHeight() {
    new ImageImpl(lol, 1, 0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void smallMaxValue() {
    new ImageImpl(lol, 1, 1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void allBadParens() {
    new ImageImpl(null, 0, 0, -1);
  }


  @Test
  public void testFlatten() {
    assertEquals(s.flatten(), Arrays.asList(one, two, three, four));
    assertEquals(u.flatten(), Arrays.asList(one, two));
    assertEquals(p.flatten(), List.of(one));
    assertEquals(e.flatten(), Arrays.asList(one, two, three, four, five, six, seven, eight));
    assertEquals(r.flatten(), Arrays.asList(one, two, three, four, five, six, seven, eight));
  }

  @Test
  public void testGetContents() {
    assertEquals(s.getContents(), Arrays.asList(2, 2, 255));
    assertEquals(u.getContents(), Arrays.asList(2, 1, 255));
    assertEquals(p.getContents(), Arrays.asList(1, 1, 100));
    assertEquals(e.getContents(), Arrays.asList(2, 4, 0));
    assertEquals(r.getContents(), Arrays.asList(4, 2, 30));

  }

  @Test
  public void testGetImageVals() {
    assertEquals(s.getImageVals(), lol);
    assertEquals(u.getImageVals(), peter);
    assertEquals(p.getImageVals(), griffin);
    assertEquals(e.getImageVals(), family);
    assertEquals(r.getImageVals(), guy);

  }



}
