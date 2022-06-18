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
}
