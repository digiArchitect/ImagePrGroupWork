package image;

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
   * Returns a pixel with a 32-bit integer as its R G B value given a set of R G B integers.
   * @param values the R G B integers.
   * @return a pixel with that value as its color.
   */
  protected Pixel properRGB(int[] values) {
    return FunctionUtils.properRGB(values);
  }
}
