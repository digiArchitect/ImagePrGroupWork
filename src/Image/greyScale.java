package Image;

import java.util.function.Function;

/**
 * Function class used to make a Pixel grey.
 */
public class greyScale implements Function<Pixel,Pixel> {
  String component;

  /**
   * Constructs greyScale class utilizing the calculation for which the pixel will be turned gray.
   * @param component The calculating value instruction to make the pixel grey.
   */
  public greyScale(String component) {
    this.component = component;
  }
  public Pixel apply(Pixel pixel) {

    int newVal;
    StringBuilder p = new StringBuilder();
    int[] values = new int[3];

    for (int x = 0; x < 3; x++) {
      values[x] = pixel.getChannel(x);
    }

    if (component.equals("value")) {
      newVal = Math.max(values[0],values[1]);
      newVal = Math.max(newVal , values[0]);
    } else if (component.equals("intensity")) {
      newVal = values[0] + values[1] + values[2];
      newVal /= 3;
    } else if(component.equals("luma")){
      newVal = (int)(0.2126 * values[0] + 0.7152 * values[1] + 0.0722 * values[2]);
    } else if(component.equals("red")) {
      newVal = values[0];
    } else if(component.equals("green")) {
      newVal = values[1];
    } else if(component.equals("blue")) {
      newVal = values[2];
    } else {
      throw new IllegalArgumentException();
    }
    p.append(newVal);

    return new Pixel(newVal,newVal,newVal);
  }
}