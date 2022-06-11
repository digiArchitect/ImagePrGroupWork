import org.junit.Before;
import org.junit.Test;

import image.Pixel;

import static org.junit.Assert.assertEquals;


/**
 * Class for testing a Pixel class.
 */
public class TestPixel {
  Pixel p;
  Pixel p2;
  Pixel p3;

  @Before
  public void setup() {

    p = new Pixel(123, 245, 183);
    p2 = new Pixel(17, 38, 240);
    p3 = new Pixel(4, 5, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSmallRed() {
    new Pixel(-1, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSmallGreen() {
    new Pixel(5, -1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSmallBlue() {
    new Pixel(5, 5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBigRed() {
    new Pixel(256, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBigGreen() {
    new Pixel(5, 256, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBigBlue() {
    new Pixel(5, 5, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAllSmall() {
    new Pixel(-1, -1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAllBig() {
    new Pixel(256, 256, 256);
  }

  @Test
  public void testGetChannel() {
    assertEquals(6, p3.getChannel(2));
    assertEquals(5, p3.getChannel(1));
    assertEquals(4, p3.getChannel(0));

    assertEquals(240, p2.getChannel(2));
    assertEquals(38, p2.getChannel(1));
    assertEquals(17, p2.getChannel(0));

    assertEquals(183, p.getChannel(2));
    assertEquals(245, p.getChannel(1));
    assertEquals(123, p.getChannel(0));
  }
}