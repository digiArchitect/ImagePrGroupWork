package image;

/**
 * Abstract apply class simply used to clean up the code.
 */
public class AbstractApply {
  protected int clamp(int value) {
    return FunctionUtils.clamp(value);
  }
  protected Pixel properRGB(int[] values) {
    return FunctionUtils.properRGB(values);
  }
}
