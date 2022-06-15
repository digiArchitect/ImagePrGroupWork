package command;

import java.util.function.Function;

import pixel.PixelImpl;

/**
 * Function class used to make a PixelImpl grey.
 */
public class GreyScale implements Function<PixelImpl, PixelImpl> {
  String component;

  /**
   * Constructs greyScale class utilizing the calculation for which the pixel will be turned gray.
   * @param component The calculating value instruction to make the pixel grey.
   */
  public GreyScale(String component) {
    this.component = component;
  }

  /**
   * Greys out the given pixel depending on the channel requested.
   * @param pixel the pixel.
   * @return a new gray pixel.
   */
  public PixelImpl apply(PixelImpl pixel) {

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
    } else if (component.equals("luma")) {
      newVal = (int)(0.2126 * values[0] + 0.7152 * values[1] + 0.0722 * values[2]);
    } else if (component.equals("red")) {
      newVal = values[0];
    } else if (component.equals("green")) {
      newVal = values[1];
    } else if (component.equals("blue")) {
      newVal = values[2];
    } else {
      throw new IllegalArgumentException();
    }
    p.append(newVal);

    return new PixelImpl(newVal,newVal,newVal);
  }
}