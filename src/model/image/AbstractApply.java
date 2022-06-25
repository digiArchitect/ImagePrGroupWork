package model.image;

/**
 * Abstract apply class simply used to clean up the code.
 */
public abstract class AbstractApply {
  /**
   * Clamps a value to the max set.
   * @param value the r g or b value.
   * @return the clamped value.
   */
  protected int clamp(int value) {
    return FunctionUtils.clamp(value);
  }

  /**
   * Produces dependent on a pixel and a string component.
   * @param component the component to retrieve
   * @param pixel the pixel to
   * @return the calculate integer
   */
  protected int newValue(String component, Pixel pixel) {
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
    newVal = clamp(newVal);

    return newVal;

  }
}
