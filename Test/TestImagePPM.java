import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Image.Pixel;
import Image.ImagePPM;

import static org.junit.Assert.assertEquals;
public class TestImagePPM {
 ImagePPM s;
 Pixel one;
 Pixel two;
 Pixel three;
 Pixel four;
 List<List<Pixel>> lol;
 @Before
 public void setUp() {
   one = new Pixel(111111111);
   two = new Pixel(222222222);
   three = new Pixel(333333333);
   four = new Pixel(444444444);
   lol = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one,two))), 
           new ArrayList<>(Arrays.asList(three,four))));

   s = new ImagePPM(lol,"testttt",2,1,3);
 }

  /*
 @Test public void testBrighten() {
   s.brighten(5);
   Pixel p = s.imageVals.get(0).get(0);
   System.out.println(p.toString());
 }
 */


}
