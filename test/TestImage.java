import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import image.ImageImpl;
import pixel.PixelImpl;

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

  @Before
  public void setUp() {

    one = new PixelImpl(111, 111, 111);
    two = new PixelImpl(222, 222, 222);
    three = new PixelImpl(0, 0, 0);
    four = new PixelImpl(50, 25, 12);
    five = new PixelImpl(255, 255, 255);
    six = new PixelImpl(202, 101, 10);
    seven = new PixelImpl(18, 50, 240);
    eight = new PixelImpl(1, 2, 3);
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
