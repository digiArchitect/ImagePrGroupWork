package model.image;

import java.util.function.Function;

/**
 * Performs an operation on a pixel using a kernel matrix.
 */
public class MatrixMultiplication extends AbstractApply implements Function<Pixel, Pixel> {
  Double[][] matrix;

  /**
   * Constructs a Matrix Multiplication function.
   * @param matrix the matrix used for the mutation.
   */
  public MatrixMultiplication(Double[][] matrix) {
    this.matrix = matrix;
  }

  /**
   * Returns a new pixel of the matrix operation being performed on the given one.
   * @param pixel the given pixel.
   * @return the new pixel.
   */
  @Override
  public Pixel apply(Pixel pixel) {
    int[] newRgb = new int[3];
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        newRgb[x] += (int)(pixel.getChannel(y) * matrix[x][y]);
      }
      newRgb[x] = clamp(newRgb[x]);

    }
    return new PixelImpl(newRgb);
  }
    
}
