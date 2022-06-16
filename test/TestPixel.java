import org.junit.Before;
import org.junit.Test;

import model.pixel.PixelImpl;

import static org.junit.Assert.assertEquals;


/**
 * Class for testing a PixelImpl class.
 */
public class TestPixel {
  PixelImpl p;
  PixelImpl p2;
  PixelImpl p3;

  @Before
  public void setup() {

    p = new PixelImpl(123, 245, 183);
    p2 = new PixelImpl(17, 38, 240);
    p3 = new PixelImpl(4, 5, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSmallRed() {
    new PixelImpl(-1, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSmallGreen() {
    new PixelImpl(5, -1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSmallBlue() {
    new PixelImpl(5, 5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBigRed() {
    new PixelImpl(256, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBigGreen() {
    new PixelImpl(5, 256, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBigBlue() {
    new PixelImpl(5, 5, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAllSmall() {
    new PixelImpl(-1, -1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAllBig() {
    new PixelImpl(256, 256, 256);
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