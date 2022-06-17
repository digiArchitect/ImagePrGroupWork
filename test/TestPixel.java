import org.junit.Before;
import org.junit.Test;

import image.FunctionUtils;
import image.PixelImpl;

import static org.junit.Assert.assertEquals;


/**
 * Class for testing a PixelImpl class.
 */
public class TestPixel {
  PixelImpl p;
  PixelImpl p2;
  PixelImpl p3;
  FunctionUtils fu;

  /**
   * Sets up variables to be used for the tests.
   */
  @Before
  public void setup() {
    p = new PixelImpl(new int[]{123, 245, 183});
    p2 = new PixelImpl(new int[]{17, 38, 240});
    p3 = new PixelImpl(new int[]{4, 5, 6});
  }

  /**
   * Tests constructing a pixel with a red value too small.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSmallRed() {
    new PixelImpl(new int[]{-1, 5, 5});
  }

  /**
   * Tests constructing a pixel with a green value too small.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSmallGreen() {
    new PixelImpl(new int[]{5, -1, 5});
  }

  /**
   * Tests constructing a pixel with a blue value too small.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSmallBlue() {
    new PixelImpl(new int[]{5, 5, -1});
  }

  /**
   * Tests constructing a pixel with a red value too high.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBigRed() {
    new PixelImpl(new int[]{256, 5, 5});
  }

  /**
   * Tests constructing a pixel with a green value too high.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBigGreen() {
    new PixelImpl(new int[]{5, 256, 5});
  }

  /**
   * Tests constructing a pixel with a blue value too high.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBigBlue() {
    new PixelImpl(new int[]{5, 5, 256});
  }

  /**
   * Tests constructing a pixel with rgb values too low.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAllSmall() {
    new PixelImpl(new int[]{-1, -1, -1});
  }

  /**
   * Tests constructing a pixel with rgb values too high.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAllBig() {
    new PixelImpl(new int[]{256, 256, 256});
  }

  /**
   * Tests the getChannel method on pixels.
   */
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