import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Image.ImagePPM;
import Image.Pixel;
import Model.ImagePrModelImpl;

import static org.junit.Assert.assertTrue;


public class TestImageModel {
  ImagePrModelImpl impOne = new ImagePrModelImpl();
  ImagePrModelImpl impTwo = new ImagePrModelImpl();
  ImagePrModelImpl impThree = new ImagePrModelImpl();

  ImagePPM m;
  ImagePPM a;
  ImagePPM r;
  ImagePPM i;
  ImagePPM o;


  List<List<Pixel>> lol;
  List<List<Pixel>> peter;
  List<List<Pixel>> griffin;
  List<List<Pixel>> family;
  List<List<Pixel>> guy;
    /*
  if you do @Rule over a new TemporaryFolder() field
then you can do folder.getRoot().getPath() to get a path to a temporary directory
and that directory will be deleted once the program ends
   */

  Pixel one;
  Pixel two;
  Pixel three;
  Pixel four;
  Pixel five;
  Pixel six;
  Pixel seven;
  Pixel eight;
  @Before
  public void setUp() {

    one = new Pixel(111,111,111);
    two = new Pixel(222,222,222);
    three = new Pixel(0,0,0);
    four = new Pixel(50,25,12);
    five =  new Pixel(255,255,255);
    six = new Pixel(202, 101, 10);
    seven = new Pixel(18,50,240);
    eight = new Pixel(1, 2, 3);
    lol = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one,two))),
            new ArrayList<>(Arrays.asList(three,four))));
    peter = new ArrayList<>(List.of((new ArrayList<>(Arrays.asList(one, two)))));
    griffin = new ArrayList<>(List.of((new ArrayList<>(List.of(one)))));
    family = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one,two))),
            new ArrayList<>(Arrays.asList(three,four)),
            new ArrayList<>(Arrays.asList(five,six)),
            new ArrayList<>(Arrays.asList(seven,eight))));
    guy = new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(one,two,three,four))),
            new ArrayList<>(Arrays.asList(five,six,seven,eight))));
    m = new ImagePPM(lol,2,2,255);
    a = new ImagePPM(peter,2,1,255);
    r = new ImagePPM(griffin, 1,1,100);
    i = new ImagePPM(family,2,4,0);
    o = new ImagePPM(guy,4,2,30);
  }
  @Test
  public void testLoad() {
    FileWriter f = new File("res/1by1");
    impOne.load("images/koala.ppm", "koala");
    assertTrue(impOne.hasKey("koala"));
    impOne.getHashMap().get("koala").getImageVals();




  }

}