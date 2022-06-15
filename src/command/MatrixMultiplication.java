package command;

import java.util.function.Function;

import pixel.PixelImpl;

public class MatrixMultiplication implements Function<PixelImpl, PixelImpl> {
  Double[][] matrix;

  public MatrixMultiplication(Double[][] matrix) {
    this.matrix = matrix;
  }

  @Override
  public PixelImpl apply(PixelImpl pixel) {
    int[] newRgb = new int[3];
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        newRgb[x] += (int)(pixel.getChannel(y) * matrix[x][y]);
      }
      if (newRgb[x] > 255) {
        newRgb[x] = 255;
      } else if (newRgb[x] < 0) {
        newRgb[x] = 0;
      }
    }
    return new PixelImpl(newRgb[0], newRgb[1], newRgb[2]);
  }
    
}
