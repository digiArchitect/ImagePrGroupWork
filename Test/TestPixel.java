import org.junit.Before;
import org.junit.Test;

import Image.Pixel;

import static org.junit.Assert.assertEquals;


public class TestPixel {
  Pixel p;
  Pixel p2;
  Pixel p3;

  @Before
  public void setup() {
    p = new Pixel(123456789);
    p2 = new Pixel(790050001);
    p3 = new Pixel(791050001);
  }
  @Test
  public void testGetChannel() {
    assertEquals(791, p3.getChannel(2));
    assertEquals(50, p3.getChannel(1));
    assertEquals(1, p3.getChannel(0));

    assertEquals(790, p2.getChannel(2));
    assertEquals(50, p2.getChannel(1));
    assertEquals(1, p2.getChannel(0));

    assertEquals(123, p.getChannel(2));
    assertEquals(456, p.getChannel(1));
    assertEquals(789, p.getChannel(0));
  }
}
